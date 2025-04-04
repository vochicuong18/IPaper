package screens

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import base.BaseApp
import internal.GlobalVariable

public class HomeScreen extends BaseApp {

	def goToProfileInfo() {
		waitForVisibilityOf(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/HomeScreen/avatar"))
		clickToElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/HomeScreen/avatar"))
	}

	def logout() {
		expandMenu()
		clickToElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/HomeScreen/logoutButton"))
		waitForVisibilityOf(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/LoginScreen/title"))
	}

	def expandMenu() {
		//		waitLoadingMask()
		if(GlobalVariable.PLATFORM == "iOS") {
			if (!isDisplayed(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/HomeScreen/closeTabBtn"))) {
				//				waitForVisibilityOf(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/HomeScreen/menuIcon"))
				clickToElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/HomeScreen/menuIcon"))
			}
		}

		else  {
			if (!isDisplayed(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/HomeScreen/menuTab"))) {
				waitForVisibilityOf(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/HomeScreen/menuIcon"))
				clickToElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/HomeScreen/menuIcon"))
			}
		}
	}

	def openRequestList() {
		expandMenu()
		clickToElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/HomeScreen/createRequestItems"))
	}

	def createRequest(String requestName) {
		def item = findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/HomeScreen/requestItem", [('requestName') : requestName])
		waitForVisibilityOf(item)
		Thread.sleep(300)
		clickToElement(item)
	}

	def waitLoadingMask() {
		//ios //XCUIElementTypeImage[@name="placeholder_loading_list_doc"]
		waitForInVisibilityOf(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/HomeScreen/loadingMask"))
	}
}
