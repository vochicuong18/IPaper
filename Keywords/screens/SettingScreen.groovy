package screens

import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import base.BaseKeyword
import locator.SettingLocator

public class SettingScreen extends SettingLocator implements BaseKeyword {
	def enableApproveByEmail(boolean status) {
		if(getValueAttributeOf(approveByEmailSwitch, "checked") != status ) {
			clickToElement(approveByEmailSwitch)
		}
	}

	def backToHome () {
		clickToElement(back)
	}
}
