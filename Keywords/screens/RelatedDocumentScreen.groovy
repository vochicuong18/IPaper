package screens

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import internal.GlobalVariable
import locator.RelatedDocumentLocator

public class RelatedDocumentScreen extends RelatedDocumentLocator implements BaseKeyword{
	TestObject documentItem

	def viewInformationDocument (String documentTitle) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(documentTitle)
		}
		horizontalSwipeFromElement(documentItem(documentTitle), "right")
	}

	def searchDocument(String documentTitle) {
		inputText(searchDocument, documentTitle)
	}

	boolean isUniqueDocument (String documetName) {
	}
}
