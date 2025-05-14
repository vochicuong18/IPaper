package screens

import base.BaseKeyword
import entities.User
import io.qameta.allure.Step
import locator.LoginLocator

public class LoginScreen extends LoginLocator implements BaseKeyword {
	def login(User user) {
		if (isGuest()) {
			fillInUserName(user.getUserName())
			fillInPassword(user.getPassword())
			clickOnLogin()
		}
	}


	def fillInUserName(String userName) {
		waitForVisibilityOf(userNameTxt)
		inputText(userNameTxt, userName)
	}

	def fillInPassword(String password) {
		inputText(passwordTxt, password)
	}

	def clickOnLogin() {
		clickToElement(title)
		clickToElement(loginButton)
		waitForNotPresentOf(loadingMask)
	}

	boolean isGuest() {
		return isDisplayed(userNameTxt)
	}

	boolean isUpdated() {
		return isDisplayed(ignoreUpdate, 3)
	}
}
