package locator

import com.kms.katalon.core.testobject.TestObject

import entities.Document
import entities.LocatorType
import internal.GlobalVariable


public class Outlook_MailLocator extends TestObjectFactory {
	TestObject approveBtn, returnBtn, rejectBtn, commentBtn, sendEmailBtn, loadingBar, iosSendNoti, emailContent, emailContentWrapper, bottomMailContent, timeReceived, subjectMail

	Outlook_MailLocator() {
		switch(GlobalVariable.PLATFORM) {
			case "Android":
				approveBtn = createTestObject(LocatorType.XPATH, "//android.view.View[@content-desc='Duyệt']/parent::android.view.View")
				returnBtn = createTestObject(LocatorType.XPATH, "//android.view.View[@content-desc='Trả về']/parent::android.view.View")
				rejectBtn = createTestObject(LocatorType.XPATH, "//android.view.View[@content-desc='Từ chối']/parent::android.view.View")
				commentBtn = createTestObject(LocatorType.XPATH, "//android.view.View[@content-desc='Đóng góp ý kiến']/parent::android.view.View")
				sendEmailBtn = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/compose_toolbar_send")
				loadingBar = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/snackbar_indicator")
				emailContent = createTestObject(LocatorType.XPATH, "//android.widget.EditText[@resource-id='ms-outlook-mobile-rooster-writer']")
				bottomMailContent = createTestObject(LocatorType.XPATH, "//android.view.View[@text='Đây là email tự động từ hệ thống iPaper. Anh/Chị vui lòng thực hiện các nút chức năng trên Email này. Vui lòng không phản hồi email này!']")
				timeReceived = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/short_date")
				subjectMail = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/compose_subject_field")
				break
			case"iOS":
				approveBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeLink[@name='Duyệt']")
				returnBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeLink[@name='Trả về']")
				rejectBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeLink[@name='Từ chối']")
				commentBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Đóng góp ý kiến']")
				sendEmailBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ComposeToolbar.send']")
				iosSendNoti = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Đã gửi Thư']")
				emailContent = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTextView[@name='Nội dung email']")
				emailContentWrapper = createTestObject(LocatorType.XPATH,"//XCUIElementTypeTextView[@name='Nội dung email']/parent::XCUIElementTypeOther")
				bottomMailContent = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Vui lòng không phản hồi email này!']")
				timeReceived = createTestObject(LocatorType.XPATH, "//XCUIElementTypeCollectionView[@name='ConversationThread']//XCUIElementTypeButton[1]")
				break
		}
	}

	TestObject summarizeApproveBtn (Document document) {
		String title = document.getTitle()
		String number = (title =~ /\d+/)[0]
		switch(GlobalVariable.PLATFORM ) {
			case 'Android':
				return createTestObject(LocatorType.XPATH, "//android.view.View[contains(@text,'${document.getTitle()}')]/parent::android.view.View//android.view.View[@content-desc='Duyệt'][1]/parent::android.view.View")
			case 'iOS':
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeLink[@name='${number}']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther//XCUIElementTypeLink[@name='Duyệt']")
		}
	}

	TestObject summarizeRejectBtn (Document document) {
		switch(GlobalVariable.PLATFORM ) {
			case 'Android':
				return createTestObject(LocatorType.XPATH, "//android.view.View[contains(@text,'${document.getTitle()}')]/parent::android.view.View//android.view.View[@content-desc='Từ chối'][1]/parent::android.view.View")
			case 'iOS':
				return createTestObject(LocatorType.XPATH, "")
		}
	}

	TestObject summarizeReturnBtn (Document document) {
		switch(GlobalVariable.PLATFORM ) {
			case 'Android':
				return createTestObject(LocatorType.XPATH, "//android.view.View[contains(@text,'${document.getTitle()}')]/parent::android.view.View//android.view.View[@content-desc='Trả về'][1]/parent::android.view.View")
			case 'iOS':
				return createTestObject(LocatorType.XPATH, "")
		}
	}
}
