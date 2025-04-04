package screens

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject

import base.BaseApp
import internal.GlobalVariable

public class IncomingDocumentScreen extends BaseApp {
	TestObject documentItem

	def viewInformationDocument (String documentTitle) {
		def documentItem = findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/IncomingDocumentScreen/documentItem", [('documentTitle') : documentTitle])
		horizontalSwipeFromElement(documentItem, "right")
	}
}
