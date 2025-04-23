package locator

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

public class HomeScreenLocator extends TestObjectFactory {
	TestObject avatar, createRequestItems, inComingDocument, loadingMask, logoutButton, menuIcon, menuTab, outComingDocument, requestItem, screenTitle,
	closeMenuTab, closeTabBtn, setting

	TestObject requestItem(String requestName) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text='$requestName']",
				[('requestName'): requestName])

			case 'iOS':
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='${requestName}']/parent::XCUIElementTypeCell",
				[('requestName'): requestName])
		}
	}

	HomeScreenLocator() {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				avatar = createTestObject(LocatorType.XPATH, "//android.widget.ImageView[@resource-id='com.hdbank.ipaper:id/img_menu_profile']")
			//				requestItem = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item' and @text='$requestName}']/parent::android.widget.LinearLayout/parent::android.widget.RelativeLayout")
				createRequestItems = createTestObject(LocatorType.XPATH, "//android.widget.CheckedTextView[@resource-id='com.hdbank.ipaper:id/design_menu_item_text' and @text='Tạo yêu cầu theo mẫu']")
				inComingDocument = createTestObject(LocatorType.XPATH, "//android.widget.CheckedTextView[@resource-id='com.hdbank.ipaper:id/design_menu_item_text' and @text='Hồ sơ đến']/parent::androidx.appcompat.widget.LinearLayoutCompat")
				outComingDocument = createTestObject(LocatorType.XPATH, "//android.widget.CheckedTextView[@resource-id='com.hdbank.ipaper:id/design_menu_item_text' and @text='Hồ sơ đi']/parent::androidx.appcompat.widget.LinearLayoutCompat")
				loadingMask = createTestObject(LocatorType.XPATH, "//android.widget.RelativeLayout[@resource-id='com.hdbank.ipaper:id/layout_bt_floating']")
				logoutButton = createTestObject(LocatorType.XPATH, "//android.widget.CheckedTextView[@resource-id='com.hdbank.ipaper:id/design_menu_item_text' and @text='Đăng xuất']")
				menuIcon = createTestObject(LocatorType.XPATH, "//android.widget.RelativeLayout[@resource-id='com.hdbank.ipaper:id/layout_home_bar']")
				menuTab = createTestObject(LocatorType.XPATH, "//android.widget.FrameLayout[@resource-id='com.hdbank.ipaper:id/navigation_view']")
				screenTitle = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_title")
				setting = createTestObject(LocatorType.XPATH, "//android.widget.CheckedTextView[@resource-id='com.hdbank.ipaper:id/design_menu_item_text' and @text='Cài đặt']")
				break
			case "iOS":
				avatar = createTestObject(LocatorType.XPATH, "")
			//				requestItem = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='${requestName}']/parent::XCUIElementTypeCell")
				createRequestItems = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='menu_template']")
				inComingDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='menu_tasks']/parent::XCUIElementTypeCell")
				outComingDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='menu_outcomingdocs']/parent::XCUIElementTypeCell")
				loadingMask = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='placeholder_loading_list_doc']")
				logoutButton = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='menu_logout']//parent::XCUIElementTypeCell")
				menuIcon = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic user info']")
				menuTab = createTestObject(LocatorType.XPATH, "")
				screenTitle = createTestObject(LocatorType.XPATH, "//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText")
				closeMenuTab = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='clearX']")
				closeTabBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='clearX']")
				setting = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Cài đặt']")
				break
		}
	}
}
