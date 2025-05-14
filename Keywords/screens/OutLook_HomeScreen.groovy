package screens

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
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
		NOT_ACCEPTED_WITHDRAW,
		GET_COMMENT,
		COMMENTED,
		REJECT_COMMENT,
		SUMMARY_DOCUMENT

		String toString() {
			switch (this) {
				case SEND_APPROVED: return "gửi duyệt"
				case APPROVED: return "duyệt"
				case REJECTED: return "từ chối"
				case RETURNED: return "trả về"
				case GET_COMMENT: return "lấy ý kiến"
				case NOT_ACCEPTED : return "Kính gửi Anh/Chị, Phê duyệt của Anh/Chị(Chi tiết file đính kèm) KHÔNG ĐƯỢC GHI NHẬN. Lý do: Hồ sơ không tồn tại hoặc đã được chuyển bước"
				case NOT_ACCEPTED_WITHDRAW : return "Kính gửi Anh/Chị, Phê duyệt của Anh/Chị(Chi tiết file đính kèm) KHÔNG ĐƯỢC GHI NHẬN. Lý do: Hồ sơ đã được chuyển bước hoặc rút lại"
				case COMMENTED : return "Kính gửi Anh/Chị, Góp ý của Anh/Chị đã được hệ thống tiếp nhận và xử lý. Chi tiết Anh/Chị vui lòng xem file đính kèm."
				case REJECT_COMMENT : return "Kính gửi Anh/Chị, Góp ý của Anh/Chị không được hệ thống xử lý. Lý do: Vui lòng nhập nội dung khi cho ý kiến!"
				case SUMMARY_DOCUMENT : return "HỒ SƠ IPAPER ĐANG CHỜ PHÊ DUYỆT ĐẾN NGÀY"
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

	def goToFirstEmail() {
		waitForPresentOf(firstEmail)
		clickToElement(firstEmail)
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

	def waitNotiEmailSent(String noti) {
		String content = "${noti}"
		waitForEmail(content)
	}

	/**
	 * Waits for an email with the given content to appear within the timeout.
	 * If a document is provided, its title will be used in the search criteria.
	 * Also validates that the received email is within the allowed time window.
	 */

	private void waitForEmail(String content, Document document = null) {
		long deadline = System.currentTimeMillis() + SEND_MAIL_TIME_OUT * 1000
		LocalTime startTime = LocalTime.now()

		TestObject email = emailItem(content, document != null ? document : new Document(title: ''))
		TestObject timeReceivedEmail = timeReceivedEmailItem(content, document != null ? document : new Document(title: ''))

		// Search email
		waitForPresentOf(search)
		clickToElement(search)
		waitForPresentOf(searchTxt)
		
		while (System.currentTimeMillis() < deadline) {
			// Use document title if available, otherwise use empty string
			String keyword = document != null ? document.getTitle() : content
			inputText(searchTxt, keyword)
			enterText(searchTxt)

			Thread.sleep(3)

			// Pass empty title if document is null
			if (isDisplayed(email)) {
				println "Check email time"
				String timeText = getText(timeReceivedEmail).trim()
				println timeText
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm")
				LocalTime receivedTime = LocalTime.parse(timeText, formatter)

				long diffMinutes = ChronoUnit.MINUTES.between(startTime, receivedTime)
				println "check diff: " + diffMinutes
				if (diffMinutes >= 0 && diffMinutes <= SEND_MAIL_TIME_OUT) {
					KeywordUtil.markPassed("Email item appeared after ${(SEND_MAIL_TIME_OUT * 1000 - (deadline - System.currentTimeMillis())) / 1000} seconds")
					return
				}
			}

			Thread.sleep(15)
		}
		KeywordUtil.markFailedAndStop("Email item not found within ${SEND_MAIL_TIME_OUT} seconds.")
	}




	//--------------------------------------

	def switchToAccount(User user) {
		if(GlobalVariable.PLATFORM == 'iOS') {
			openListAccountTab(true)
			clickToElement(iosAccountItem(user))
			return
		}

		else {
			String expectedEmail = user.getEmail()
			for (int i = 0; i < 5; i++) {
				openListAccountTab(true)
				if (getText(currentEmail) == expectedEmail) {
					KeywordUtil.logInfo("Switched account: $expectedEmail")
					openListAccountTab(false)
					return
				}
				def items = accountItems()
				if (i >= items.size()) break
					clickToElement(items[i])
			}
			KeywordUtil.markFailed("Không tìm thấy account: $expectedEmail")
		}
	}

	def openListAccountTab(boolean open) {
		if (open && !isDisplayed(leftMenu)) {
			Mobile.delay(0.3)
			clickToElement(accountButton)
			Mobile.delay(0.3)
		} else if (!open && isDisplayed(leftMenu)) {
			clickToElement(accountItemSelected)
			Mobile.delay(0.3)
		}
	}
}
