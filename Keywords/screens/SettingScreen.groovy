package screens

import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import internal.GlobalVariable
import locator.SettingLocator

public class SettingScreen extends SettingLocator implements BaseKeyword {
	def enableApproveByEmail(boolean expectedStatus) {
		boolean currentStatus
		if (GlobalVariable.PLATFORM == 'Android') {
			currentStatus = getValueAttributeOf(approveByEmailSwitch, "checked") == "true"
		} else {
//			currentStatus = getValueAttributeOf(approveByEmaiSwitchStatus, "name") 
		}

		if (currentStatus != expectedStatus) {
			clickToElement(approveByEmailSwitch)
		}
	}

	def enablReceivedNotification (boolean expectedStatus) {
		boolean currentStatus
		if(GlobalVariable.PLATFORM == 'Android') {
			currentStatus = getValueAttributeOf(notificationSwtich, "checked") == "true"
		} else {
			currentStatus = getValueAttributeOf(notificationSwtich, "value") == "1"
		}
		if (currentStatus != expectedStatus) {
			clickToElement(notificationSwtich)
			Thread.sleep(500) // wait switch animation
		}
	}

	def enableShowDocumentOlder(boolean expectedStatus) {
		boolean currentStatus
		if(GlobalVariable.PLATFORM == 'Android') {
			currentStatus = getValueAttributeOf(olderDocumentSwtich, "checked") == "true"
		} else {
			currentStatus = getValueAttributeOf(olderDocumentSwtich, "value") == "1"
		}
		if (currentStatus != expectedStatus) {
			clickToElement(olderDocumentSwtich)
			Thread.sleep(500) // wait switch animation
		}
	}

	def enableEditFileAttach(boolean expectedStatus) {
		boolean currentStatus
		if(GlobalVariable.PLATFORM == 'Android') {
			currentStatus = getValueAttributeOf(editFileAttachSwitch, "checked") == "true"
		} else {
			currentStatus = getValueAttributeOf(editFileAttachSwitch, "value") == "1"
		}
		if (currentStatus != expectedStatus) {
			clickToElement(editFileAttachSwitch)
			Thread.sleep(500) // wait switch animation
		}
	}

	def backToHome () {
		clickToElement(back)
	}
	
	
//	private boolean getSwitchStatus(TestObject switchObject) {
//		if(GlobalVariable.PLATFORM == 'Android') {
//			return getValueAttributeOf(switchObject, "checked") == "true"
//		} else {
//			return getValueAttributeOf(switchObject, "value") == "1"
//		}
//	}
//	
//	// Generic toggle method
//	private void toggleSwitch(TestObject switchObject, boolean expectedStatus) {
//		boolean currentStatus = getSwitchStatus(switchObject)
//		
//		if (currentStatus != expectedStatus) {
//			clickToElement(switchObject)
//			Thread.sleep(500) // wait for switch animation
//		}
//	}
}
