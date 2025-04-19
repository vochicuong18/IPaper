package locator

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

public class SettingLocator extends TestObjectFactory{
	TestObject approveByEmailSwitch, back

	SettingLocator () {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				approveByEmailSwitch = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/switch_aprove_email")
				back = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_arrow_back")
				break
			case "iOS":
				approveByEmailSwitch = createTestObject(LocatorType.ID, "")
				break
		}
	}
}
