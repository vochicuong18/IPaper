package screens

import java.text.SimpleDateFormat

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

import base.BaseKeyword
import entities.Document
import entities.DocumentStatus
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

	def viewInformationDocument (Document doc) {
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(doc.getTitle())
		}
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

	def clickOnSendOpinionApprove() {
		clickToElement(sendOpinionApprove)
	}

	boolean isUniqueDocument (String documetName) {
	}

	def performAction(Document document, ActionType action, String comment = null) {
		String TO_DAY = new SimpleDateFormat('dd/MM/yyyy').format(new Date())
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(document.getTitle())
		}

		switch (action) {
			case ActionType.QUICK_APPROVE:
				clickToElement(quickApproveBtn(document.getTitle()))
				waitForNotPresentOf(quickApproveBtn(document.getTitle()))
				document.setStatus(DocumentStatus.APPROVED)
				document.setTime(TO_DAY)
				break

			case ActionType.APPROVE_WITH_CONDITION:
				clickToElement(approveWithCondition(document.getTitle()))
				if (comment) fillOpinion(comment)
				clickOnSendOpinionApprove()
				waitForNotPresentOf(approveWithCondition(document.getTitle()))
				document.setStatus(DocumentStatus.APPROVED)
				document.setTime(TO_DAY)
				break

			case ActionType.REJECT:
				clickToElement(quickRejectBtn(document.getTitle()))
				if (comment) fillOpinion(comment)
				clickOnSendOpinionApprove()
				waitForNotPresentOf(quickRejectBtn(document.getTitle()))
				document.setStatus(DocumentStatus.REJECT)
				document.setTime(TO_DAY)
				break

			case ActionType.SEND_COMMENT:
				clickToElement(sendCommentBtn(document.getTitle()))
				if (comment) fillOpinion(comment)
				clickOnSendOpinionApprove()
				waitForNotPresentOf(sendCommentBtn(document.getTitle()))
				document.setStatus(DocumentStatus.COMMENTED)
				document.setTime(TO_DAY)
				break
		}
		backToHome()
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
		List<Map<String, String>> itemList = []
		List<WebElement> titles = MobileDriverFactory.getDriver().findElements(By.xpath("//XCUIElementTypeStaticText[contains(@name, 'Trình ký')"))
		Utilities.logInfo(titles.size())
		for(WebElement title : titles) {
			Utilities.logInfo(title.getText())
		}
		return itemList
	}

	def waitForDocument(Document document, int timeoutInSeconds = 300) {
		String title = document.getTitle()
		long endTime = System.currentTimeMillis() + timeoutInSeconds * 1000
		while (System.currentTimeMillis() < endTime) {
			GlobalVariable.PLATFORM == 'iOS' ? searchDocument(title) : swipe('up', 0.5) //how to document show

			if (isDisplayed(documentItem(title))) {
				long elapsed = (System.currentTimeMillis() + timeoutInSeconds * 1000 - endTime) / 1000
				KeywordUtil.logInfo("✅ Found '${title}' in ${timeoutInSeconds - (endTime - System.currentTimeMillis()) / 1000} seconds.")
				return
			}
			Mobile.delay(15)
		}
		KeywordUtil.markFailedAndStop("'${title}' not found within ${timeoutInSeconds} seconds.")
	}

	def backToHome() {
		int attempts = 0
		while (!isDisplayed(fillterBtn) && attempts++ < 5) {
			clickToElement(backBtn)
			Mobile.delay(0.5)
		}
	}
}
