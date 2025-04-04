package screens

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject

import base.BaseApp
import internal.GlobalVariable

public class FileBrowserScreen extends BaseApp {

	private TestObject searchBtn
	private TestObject searchTxt
	private TestObject fileLbl, submitSelectedFile

	public FileBrowserScreen() {
		String platformPath = "Object Repository/Elements/${GlobalVariable.PLATFORM}/FileBrowserScreen/"
		searchBtn = findTestObject("${platformPath}searchBtn")
		searchTxt = findTestObject("${platformPath}searchTxt")
		fileLbl = findTestObject("${platformPath}fileLbl")
		submitSelectedFile = findTestObject("${platformPath}submitSelectedFile")
	}

	def attachFile(String fileName) {
		if (GlobalVariable.PLATFORM == "Android") {
			clickToSearch()
		} else {
			inputSearch(fileName)
			selectFile(fileName)
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
		def fileItem = findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/FileBrowserScreen/fileLbl", [('fileName') : fileName])
		clickToElement(fileItem)
	}
}