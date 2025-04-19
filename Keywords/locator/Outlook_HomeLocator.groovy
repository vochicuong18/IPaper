package locator

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

public class Outlook_HomeLocator extends TestObjectFactory{
	TestObject search, searchTxt

	Outlook_HomeLocator() {
		switch(GlobalVariable.PLATFORM) {
			case "Android":
				search = createTestObject(LocatorType.XPATH, "//android.widget.Button[@content-desc='Tìm kiếm']")
				searchTxt = createTestObject(LocatorType.ID, "com.microsoft.office.outlook:id/search_edit_text")
				break
			case "iOS":
				search = createTestObject(LocatorType.XPATH, "")
				searchTxt = createTestObject(LocatorType.ID, "")
				break
		}
	}
}
