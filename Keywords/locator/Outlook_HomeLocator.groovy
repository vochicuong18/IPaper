package locator

import com.kms.katalon.core.testobject.TestObject

import entities.Document
import entities.LocatorType
import entities.User
import internal.GlobalVariable

public class Outlook_HomeLocator extends TestObjectFactory{
	TestObject search, searchTxt, searchProgress, accountButton, currentEmail, leftMenu, backBtn, accountItemSelected, firstEmail, timeReceived

	Outlook_HomeLocator() {
		switch(GlobalVariable.PLATFORM) {
			case "Android":
				search = createTestObject(LocatorType.XPATH, "//android.widget.Button[@content-desc='Tìm kiếm']/parent::androidx.appcompat.widget.LinearLayoutCompat")
				searchTxt = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/search_edit_text")
				searchProgress = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/search_progress")
				accountButton = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/account_button")
				currentEmail = createTestObject(LocatorType.XPATH, "//android.widget.TextView[contains(@text,'@hdbank.com.vn')]")
				leftMenu = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/left_menu")
				backBtn = createTestObject(LocatorType.XPATH, "//android.widget.ImageButton[@content-desc='Quay lại' or @content-desc='Đóng']")
				accountItemSelected = createTestObject(LocatorType.XPATH, "//android.widget.Button[@content-desc='Tất cả Tài khoản']/following-sibling::android.widget.Button[@selected = 'true']")
				firstEmail = createTestObject(LocatorType.XPATH, "//android.view.ViewGroup[@resource-id='com.microsoft.office.outlook:id/message_snippet_frontview'][1]")
				break
			case "iOS":
				search = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Tìm']")
				searchTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTextField[@name='SearchBox']")
				searchProgress = createTestObject(LocatorType.ID, "")
				accountButton = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='MailStartAvatar']")
				leftMenu = createTestObject(LocatorType.ID, "//XCUIElementTypeButton[@name='Đóng']")
				backBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Hủy bỏ'] | //XCUIElementTypeButton[@name='Quay lại']")
				firstEmail = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTable[@name='ResultsAllTabTable']/XCUIElementTypeCell[1]")
				break
		}
	}

	private String getEmailBaseXPath(String action, Document document) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return "//android.view.ViewGroup[contains(@content-desc, '${document.getTitle()}') and contains(@content-desc, '${action}')]"
			case "iOS":
				return "//XCUIElementTypeTable[@name='ResultsAllTabTable']/XCUIElementTypeCell[contains(@label, '${document.getTitle()}') and contains(@label, '${action}')]"
			default:
				throw new IllegalArgumentException("Unsupported platform: ${GlobalVariable.PLATFORM}")
		}
	}

	TestObject emailItem(String action, Document document) {
		String baseXPath = getEmailBaseXPath(action, document)
		return createTestObject(LocatorType.XPATH, baseXPath)
	}

	TestObject timeReceivedEmailItem(String action, Document document) {
		String baseXPath = getEmailBaseXPath(action, document)
		String timeXPath

		switch (GlobalVariable.PLATFORM) {
			case "Android":
				timeXPath = "${baseXPath}//android.widget.TextView[@resource-id='com.microsoft.office.outlook:id/message_snippet_time']"
				break
			case "iOS":
				timeXPath = "${baseXPath}/XCUIElementTypeStaticText[4]"
				break
		}

		return createTestObject(LocatorType.XPATH, timeXPath)
	}


	List<TestObject> accountItems(){
		switch(GlobalVariable.PLATFORM) {
			case 'Android':
				return createTestObjects(LocatorType.XPATH, "//android.widget.Button[@content-desc='Tất cả Tài khoản']/following-sibling::android.widget.Button")
			case 'iOS':
				return createTestObjects(LocatorType.XPATH, "")
		}
	}

	TestObject iosAccountItem(User user) {
		switch(GlobalVariable.PLATFORM) {
			case 'Android':
				return createTestObject(LocatorType.XPATH, "")
			case 'iOS':
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeCell[@name='MailAccountChooser.account.${user.getEmail()}']")
		}
	}
}
