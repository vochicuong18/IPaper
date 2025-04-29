package screens

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

import base.BaseKeyword
import internal.GlobalVariable
import locator.Outlook_MailLocator

public class OutLook_MailScreen extends Outlook_MailLocator implements BaseKeyword {

	enum ActionType {
		APPROVE,
		REJECT,
		RETURN,
		COMMENT
	}

	def action(ActionType actionType, String comment = null) {
		TestObject actionButton
		Closure<String> getEmailContent

		switch (actionType) {
			case ActionType.APPROVE:
				actionButton = approveBtn
				getEmailContent = { c -> approveEmailContent(c) }
				break
			case ActionType.REJECT:
				actionButton = rejectBtn
				getEmailContent = { c -> rejectEmailContent(c) }
				break
			case ActionType.RETURN:
				actionButton = returnBtn
				getEmailContent = { c -> returnEmailContent(c) }
				break
			case ActionType.COMMENT:
				actionButton = commentBtn
				getEmailContent = { c -> returnEmailContent(c) }
				break
			default:
				KeywordUtil.markFailed("Invalid ActionType: ${actionType}")
				return
		}

		//swipeToElement(bottomMailContent)
		swipe ("down", 0.5) //handle scroll to actionButton
		waitForPresentOf(actionButton)
		clickToElement(actionButton)

		if (comment) {
			waitForPresentOf(sendEmailBtn)
			if(GlobalVariable.PLATFORM == 'iOS') {
				clickToElement(emailContentWrapper)
				Mobile.clearText(emailContent, 1)
				Mobile.sendKeys(emailContent, comment)
			} else {
				inputText(emailContent, getEmailContent(comment))
			}
		}
		Mobile.delay(1)
		clickToElement(sendEmailBtn)

		if (GlobalVariable.PLATFORM == 'iOS') {
			Mobile.delay(1)
			waitForNotPresentOf(iosSendNoti)
		} else {
			waitForPresentOf(loadingBar)
			waitForNotPresentOf(loadingBar)
		}
	}


	//	def clearEmailContent() {
	//		waitForNotPresentOf(emailContent)
	//		if(GlobalVariable.PLATFORM == 'iOS') {
	//			clickToElement(emailContentWrapper)
	//		}
	//	}

	String approveEmailContent(String comment) {
		String data =
				"[VUI LÒNG KHÔNG XÓA NỘI DUNG MẶC ĐỊNH CỦA EMAIL NÀY]\n" +
				"Để chuyển duyệt (nếu có) thì Anh/Chị nhập bổ sung 1 email vào mục TO\n" +
				"=====Bắt đầu ý kiến phản hồi (nếu có)=====\n" +
				comment + "\n" +
				"=====Kết thúc ý kiến phản hồi=====\n\n"
		return data
	}

	String rejectEmailContent(String comment) {
		String data =
				"[VUI LÒNG KHÔNG XÓA NỘI DUNG MẶC ĐỊNH CỦA EMAIL NÀY]\n" +
				"Anh/Chị vui lòng nhập nội dung phản hồi trong khung phía dưới\n" +
				"=====Bắt đầu ý kiến phản hồi từ chối (nếu có)=====\n\n" +
				comment + "\n" +
				"=====Kết thúc ý kiến phản hồi từ chối=====\n\n"
		return data
	}

	String returnEmailContent(String comment) {
		String data =
				"[VUI LÒNG KHÔNG XÓA NỘI DUNG MẶC ĐỊNH CỦA EMAIL NÀY]\n" +
				"Để lấy ý kiến người liên quan thì Anh/Chị nhập bổ sung email vào mục TO hoặc CC\n" +
				"=====Bắt đầu ý kiến phản hồi trả về (nếu có)=====\n" +
				comment + "\n" +
				"=====Kết thúc ý kiến phản hồi=====\n\n"
		return data
	}

	String commentsEmailContent(String comment) {
		String data =
				"[VUI LÒNG KHÔNG XÓA NỘI DUNG MẶC ĐỊNH CỦA EMAIL NÀY]\n" +
				"Anh/Chị vui lòng nhập nội dung phản hồi trong khung phía dưới\n" +
				"=====Bắt đầu nội dung góp ý=====\n\n" +
				comment + "\n" +
				"=====Kết thúc nội dung góp ý=====\n\n"
		return data
	}
}
