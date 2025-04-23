package screens

import static org.assertj.core.api.InstanceOfAssertFactories.STRING

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

import base.BaseKeyword
import entities.Document
import entities.User
import locator.Outlook_HomeLocator
import screens.PDFSignScreen.PerformAction

public class OutLook_HomeScreen extends Outlook_HomeLocator implements BaseKeyword{

	enum EmailNoti{
		SEND_APPROVED,
		APPROVED,
		REJECTED

		String toString() {
			switch (this) {
				case SEND_APPROVED: return "gửi duyệt"
				case APPROVED: return "duyệt"
				case REJECTED: return "từ chối"
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

	/*
	 * Search until email show
	 * */

	def waitActionEmailSent(PerformAction action, Document document) {
		int timeoutInSeconds = 300
		long deadline = System.currentTimeMillis() + timeoutInSeconds * 1000

		waitForPresentOf(search)
		clickToElement(search)

		while (System.currentTimeMillis() < deadline) {
			inputText(searchTxt, document.getTitle())
			enterText(searchTxt)
			Mobile.delay(5) // wait for search loading

			if (isDisplayed(emailItem(action.toStringEmail(), document))) {
				long elapsedTime = (timeoutInSeconds * 1000 - (deadline - System.currentTimeMillis())) / 1000
				KeywordUtil.markPassed("Email item appeared after ${elapsedTime} seconds")
				return
			}
			Mobile.delay(15)
		}
		KeywordUtil.markFailedAndStop("Email item not found within ${timeoutInSeconds} seconds.")
	}


	/*
	 * Search until email show
	 * */

	def waitNotiEmailSent(User user, EmailNoti noti, Document document) {
		int timeoutInSeconds = 300
		long deadline = System.currentTimeMillis() + timeoutInSeconds * 1000

		waitForPresentOf(search)
		clickToElement(search)

		while (System.currentTimeMillis() < deadline) {
			inputText(searchTxt, document.getTitle())
			enterText(searchTxt)
			Mobile.delay(5) // wait for search loading

			if (isDisplayed(emailItem(emailContent(user, noti), document))) {
				long elapsedTime = (timeoutInSeconds * 1000 - (deadline - System.currentTimeMillis())) / 1000
				KeywordUtil.markPassed("Email item appeared after ${elapsedTime} seconds")
				return
			}
			Mobile.delay(15)
		}
		KeywordUtil.markFailed("Email item not found within ${timeoutInSeconds} seconds.")
	}



	def goToEmail(PerformAction action, Document document) {
		clickToElement(emailItem(action.toStringEmail(), document))
	}

	def emailContent(User user, EmailNoti noti) {
		String content = "Kính gửi Anh/Chị, Yêu cầu đã được ${user.getName()} ${noti.toString()}"
	}

	def switchToAccount(User user) {
		String expectedEmail = user.getEmail()

		for (int i = 0; i < 5; i++) {
			openListAccountTab(true)
			if (getText(currentEmail) == expectedEmail) {
				KeywordUtil.logInfo("Đã ở account: $expectedEmail")
				openListAccountTab(false)
				return
			}
			def items = accountItems()
			if (i >= items.size()) {
				break
			}
			clickToElement(items[i])
		}
		KeywordUtil.markFailed("Không tìm thấy account: $expectedEmail")
	}


	def openListAccountTab(boolean open) {
		if (open && !isDisplayed(leftMenu)) {
			clickToElement(accountButton)
		} else if (!open && isDisplayed(leftMenu)) {
			horizontalSwipeFromElement(currentEmail,'left')
		}
	}
}
