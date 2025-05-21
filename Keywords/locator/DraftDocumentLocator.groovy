package locator

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

public class DraftDocumentLocator extends TestObjectFactory {
	TestObject searchDocument, requestType, loadingSwipeIcon, filterBtn, backBtn, firstDocumentTitle, firstDocumentDate, secondItem, headerBar

	DraftDocumentLocator() {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				backBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_home_bar")
				filterBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_filter")
				requestType = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_value_process_type")
				loadingSwipeIcon = createTestObject(LocatorType.XPATH, "//android.view.ViewGroup[@resource-id='com.hdbank.ipaper:id/swipe_layout']/android.widget.ImageView")
				secondItem = createTestObject(LocatorType.XPATH, "(//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item']//ancestor::android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/layout_inf']//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/view_infor'])[2]")
				headerBar = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_home_bar")
				firstDocumentDate = createTestObject(LocatorType.XPATH, "(//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/view_infor'])[1]//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_date_approve']")
				firstDocumentTitle = createTestObject(LocatorType.XPATH, "(//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/view_infor'])[1]//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item']")
				break
			case "iOS":
				backBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic back']")
				filterBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic filter']")
				searchDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic-search']/parent::XCUIElementTypeOther/XCUIElementTypeTextField")
				requestType = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Loại yêu cầu:']/following-sibling::XCUIElementTypeStaticText[contains(@name, 'Quy trình PDF') or contains(@name, 'Review process')]")
				loadingSwipeIcon = createTestObject(LocatorType.XPATH, "//XCUIElementTypeActivityIndicator[@name='Progress halted']")
				firstDocumentDate = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTable/XCUIElementTypeCell[1]//XCUIElementTypeStaticText[3]")
				firstDocumentTitle = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTable/XCUIElementTypeCell[1]//XCUIElementTypeStaticText[1]")
				break
		}
	}

	TestObject documentItem(String documentTitle) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item' and @text='${documentTitle}']")
			case "iOS":
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='${documentTitle}']/parent::XCUIElementTypeCell")
		}
	}

	List<TestObject> titleItems() {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObjects(LocatorType.XPATH,"(//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item'])")
			case "iOS":
				return createTestObjects(LocatorType.XPATH, "")
		}
	}
}
