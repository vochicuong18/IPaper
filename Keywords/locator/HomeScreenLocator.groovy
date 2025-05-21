package locator

import static entities.LocatorType.XPATH

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

public class HomeScreenLocator extends TestObjectFactory {
	TestObject avatar, createRequestItems, inComingDocument, loadingMask, logoutButton, menuIcon, menuTab, draftDocument, outComingDocument, requestItem, screenTitle,
	closeMenuTab, closeTabBtn, setting, logo, loadingItem, addDocumentIcon, fillterBtn, relatedDocumentBtn, loadingListDocument, listFormSample

	TestObject requestItem(String requestName) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text='$requestName']")
			case 'iOS':
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='${requestName}']/parent::XCUIElementTypeCell")
		}
	}

	HomeScreenLocator() {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				loadingItem = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_loading_item")
				logo = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_logo")
				avatar = createTestObject(LocatorType.XPATH, "//android.widget.ImageView[@resource-id='com.hdbank.ipaper:id/img_menu_profile']")
				createRequestItems = createTestObject(LocatorType.XPATH, "//android.widget.CheckedTextView[@resource-id='com.hdbank.ipaper:id/design_menu_item_text' and @text='Tạo yêu cầu theo mẫu']")
				inComingDocument = createTestObject(LocatorType.XPATH, "//android.widget.CheckedTextView[@resource-id='com.hdbank.ipaper:id/design_menu_item_text' and @text='Hồ sơ đến']/parent::androidx.appcompat.widget.LinearLayoutCompat")
				outComingDocument = createTestObject(LocatorType.XPATH, "//android.widget.CheckedTextView[@resource-id='com.hdbank.ipaper:id/design_menu_item_text' and @text='Hồ sơ đi']/parent::androidx.appcompat.widget.LinearLayoutCompat")
				draftDocument = createTestObject(LocatorType.XPATH, "//android.widget.CheckedTextView[@resource-id='com.hdbank.ipaper:id/design_menu_item_text' and @text='Hồ sơ nháp']/parent::androidx.appcompat.widget.LinearLayoutCompat")
				loadingMask = createTestObject(LocatorType.XPATH, "//android.widget.RelativeLayout[@resource-id='com.hdbank.ipaper:id/layout_bt_floating']")
				logoutButton = createTestObject(LocatorType.XPATH, "//android.widget.CheckedTextView[@resource-id='com.hdbank.ipaper:id/design_menu_item_text' and @text='Đăng xuất']")
				menuIcon = createTestObject(LocatorType.XPATH, "//android.widget.RelativeLayout[@resource-id='com.hdbank.ipaper:id/layout_home_bar']")
				menuTab = createTestObject(LocatorType.XPATH, "//android.widget.FrameLayout[@resource-id='com.hdbank.ipaper:id/navigation_view']")
				screenTitle = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_title")
				setting = createTestObject(LocatorType.XPATH, "//android.widget.CheckedTextView[@resource-id='com.hdbank.ipaper:id/design_menu_item_text' and @text='Cài đặt']")
				fillterBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_filter")
				relatedDocumentBtn = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_filter' and @text='Hồ sơ liên quan']")
			//				loadingListDocument = createTestObject(XPATH, "")
				listFormSample = createTestObject(LocatorType.XPATH, "//android.widget.ExpandableListView[@resource-id='com.hdbank.ipaper:id/expandible_listview']//android.widget.RelativeLayout")
				break
			case "iOS":
				loadingItem = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='placeholder_loading_list_doc']")
				logo = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Đăng nhập']")
				avatar = createTestObject(LocatorType.XPATH, "")
			//				requestItem = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='${requestName}']/parent::XCUIElementTypeCell")
				createRequestItems = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='menu_template']")
				inComingDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='menu_tasks']/parent::XCUIElementTypeCell")
				outComingDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='menu_outcomingdocs']/parent::XCUIElementTypeCell")
				draftDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='menu_draft']")

				loadingMask = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='placeholder_loading_list_doc']")
				logoutButton = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='menu_logout']//parent::XCUIElementTypeCell")
				menuIcon = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic user info']")
				menuTab = createTestObject(LocatorType.XPATH, "")
				screenTitle = createTestObject(LocatorType.XPATH, "//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText")
				closeMenuTab = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='clearX']")
				closeTabBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='clearX']")
				setting = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Cài đặt']")
				addDocumentIcon = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic_add']")
				fillterBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic filter']")
				relatedDocumentBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Hồ sơ liên quan']/parent::XCUIElementTypeCell")
				listFormSample = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTable/XCUIElementTypeCell")
				break
		}
	}
}
