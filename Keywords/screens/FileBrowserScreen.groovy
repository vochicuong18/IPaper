package screens

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import internal.GlobalVariable
import locator.FileBrowserLocator

public class FileBrowserScreen extends FileBrowserLocator implements BaseKeyword {

	def attachFile(String fileName) {
		if (GlobalVariable.PLATFORM == "Android") {
			clickToSearch()
		}
		inputSearch(fileName)
		Thread.sleep(1000) //wait system find file
		selectFile(fileName)
		if (GlobalVariable.PLATFORM == "iOS" && isDisplayed(submitSelectedFile)) {
			clickToElement(submitSelectedFile)
		}
	}

	def clickToSearch() {
		waitForVisibilityOf(searchBtn)
		clickToElement(searchBtn)
	}

	def inputSearch(String fileName) {
		inputText(searchTxt, fileName)
	}


	def selectFile(String fileName) {
		def fileItem = fileLbl(fileName)
		clickToElement(fileItem)
	}
}