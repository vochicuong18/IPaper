package screens

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject

import base.BaseApp
import internal.GlobalVariable
import utilities.AssertUtilities

public class DocumentInformationScreen extends BaseApp {

	TestObject attachFile, createDate, documentTitle, finishDate, presentFile, priority, senderName, desc

	public DocumentInformationScreen() {
		String platformPath = "Object Repository/Elements/${GlobalVariable.PLATFORM}/DocumentInformationScreen/"
		attachFile = findTestObject("${platformPath}attachFile")
		createDate = findTestObject("${platformPath}createDate")
		documentTitle = findTestObject("${platformPath}documentTitle")
		finishDate = findTestObject("${platformPath}finishDate")
		presentFile = findTestObject("${platformPath}presentFile")
		priority = findTestObject("${platformPath}priority")
		senderName = findTestObject("${platformPath}senderName")
		desc = findTestObject("${platformPath}description")
	}

	def checkDocumentTitle(String data) {
		AssertUtilities.checkEquals(getText(documentTitle), data)
	}

	def checkSender(String data) {
		AssertUtilities.checkEquals(getText(senderName), data)
	}

	def checkCreateDate(String data) {
		AssertUtilities.checkEquals(getText(createDate), data)
	}

	def checkFinishDate(String data) {
		AssertUtilities.checkEquals(getText(finishDate), data)
	}

	def checkPriority(String data) {
		AssertUtilities.checkEquals(getText(priority), data)
	}

	def checkDescription(String data) {
		AssertUtilities.checkEquals(getText(desc), data)
	}

	def checkPresentFileName(String data) {
		AssertUtilities.checkEquals(getText(presentFile), data)
	}

	def checkAttachFileName(String data) {
		AssertUtilities.checkEquals(getText(attachFile), data)
	}
}
