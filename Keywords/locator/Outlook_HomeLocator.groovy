package locator

import static entities.LocatorType.XPATH

import com.kms.katalon.core.testobject.TestObject

import entities.Document
import entities.LocatorType
import internal.GlobalVariable
import screens.PDFSignScreen.PerformAction

public class Outlook_HomeLocator extends TestObjectFactory{
	TestObject search, searchTxt, accountButton, currentEmail, leftMenu, backBtn

	Outlook_HomeLocator() {
		switch(GlobalVariable.PLATFORM) {
			case "Android":
				search = createTestObject(LocatorType.XPATH, "//android.widget.Button[@content-desc='Tìm kiếm']/parent::androidx.appcompat.widget.LinearLayoutCompat")
				searchTxt = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/search_edit_text")
				accountButton = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/account_button")
				currentEmail = createTestObject(LocatorType.XPATH, "//android.widget.TextView[contains(@text,'@hdbank.com.vn')]")
				leftMenu = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/left_menu")
				backBtn = createTestObject(LocatorType.XPATH, "//android.widget.ImageButton[@content-desc='Quay lại' or @content-desc='Đóng']")
				break
			case "iOS":
				search = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Tìm']")
				searchTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTextField[@name='SearchBox']")
				accountButton = createTestObject(LocatorType.XPATH, "")
				leftMenu = createTestObject(LocatorType.ID, "")
				backBtn = createTestObject(LocatorType.XPATH, "")
				break
		}
	}

	TestObject emailItem(String action, Document document) {
		switch(GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH,"//android.view.ViewGroup[contains(@content-desc, '${document.getTitle()}') and contains(@content-desc, '${action}')]")
			case "iOS":
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeTable[@name='ResultsAllTabTable']/XCUIElementTypeCell[contains(@label, '${document.getTitle()}') and contains(@label, '${action}')]")
		}
	}

	List<TestObject> accountItems(){
		switch(GlobalVariable.PLATFORM) {
			case 'Android':
				return createTestObjects(LocatorType.XPATH, "//android.widget.Button[@content-desc='Tất cả Tài khoản']/following-sibling::android.widget.Button")
			case 'iOS':
				return createTestObjects(LocatorType.XPATH, "")
		}
	}
}
