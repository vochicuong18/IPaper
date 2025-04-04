package screens

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import base.BaseKeyword
import internal.GlobalVariable

public class LoginScreen extends BaseKeyword{

	def login(String userName, String password) {
		if (isGuest()) {
			fillInUserName(userName)
			fillInPassword(password)
			clickOnLogin()
		}
	}

	def fillInUserName(String userName) {
		waitForVisibilityOf(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/LoginScreen/userNameTxt"))
		inputText(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/LoginScreen/userNameTxt"), userName)
	}

	def fillInPassword(String password) {
		inputText(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/LoginScreen/passwordTxt"), password)
	}

	def clickOnLogin() {
		clickToElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/LoginScreen/title")) // hidden keyboard
		clickToElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/LoginScreen/loginButton"))
		waitForNotPresentOf(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/LoginScreen/loadingMask"))
	}

	boolean isGuest() {
		return isDisplayed(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/LoginScreen/userNameTxt"))
	}
}
