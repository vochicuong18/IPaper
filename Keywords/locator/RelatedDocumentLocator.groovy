package locator

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

public class RelatedDocumentLocator extends TestObjectFactory{
	TestObject documentItem, fillterBtn, relatedDocumentBtn, searchDocument

	RelatedDocumentLocator() {
		switch(GlobalVariable.PLATFORM) {
			case "Android":
				fillterBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_filter")
				relatedDocumentBtn = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_filter' and @text='Hồ sơ liên quan']")
				break
			case "iOS":
				fillterBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic filter']")
				relatedDocumentBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Hồ sơ liên quan']/parent::XCUIElementTypeCell")
				searchDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTextField[@value='Tìm kiếm hồ sơ']")
				break
		}
	}

	TestObject documentItem(String documentTitle) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item' and @text='${documentTitle}']//ancestor::android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/layout_inf']", [('documentTitle'): documentTitle])
			case "iOS":
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='${documentTitle}']/parent::XCUIElementTypeCell", [('documentTitle'): documentTitle])
		}
	}
}
