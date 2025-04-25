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
			currentStatus = getValueAttributeOf(approveByEmaiSwitchStatus, "name")
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
