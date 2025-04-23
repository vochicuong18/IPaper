package locator

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

public class SettingLocator extends TestObjectFactory{
	TestObject approveByEmailSwitch, approveByEmaiSwitchStatus, back

	SettingLocator () {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				approveByEmailSwitch = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/switch_aprove_email")
				back = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_arrow_back")
				break
			case "iOS":
				approveByEmailSwitch = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name=' Duyệt hồ sơ qua Email']/following-sibling::XCUIElementTypeSwitch")
				approveByEmaiSwitchStatus = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name=' Duyệt hồ sơ qua Email']/following-sibling::XCUIElementTypeSwitch/XCUIElementTypeImage")
				back = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic back']")
				break
		}
	}
}
