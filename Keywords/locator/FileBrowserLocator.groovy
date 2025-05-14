package locator

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable


public class FileBrowserLocator extends TestObjectFactory {
	TestObject fileLbl, searchBtn, searchTxt, submitSelectedFile

	FileBrowserLocator() {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				searchBtn = createTestObject(LocatorType.ID, "com.google.android.documentsui:id/option_menu_search")
				searchTxt = createTestObject(LocatorType.ID, "com.google.android.documentsui:id/search_src_text")
				submitSelectedFile = createTestObject(LocatorType.XPATH, "")
				break
			case "iOS":
				searchBtn = createTestObject(LocatorType.XPATH, "")
				searchTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeSearchField[@name='Tìm kiếm']")
				submitSelectedFile = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Mở']")
				break
		}
	}

	TestObject fileLbl(fileName) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='android:id/title' and @text='${fileName}']/parent::android.widget.LinearLayout", [('fileName') : fileName])
			case "iOS":
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeCell[@name='${fileName}, pdf']", [('fileName') : fileName])
		}
	}
}