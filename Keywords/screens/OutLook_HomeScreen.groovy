package screens

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.util.KeywordUtil

import base.BaseKeyword
import entities.Document
import entities.User
import internal.GlobalVariable
import locator.Outlook_HomeLocator
import screens.PDFSignScreen.PerformAction

public class OutLook_HomeScreen extends Outlook_HomeLocator implements BaseKeyword{
	final int SEND_MAIL_TIME_OUT = 300 //5m

	enum EmailNoti{
		SEND_APPROVED,
		APPROVED,
		REJECTED,
		RETURNED,
		NOT_ACCEPTED,
		NOT_ACCEPTED_WITHDRAW

		String toString() {
			switch (this) {
				case SEND_APPROVED: return "gửi duyệt"
				case APPROVED: return "duyệt"
				case REJECTED: return "từ chối"
				case RETURNED: return "trả về"
				case NOT_ACCEPTED : return "Kính gửi Anh/Chị, Phê duyệt của Anh/Chị(Chi tiết file đính kèm) KHÔNG ĐƯỢC GHI NHẬN. Lý do: Hồ sơ không tồn tại hoặc đã được chuyển bước"
				case NOT_ACCEPTED_WITHDRAW : return "Kính gửi Anh/Chị, Phê duyệt của Anh/Chị(Chi tiết file đính kèm) KHÔNG ĐƯỢC GHI NHẬN. Lý do: Hồ sơ đã được chuyển bước hoặc rút lại"
				default : return "please define"
			}
		}
	}

	def backToHome() {
		while (!isDisplayed(search)) {
			clickToElement(backBtn)
		}
	}

	def searchEmail(String title) {
		waitForPresentOf(search)
		clickToElement(search)
		inputText(searchTxt, title)
		enterText(searchTxt)
	}

	def goToEmail(PerformAction action, Document document) {
		clickToElement(emailItem(action.toStringEmail(), document))
	}


	/*
	 * Search until the email show on search result
	 * */

	def waitActionEmailSent(PerformAction action, Document document) {
		String content = action.toStringEmail()
		waitForEmail(content, document)
	}

	def waitNotiEmailSent(User user, EmailNoti noti, Document document) {
		String content = "Kính gửi Anh/Chị, Yêu cầu đã được ${user.getName()} - ${user.getRole()} ${noti}"
		waitForEmail(content, document)
		backToHome()
	}

	def waitNotiEmailSent(EmailNoti noti, Document document) {
		String content = "${noti}"
		waitForEmail(content, document)
		backToHome()
	}

	private void waitForEmail(String content, Document document) {
		long deadline = System.currentTimeMillis() + SEND_MAIL_TIME_OUT * 1000

		/*Search email*/
		waitForPresentOf(search)
		clickToElement(search)

		while (System.currentTimeMillis() < deadline) {
			inputText(searchTxt, document.getTitle())
			enterText(searchTxt)
			// wait result shown after searching
			//			waitForNotPresentOf(searchProgress)
			Mobile.delay(3)
			if (isDisplayed(emailItem(content, document))) {
				KeywordUtil.markPassed("Email item appeared after ${(SEND_MAIL_TIME_OUT * 1000 - (deadline - System.currentTimeMillis())) / 1000} seconds")
				return
			}
			Mobile.delay(15)
		}
		KeywordUtil.markFailedAndStop("Email item not found within ${SEND_MAIL_TIME_OUT} seconds.")
	}


	//--------------------------------------

	//	def switchToAccount(User user) {
	//		if(GlobalVariable.PLATFORM == 'iOS') {
	//			openListAccountTab(true)
	//			clickToElement(iosAccountItem(user))
	//			return
	//		}
	//
	//		else {
	//			String expectedEmail = user.getEmail()
	//			for (int i = 0; i < 5; i++) {
	//				openListAccountTab(true)
	//				if (getText(currentEmail) == expectedEmail) {
	//					KeywordUtil.logInfo("Switched account: $expectedEmail")
	//					openListAccountTab(false)
	//					return
	//				}
	//				def items = accountItems()
	//		if (i >= items.size()) break
	//
	//		clickToElement(items[i])
	//			}
	//			KeywordUtil.markFailed("Không tìm thấy account: $expectedEmail")
	//		}
	//	}

	def switchToAccount(User user) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			openListAccountTab(true)
			clickToElement(iosAccountItem(user))
			return
		}

		String expectedEmail = user.getEmail()
		openListAccountTab(true)

		// Check current email account
		if (getText(currentEmail) == expectedEmail) {
			KeywordUtil.logInfo("Switched account: $expectedEmail")
			openListAccountTab(false)
			return
		}

		// If email does not match, get account list and click each item
		def items = accountItems()
		items.each { item ->
			clickToElement(item)
			Mobile.delay(1)
			if (getText(currentEmail) == expectedEmail) {
				KeywordUtil.logInfo("Switched account: $expectedEmail")
				openListAccountTab(false)
				return
			}
		}

		KeywordUtil.markFailedAndStop("Not found: $expectedEmail")
	}

	def openListAccountTab(boolean open) {
		if (open && !isDisplayed(leftMenu)) {
			clickToElement(accountButton)
		} else if (!open && isDisplayed(leftMenu)) {
			clickToElement(accountItemSelected)
		}
	}
}
