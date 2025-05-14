package screens

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

import base.BaseKeyword
import entities.Document
import internal.GlobalVariable
import locator.Outlook_MailLocator
import utilities.AssertUtilities

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

	def actionSummarizeEmail(Document document, ActionType actionType, String comment = null) {
		TestObject actionButton
		Closure<String> getEmailContent

		switch (actionType) {
			case ActionType.APPROVE:
				actionButton = summarizeApproveBtn(document)
				getEmailContent = { c -> approveEmailContent(c) }
				break
			case ActionType.REJECT:
				actionButton = summarizeRejectBtn(document)
				getEmailContent = { c -> rejectEmailContent(c) }
				break
			case ActionType.RETURN:
				actionButton = summarizeReturnBtn(document)
				getEmailContent = { c -> returnEmailContent(c) }
				break
			default:
				KeywordUtil.markFailed("Invalid ActionType: ${actionType}")
				return
		}

		swipeToElement(actionButton)
//		swipe ("down", 0.2) //handle scroll to actionButton
		clickToElement(actionButton)
		waitForPresentOf(sendEmailBtn)
		
		if (comment) {
			if(GlobalVariable.PLATFORM == 'iOS') {
				clickToElement(emailContentWrapper)
				Mobile.clearText(emailContent, 1)
				Mobile.sendKeys(emailContent, getEmailContent(comment))
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

	String getDocumentTitle() {
		String subject = getText(subjectMail)
		def matcher = subject =~ /(Trình ký \d+)/
		if (matcher.find()) {
			String title = matcher.group(1)
			println title
			return title
		}
	}


	/**
	 * Checks whether the email was received within the specified number of hours from the current time.
	 *
	 * @param hours The number of hours allowed between the email received time and the current time.
	 * @return true if the received time is within the specified range, otherwise false.
	 */

	def checkEmailReceivedTime(int hours) {
		String timeText = getText(timeReceived)
		if (GlobalVariable.PLATFORM == 'iOS') timeText = timeText.substring(timeText.lastIndexOf(',') + 1).trim()

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm")
		LocalTime received = LocalTime.parse(timeText, formatter)
		LocalTime now = LocalTime.now()

		long minutesDiff = ChronoUnit.MINUTES.between(received, now)
		AssertUtilities.assertTrue(minutesDiff <= hours * 60)
	}



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
