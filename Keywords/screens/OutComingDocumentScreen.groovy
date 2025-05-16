package screens


import java.text.SimpleDateFormat

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import entities.Document
import internal.GlobalVariable
import locator.OutComingDocumentLocator
import utilities.Utilities

public class OutComingDocumentScreen extends OutComingDocumentLocator implements BaseKeyword{
	TestObject documentItem

	def viewInformationDocument (Document doc) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(doc.getTitle())
		}
		waitForPresentOf(documentItem(doc.getTitle()))
		horizontalSwipeFromElement(documentItem(doc.getTitle()), "right")
	}

	def viewMainFile(Document doc) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(doc.getTitle())
		}
		waitForPresentOf(documentItem(doc.getTitle()))
		clickToElement(documentItem(doc.getTitle()))
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
		waitForPresentOf(searchDocument)
		clickToElement(searchDocument)
		inputText(searchDocument, documentTitle)
	}

	boolean checkItemInDocument() {
//		if (GlobalVariable.PLATFORM != 'Android') {
//			getAllItemDataIOS()
//			return true
//		}
//		List<Map<String, String>> data = getAllItemData()
//		if (data.isEmpty()) {
//			Utilities.logInfo("Do not have document in screens")
//			return false
//		}
//		Set<String> seenTitles = new HashSet<>()
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm")
//		boolean isTitleUnique = true
//		boolean isTimeSorted = true
//		Date prevTime = null
//		data.eachWithIndex { item, i ->
//			String title = item.title
//			String timeStr = item.time
//			if (!seenTitles.add(title)) {
//				Utilities.logInfo("Title is duplicated -  index $i: '$title'")
//				isTitleUnique = false
//			}
//			if (timeStr == "-") return
//				Date currentTime = sdf.parse(timeStr)
//			if (prevTime && prevTime.before(currentTime)) {
//				Utilities.logInfo("Time is wrong -  index $i")
//				Utilities.logInfo("   ➤ Trước:  ${data[i-1].title} - ${data[i-1].time}")
//				Utilities.logInfo("   ➤ Sau:    ${item.title} - $timeStr")
//				isTimeSorted = false
//			}
//			prevTime = currentTime
//		}
//		if (isTitleUnique) Utilities.logPass("Title is not duplicated")
//		if (isTimeSorted) Utilities.logPass("Time is correctly")
//		return isTitleUnique && isTimeSorted
	}



	List<Map<String, String>> getAllItemData() {
		List<Map<String, String>> itemList = []
		for (int i = 0; i <= 10; i ++) {
			if(isDisplayed(firstDocumentTitle)) break
				String title = getText(firstDocumentTitle)
			String time = getText(firstDocumentDate)
			scrollToAnchor(secondItem, headerBar)
			itemList.add([title: title, time: time])
			Utilities.logInfo(itemList.toString())
		}
		return itemList
	}

	def getAllItemDataIOS() {
		List<Map<String, String>> itemList = []
		Mobile.delay(10)
		List<WebElement> titles = MobileDriverFactory.getDriver().findElements(By.xpath("//XCUIElementTypeStaticText[contains(@name, 'Trình ký')]"))
		println "--------------------------------------------"

		println "------------------- ${titles.size()} -------------------------"
		println "--------------------------------------------"
		println "--------------------------------------------"
		for(WebElement title : titles) {
			Utilities.logInfo(title.getText())
		}
		return itemList
	}
}
