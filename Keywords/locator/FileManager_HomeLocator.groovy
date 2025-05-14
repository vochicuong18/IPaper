package locator

import static entities.LocatorType.XPATH

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable


public class FileManager_HomeLocator extends TestObjectFactory  {
	TestObject searchTxt, iosBrowseBtn	
	FileManager_HomeLocator() {
		switch (GlobalVariable.PLATFORM) {
			case 'Android':
			break
			case 'iOS':
			iosBrowseBtn = createTestObject(XPATH, "//XCUIElementTypeButton[@name='Duyệt']")
		}
	}

	TestObject folder (String folderName) {
		switch(GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(XPATH, "//android.widget.EditText[@resource-id='com.coloros.filemanager:id/item_title' and @text='${folderName}']")
			case "iOS":
				return createTestObject(XPATH, "//XCUIElementTypeCell[@name='DOC.sidebar.item.${folderName}']")
		}
	}
}
