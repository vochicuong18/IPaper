package locator
import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

class LoginLocator extends TestObjectFactory {
	TestObject userNameTxt, passwordTxt, ignoreUpdate, loginButton, title, loadingMask

	LoginLocator() {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				userNameTxt = createTestObject(LocatorType.XPATH, "//android.widget.EditText[@resource-id='com.hdbank.ipaper:id/edit_text_user_name']")
				passwordTxt = createTestObject(LocatorType.XPATH, "//android.widget.EditText[@resource-id='com.hdbank.ipaper:id/edit_text_pw']")
				ignoreUpdate = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text = 'Bỏ qua']")
				loginButton = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/btn_sign_in")
				title = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_logo")
				loadingMask = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/loading_image")
				break

			case "iOS":
				ignoreUpdate = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Bỏ qua']")
				userNameTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic_arrow_for_company_list']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeTextField")
				passwordTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic_arrow_for_company_list']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeSecureTextField")
				loginButton = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Đăng nhập']")
				title = createTestObject(LocatorType.XPATH, "//XCUIElementTypeToolbar[@name='Toolbar']")
				loadingMask = createTestObject(LocatorType.XPATH, "//android.widget.RelativeLayout[@resource-id='com.hdbank.ipaper:id/layout_bt_floating']")
				break
		}
	}
}
