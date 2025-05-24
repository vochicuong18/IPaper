package locator

import static entities.LocatorType.ID

import com.kms.katalon.core.testobject.TestObject

import internal.GlobalVariable

public class NotificationLocator extends TestObjectFactory{
	TestObject deleteAllNofi, readAllNoti, submitDeleted, emptyIcon

	NotificationLocator() {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				deleteAllNofi = createTestObject(ID, "com.hdbank.ipaper:id/img_delete_all")
				readAllNoti = createTestObject(ID, "com.hdbank.ipaper:id/layout_check")
				submitDeleted = createTestObject(ID, "com.hdbank.ipaper:id/btn_one")
				emptyIcon = createTestObject(ID, "com.hdbank.ipaper:id/img_empty")
				break
			case "iOS":
				deleteAllNofi = createTestObject(ID, "")
				readAllNoti = createTestObject(ID, "")
				submitDeleted = createTestObject(ID, "")
				break
		}
	}
}
