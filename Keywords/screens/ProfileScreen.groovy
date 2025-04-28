package screens

import base.BaseKeyword
import locator.ProfileLocator
import utilities.AssertUtilities

public class ProfileScreen extends ProfileLocator implements BaseKeyword {
	def checkUserEmail(String emailData) {
		String gui = getText(email)
		AssertUtilities.checkEquals(gui, emailData)
	}

	def checkUserName(String userNameData) {
		String gui = getText(userName)
		AssertUtilities.checkEquals(gui, userNameData)
	}

	def checkName(String nameData) {
		String gui = getText(name)
		AssertUtilities.checkEquals(gui, nameData)
	}

	def checkPhoneNumber (String phoneNumberData) {
		String gui = getText(phoneNumber)
		AssertUtilities.checkEquals(gui, phoneNumberData)
	}

	def back() {
		clickToElement(backBtn)
	}
}
