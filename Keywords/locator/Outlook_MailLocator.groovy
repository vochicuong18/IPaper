package locator

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable


public class Outlook_MailLocator extends TestObjectFactory {
	TestObject approveBtn, returnBtn, rejectBtn, sendEmailBtn, loadingBar, iosSendNoti, emailContent, emailContentWrapper

	Outlook_MailLocator() {
		switch(GlobalVariable.PLATFORM) {
			case "Android":
				approveBtn = createTestObject(LocatorType.XPATH, "//android.view.View[@content-desc='Duyệt']/parent::android.view.View")
				returnBtn = createTestObject(LocatorType.XPATH, "//android.view.View[@content-desc='Trả về']/parent::android.view.View")
				rejectBtn =createTestObject(LocatorType.XPATH, "//android.view.View[@content-desc='Từ chối']/parent::android.view.View")
				sendEmailBtn = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/compose_toolbar_send")
				loadingBar = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/snackbar_indicator")
				emailContent = createTestObject(LocatorType.ID, "ms-outlook-mobile-rooster-writer")
				break
			case"iOS":
				approveBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeLink[@name='Duyệt']")
				returnBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeLink[@name='Trả về']")
				rejectBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeLink[@name='Từ chối']")
				sendEmailBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ComposeToolbar.send']")
				iosSendNoti = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Đã gửi Thư']")
				emailContent = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTextView[@name='Nội dung email']")
				emailContentWrapper = createTestObject(LocatorType.XPATH,"//XCUIElementTypeTextView[@name='Nội dung email']/parent::XCUIElementTypeOther")
				break
		}
	}
}
