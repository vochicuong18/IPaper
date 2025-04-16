package screens

import base.BaseKeyword
import internal.GlobalVariable
import locator.HomeScreenLocator
public class HomeScreen extends HomeScreenLocator implements BaseKeyword {

	def goToProfileInfo() {
		waitForVisibilityOf(avatar)
		clickToElement(avatar)
	}

	def goToIncomingDocument() {
		if(!isInComingDocumentScreen()) {
			expandMenu()
			clickToElement(inComingDocument)
		}
	}

	def goToOutComingDocument() {
		if (!isOutComingDocumentScreen()) {
			expandMenu()
			clickToElement(outComingDocument)
		}
	}

	def goToRelatedDocument() {
		expandMenu()
		clickToElement(outComingDocument)
	}

	def logout() {
		expandMenu()
		clickToElement(logoutButton)
		waitForNotPresentOf(loadingMask)
	}

	def expandMenu() {
		if(GlobalVariable.PLATFORM == "iOS") {
			if (!isDisplayed(closeTabBtn)) {
				clickToElement(menuIcon)
			}
		}

		else  {
			if (!isDisplayed(menuTab)) {
				waitForVisibilityOf(menuIcon)
				clickToElement(menuIcon)
			}
		}
	}

	// TAB MENU

	def openRequestList() {
		expandMenu()
		clickToElement(createRequestItems)
	}

	def createRequest(String requestName) {
		def item = requestItem(requestName)
		waitForVisibilityOf(item)
		Thread.sleep(300)
		clickToElement(item)
	}

	//--------------------------

	def waitLoadingMask() {
		waitForInVisibilityOf(loadingMask)
	}

	boolean isInComingDocumentScreen() {
		getText(screenTitle) == "Hồ sơ đến"
	}
	boolean isOutComingDocumentScreen() {
		return getText(screenTitle)== "Hồ sơ đi"
	}
	boolean isRelatedDocumentScreen() {}
}
