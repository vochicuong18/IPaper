package screens

import java.text.SimpleDateFormat

import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import entities.Document
import internal.GlobalVariable
import locator.DraftDocumentLocator
import utilities.AIUtilities
import utilities.AssertUtilities
import utilities.Utilities

public class DraftDocumentScreen extends DraftDocumentLocator implements BaseKeyword{

	def viewInformationDocument (Document doc) {
		Utilities.logInfo("View document ${doc.getTitle()}")
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(doc.getTitle())
		}

		waitForPresentOf(documentItem(doc.getTitle()))
		horizontalSwipeFromElement(documentItem(doc.getTitle()), "right")
		waitForPresentOf(requestType)
		waitForVisibilityOf(requestType)
	}

	def searchDocument(String documentTitle) {
		waitForPresentOf(searchDocument)
		clickToElement(searchDocument)
		inputText(searchDocument, documentTitle)
		enterText(searchDocument)
	}

	def loadDataResult() {
		swipe('up', 0.5)
		waitForNotPresentOf(loadingSwipeIcon)
	}

	def backToHome() {
		int attempts = 0
		while (!isDisplayed(filterBtn) && attempts++ < 5) {
			clickToElement(backBtn)
			Thread.sleep(500)
		}
	}


	boolean checkItemInDocument () {
		if (GlobalVariable.PLATFORM == 'Android') {
			List<Map<String, String>> data = getAllItemDataAndroid()
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
		} else {
			//			getAllItemDataIOS ()
			Utilities.logInfo("Ignore this checkpoint in IOS temporary")
		}
	}


	//    [title: "Trình ký 1684937123", time: "19/05/2025 - 09:15"]

	List<Map<String, String>> getAllItemDataAndroid() {
		List<Map<String, String>> itemList = []
		for (int i = 0; i <= 1; i ++) {
			if(!isDisplayed(firstDocumentTitle)) break
				String title = getText(firstDocumentTitle)
			String time = getText(firstDocumentDate)
			scrollToAnchor(secondItem, headerBar)
			itemList.add([title: title, time: time])
			Utilities.logInfo(itemList.toString())
		}
		return itemList
	}

	def getAllItemDataIOS() {
		AIUtilities.analyzeImage("", "")
		//		List<Map<String, String>> itemList = []
		//		List<WebElement> titles = MobileDriverFactory.getDriver().findElements(By.xpath("//XCUIElementTypeStaticText[contains(@name, 'Trình ký')"))
		//		Utilities.logInfo(titles.size())
		//		for(WebElement title : titles) {
		//			Utilities.logInfo(title.getText())
		//		}
		//		return itemList
	}

	List<String> getListDocumentTitle() {
		List<String> titles = []
		for (TestObject item : titleItems()) {
			titles.add(getText(item))
		}
		return titles
	}

	def isDocumentDisplayed(Document doc) {
		searchDocument(doc.getTitle())
		waitForNotPresentOf(loadingMask)
		boolean status = isDisplayed(documentItem(doc.getTitle()))
		AssertUtilities.assertTrue(status)
	}
	
	def isDocumentNotDisplayed(Document doc) {
		searchDocument(doc.getTitle())
		waitForNotPresentOf(loadingMask)
		boolean status = isDisplayed(documentItem(doc.getTitle()))
		AssertUtilities.assertFalse(status)
	}
}
