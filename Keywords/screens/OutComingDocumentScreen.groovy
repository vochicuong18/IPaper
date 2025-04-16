package screens


import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import internal.GlobalVariable
import locator.OutComingDocumentLocator
import utilities.Utilities

public class OutComingDocumentScreen extends OutComingDocumentLocator implements BaseKeyword{
	TestObject documentItem

	def viewInformationDocument (String documentTitle) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(documentTitle)
		}
		horizontalSwipeFromElement(documentItem(documentTitle), "right")
	}

	def goToRelatedDocument() {
		clickOnFillter()
		clickOnRelatedDocument()
	}


	def clickOnRelatedDocument() {
		clickToElement(relatedDocumentBtn)
	}

	def clickOnFillter () {
		clickToElement(fillterBtn)
	}

	boolean isUniqueDocument (String documetName) {
	}

	def searchDocument(String documentTitle) {
		inputText(searchDocument, documentTitle)
	}

	boolean checkItemInDocument () {
		List<Map<String, String>> data = getAllItemData()
		Set<String> seenTitles = new HashSet<>()
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm")

		boolean isTitleUnique = true
		boolean isTimeSorted = true

		Date prevTime = null
		for (int i = 0; i < data.size(); i++) {
			String title = data[i].title
			String timeStr = data[i].time

			if (seenTitles.contains(title)) {
				Utilities.logInfo("Title is duplicated -  index $i: '$title'")
				isTitleUnique = false
			} else {
				seenTitles.add(title)
			}

			if (timeStr == "-") continue

				Date currentTime = sdf.parse(timeStr)
			if (prevTime != null && prevTime.before(currentTime)) {
				Utilities.logInfo("Time is wrong -  index $i")
				Utilities.logInfo("   ➤ Trước:  ${data[i-1].title} - ${data[i-1].time}")
				Utilities.logInfo("   ➤ Sau:    ${data[i].title} - $timeStr")
				isTimeSorted = false
			}
			prevTime = currentTime
		}

		if (isTitleUnique) {
			Utilities.logInfo("Title is not duplicated")
		}
		if (isTimeSorted) {
			Utilities.logInfo("Time is correctly")
		}
	}


	List<Map<String, String>> getAllItemData() {
		//		TestObject documentItem = documentItem(1)
		//		TestObject documentItem1 = documentItem(2)
		List<Map<String, String>> itemList = []
		for (int i = 0; i <= 10; i ++) {
			String title = getText(firstDocumentTitle)
			String time = getText(firstDocumentDate)
			scrollToAnchor(documentItem(2), headerBar)
			itemList.add([title: title, time: time])
			Utilities.logInfo(itemList.toString())
		}
		return itemList
	}
}
