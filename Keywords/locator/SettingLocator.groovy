package locator

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

public class SettingLocator extends TestObjectFactory{
	TestObject approveByEmailSwitch, approveByEmaiSwitchStatus, back, olderDocumentSwtich, editFileAttachSwitch, notificationSwtich

	SettingLocator () {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				approveByEmailSwitch = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/switch_aprove_email")
				olderDocumentSwtich = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/switch_archive")
				editFileAttachSwitch = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/switch_edit_mode_document")
				notificationSwtich = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/switch_notification")
				back = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_arrow_back")

				break
			case "iOS":
				approveByEmailSwitch = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name=' Duyệt hồ sơ qua Email']/following-sibling::XCUIElementTypeSwitch")
				approveByEmaiSwitchStatus = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name=' Duyệt hồ sơ qua Email']/following-sibling::XCUIElementTypeSwitch/XCUIElementTypeImage")
				olderDocumentSwtich = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Tìm kiếm hồ sơ cũ']//following-sibling::XCUIElementTypeSwitch")
				editFileAttachSwitch = createTestObject(LocatorType.XPATH, "")
				notificationSwtich = createTestObject(LocatorType.XPATH, "")
				back = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic back']")
				break
		}
	}
}
