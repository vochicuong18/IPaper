package base

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable

public class BaseApp {

	def pressBack() {
		if (GlobalVariable.PLATFORM == "Android") {
			Mobile.pressBack()
		} else if (GlobalVariable.PLATFORM == "iOS") {
			int height = Mobile.getDeviceHeight()
			int width = Mobile.getDeviceWidth()
			int startX = (int)(width * 0.05)
			int endX = (int)(width * 0.9)
			int y = (int)(height * 0.5)
			Mobile.swipe(startX, y, endX, y)
		}
	}

	def waitAppLauch () {
		//		TestObject logo = new TestObject()
		//		if (GlobalVariable.PLATFORM == "Android") {
		//			logo.addProperty(LocatorType.ID.toString(), EQUALS, "com.hdbank.ipaper:id/img_logo")
		//		} else {
		//			logo.addProperty(LocatorType.XPATH.toString(), EQUALS, "//XCUIElementTypeToolbar[@name='Toolbar']")
		//		}
		//		waitForPresentOf(logo)
	}
}
