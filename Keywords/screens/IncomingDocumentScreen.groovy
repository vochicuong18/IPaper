package screens

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

	enum ActionType {
		QUICK_APPROVE,
		APPROVE_WITH_CONDITION,
		REJECT,
		SEND_COMMENT
	}

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

	def performAction(String documentTitle, ActionType action, String comment = null) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(documentTitle)
		}

		switch (action) {
			case ActionType.QUICK_APPROVE:
				clickToElement(quickApproveBtn(documentTitle))
				waitForNotPresentOf(quickApproveBtn(documentTitle))
				break

			case ActionType.APPROVE_WITH_CONDITION:
				clickToElement(approveWithCondition(documentTitle))
				if (comment != null) {
					fillOpinion(comment)
				}
				clickOnSendOpinionApprove()
				waitForNotPresentOf(approveWithCondition(documentTitle))
				break

			case ActionType.REJECT:
				clickToElement(quickRejectBtn(documentTitle))
				if (comment != null) {
					fillOpinion(comment)
				}
				clickOnSendOpinionApprove()
				waitForNotPresentOf(quickRejectBtn(documentTitle))
				break

			case ActionType.SEND_COMMENT:
				clickToElement(sendCommentBtn(documentTitle))
				if (comment != null) {
					fillOpinion(comment)
				}
				clickOnSendOpinionApprove()
				waitForNotPresentOf(sendCommentBtn(documentTitle))
				break
		}
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
			getAllItemDataIOS ()
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
		List<WebElement> titles = MobileDriverFactory.getDriver().findElements(By.xpath("//XCUIElementTypeStaticText[contains(@name, 'Trình ký')"))
		Utilities.logInfo(titles.size())
		for(WebElement title : titles) {
			Utilities.logInfo(title.getText())
		}
		return itemList
	}
}
