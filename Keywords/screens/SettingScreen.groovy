package screens

import base.BaseKeyword
import internal.GlobalVariable
import locator.SettingLocator

public class SettingScreen extends SettingLocator implements BaseKeyword {
	def enableApproveByEmail(boolean expectedStatus) {
		boolean currentStatus

		if (GlobalVariable.PLATFORM == 'Android') {
			currentStatus = getValueAttributeOf(approveByEmailSwitch, "checked")
		} else {
			String expectedName = expectedStatus ? "setting_check_ic" : "setting_off"
			currentStatus = getValueAttributeOf(approveByEmaiSwitchStatus, "name") == expectedName
		}

		if (currentStatus != expectedStatus) {
			clickToElement(approveByEmailSwitch)
		}
	}
	
	def enablReceivedNotification () {
		
	}

	def backToHome () {
		clickToElement(back)
	}
}
