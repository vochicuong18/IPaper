package screens

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import base.BaseKeyword
import internal.GlobalVariable
import utilities.AssertUtilities

public class ProfileScreen extends BaseKeyword{
	def checkUserEmail(String email) {
		String gui = getText(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/ProfileScreen/email"))
		AssertUtilities.checkEquals(gui, email)
	}

	def checkPhoneNumber (String phoneNumber) {
		String gui = getText(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/ProfileScreen/phoneNumber"))
		AssertUtilities.checkEquals(gui, phoneNumber)
	}

	def back() {
		clickToElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/ProfileScreen/backButton"))
	}
}
