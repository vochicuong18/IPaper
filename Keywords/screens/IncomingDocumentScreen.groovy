package screens

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import internal.GlobalVariable
import locator.IncomingDocumentLocator
import utilities.Utilities


public class IncomingDocumentScreen extends IncomingDocumentLocator implements BaseKeyword {
	TestObject documentItem

	def viewInformationDocument (String documentTitle) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(documentTitle)
		}
		horizontalSwipeFromElement(documentItem(documentTitle), "right")
	}

	def viewMainFile(String title) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(title)
		}
		waitForPresentOf(documentItem(title))
		clickToElement(documentTitle(title))
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

	def clickOnSendOpinionApprove() {
		clickToElement(sendOpinionApprove)
	}

	boolean isUniqueDocument (String documetName) {
	}

	def quickApprove(String documentTitle) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(documentTitle)
		}
		clickToElement(quickApproveBtn(documentTitle))
		Thread.sleep(3000)
	}

	def quickApproveWithCondition(documentTitle) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(documentTitle)
		}
		clickToElement(approveWithCondition(documentTitle))
		clickOnSendOpinionApprove()
		Thread.sleep(3000)
	}

	def quickApproveWithCondition(documentTitle, comment) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(documentTitle)
		}
		clickToElement(approveWithCondition(documentTitle))
		fillOpinion(comment)
		clickOnSendOpinionApprove()
		Thread.sleep(3000)
	}

	def quickReject(String documentTitle, comment) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(documentTitle)
		}
		clickToElement(quickRejectBtn(documentTitle))
		fillOpinion(comment)
		clickOnSendOpinionApprove()
		Thread.sleep(3000)
	}

	def quickReject(String documentTitle) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(documentTitle)
		}
		clickToElement(quickRejectBtn(documentTitle))
		clickOnSendOpinionApprove()
		Thread.sleep(3000)
	}

	def searchDocument(String documentTitle) {
		inputText(searchDocument, documentTitle)
	}

	def fillOpinion(String opinion) {
		inputText(opinionTxt, opinion)
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
			Utilities.logInfo("Ignore this checkpoint in IOS temporary")
		}
	}


	List<Map<String, String>> getAllItemDataAndroid() {
		List<Map<String, String>> itemList = []
		for (int i = 0; i <= 10; i ++) {
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
		int screenHeight = Mobile.getDeviceHeight()
		int screenWidth = Mobile.getDeviceWidth()
		int startX = (screenWidth / 2).toInteger()
		int startY = (screenHeight * 3 / 4).toInteger()
		int endX = startX
		int endY = (screenHeight / 4).toInteger()
		int maxItems = 20
		int currentCount = 0


		while (currentCount < maxItems) {
			//			List<WebElement> cells = MobileDriverFactory.getDriver().findElements(By.xpath("//XCUIElementTypeTable/XCUIElementTypeAny"))
			//			currentCount = cells.size()
			Utilities.logInfo("---------cells: ${currentCount}")
			if (currentCount < maxItems) {
				List<WebElement> cells = MobileDriverFactory.getDriver().findElements(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell"))
				currentCount = cells.size()
				println "------------------------currentCount: ${currentCount} - MaxItem ${maxItems}"
				Mobile.swipe(startX, startY, endX, endY)
				Utilities.logInfo("--------- swiped")
			}
		}

		Utilities.logInfo("---------maxItems: ${maxItems}")

		for (int i = 1; i <= maxItems; i++) {
			String titleXpath = "//XCUIElementTypeTable//XCUIElementTypeAny[$i]//XCUIElementTypeStaticText[1]"
			String timeXpath = "//XCUIElementTypeTable//XCUIElementTypeAny[$i]//XCUIElementTypeStaticText[3]"

			Utilities.logInfo("---------titleXpath: ${titleXpath}")
			Utilities.logInfo("---------timeXpath: ${timeXpath}")

			try {
				String title = Mobile.getText(titleXpath, 5)
				String time = Mobile.getText(timeXpath, 5)
				itemList.add([title: title, time: time])
			} catch (Exception e) {
				println " "
			}
		}
		return itemList
	}
}
