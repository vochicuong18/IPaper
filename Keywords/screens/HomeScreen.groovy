package screens

import org.openqa.selenium.StaleElementReferenceException

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
			waitForNotPresentOf(loadingItem)
		}
	}

	def goToOutComingDocument() {
		if (!isOutComingDocumentScreen()) {
			expandMenu()
			clickToElement(outComingDocument)
			waitForNotPresentOf(loadingItem)
		}
	}

	def goToDraftDocument() {
		if (!isDraftDocumentScreen()) {
			expandMenu()
			clickToElement(draftDocument)
			waitForNotPresentOf(loadingItem)
		}
	}

	def goToRelatedDocument() {
		clickOnFillter()
		clickOnRelatedDocument()
		waitForNotPresentOf(loadingItem)
	}


	def clickOnRelatedDocument() {
		clickToElement(relatedDocumentBtn)
	}

	def clickOnFillter () {
		clickToElement(fillterBtn)
	}

	def goToSetting() {
		expandMenu()
		clickToElement(setting)
	}

	def logout() {
		expandMenu()
		clickToElement(logoutButton)
		waitForNotPresentOf(loadingMask)
		waitForPresentOf(logo)
	}

	def expandMenu() {
		if(GlobalVariable.PLATFORM == "iOS") {
			if (!isDisplayed(closeTabBtn)) {
				waitForPresentOf(menuIcon)
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
		if (GlobalVariable.PLATFORM == 'iOS') {
			clickToElement(addDocumentIcon)
		} else {
			expandMenu()
			clickToElement(createRequestItems)
		}
	}

	def createRequest(String requestName) {
		try {
			waitForPresentOf(listFormSample)
			waitForPresentOf(requestItem(requestName))
			waitForVisibilityOf(requestItem(requestName))
			clickToElement(requestItem(requestName))
		} catch(StaleElementReferenceException ex) {
			clickToElement(requestItem(requestName))
		}
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

	boolean isDraftDocumentScreen() {
		return getText(screenTitle)== "Hồ sơ nháp"
	}

	boolean isRelatedDocumentScreen() {}
}
