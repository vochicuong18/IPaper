package locator

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

public class ProfileLocator extends TestObjectFactory {
	TestObject backBtn, email, name, phoneNumber, userName
	ProfileLocator () {
		switch(GlobalVariable.PLATFORM) {
			case "Android":
				backBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_arrow_back")
				email = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/edit_text_value_email")
				name = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/edit_text_value_full_name")
				phoneNumber = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/edit_text_value_phone_number")
				userName = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/edit_text_value_user_name")
				break
			case "iOS":
				backBtn = createTestObject(LocatorType.XPATH, "")
				email = createTestObject(LocatorType.XPATH, "")
				phoneNumber = createTestObject(LocatorType.XPATH, "")
				break
		}
	}
}
