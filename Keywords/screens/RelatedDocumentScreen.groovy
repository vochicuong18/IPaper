package screens

import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import entities.Document
import internal.GlobalVariable
import locator.RelatedDocumentLocator

public class RelatedDocumentScreen extends RelatedDocumentLocator implements BaseKeyword{
	TestObject documentItem

	def viewInformationDocument (Document doc) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(doc.getTitle())
		}
		horizontalSwipeFromElement(documentItem(doc.getTitle()), "right")
	}

	def searchDocument(String documentTitle) {
		inputText(searchDocument, documentTitle)
	}

	boolean isUniqueDocument (String documetName) {
	}
}
