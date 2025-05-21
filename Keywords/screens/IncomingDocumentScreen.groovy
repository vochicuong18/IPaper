package screens

import java.lang.annotation.Documented
import java.text.SimpleDateFormat

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

import base.BaseKeyword
import ch.qos.logback.core.joran.conditional.ElseAction
import entities.Document
import entities.DocumentStatus
import internal.GlobalVariable
import locator.IncomingDocumentLocator
import utilities.AssertUtilities
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
		Utilities.logInfo("View document ${doc.getTitle()}")
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(doc.getTitle())
		}

		waitForPresentOf(documentItem(doc.getTitle()))
		horizontalSwipeFromElement(documentItem(doc.getTitle()), "right")
		waitForPresentOf(requestType)
		waitForVisibilityOf(requestType)
	}

	def viewMainFile(Document doc) {
		Utilities.logInfo("View main file document: ${doc.getTitle()}")
		if (GlobalVariable.PLATFORM == 'iOS') {
			searchDocument(doc.getTitle())
		}
		waitForPresentOf(documentItem(doc.getTitle()))
		clickToElement(documentItem(doc.getTitle()))
		waitForNotPresentOf(imageViewLoading)
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
				waitForPresentOf(quickApproveBtn(document.getTitle()))
				clickToElement(quickApproveBtn(document.getTitle()))
				waitForNotPresentOf(quickApproveBtn(document.getTitle()))
				document.setStatus(DocumentStatus.APPROVED)
				break

			case ActionType.APPROVE_WITH_CONDITION:
				waitForPresentOf(approveWithCondition(document.getTitle()))
				clickToElement(approveWithCondition(document.getTitle()))
				if (comment) fillOpinion(comment)
				clickOnSendOpinionApprove()
				waitForNotPresentOf(approveWithCondition(document.getTitle()))
				document.setStatus(DocumentStatus.APPROVED)
				break

			case ActionType.REJECT:
				waitForPresentOf(quickRejectBtn(document.getTitle()))
				clickToElement(quickRejectBtn(document.getTitle()))
				if (comment) fillOpinion(comment)
				clickOnSendOpinionApprove()
				waitForNotPresentOf(quickRejectBtn(document.getTitle()))
				document.setStatus(DocumentStatus.REJECT)
				break

			case ActionType.SEND_COMMENT:
				waitForPresentOf(sendCommentBtn(document.getTitle()))
				clickToElement(sendCommentBtn(document.getTitle()))
				if (comment) {
					fillOpinion(comment)
					clickOnSendOpinionApprove()
				} else {
					clickOnSendOpinionApprove()
					clickToElement(submitWarningPopup)
					fillOpinion(comment)
					clickOnSendOpinionApprove()
				}
				waitForNotPresentOf(sendCommentBtn(document.getTitle()))
				document.setStatus(DocumentStatus.COMMENTED)
				break
		}
		backToHome()
	}

	def searchDocument(String documentTitle) {
		waitForPresentOf(searchDocument)
		clickToElement(searchDocument)
		inputText(searchDocument, documentTitle)
		enterText(searchDocument)
	}

	def fillOpinion(String opinion) {
		inputText(opinionTxt, opinion)
	}

	boolean checkItemInDocument () {
		//		if (GlobalVariable.PLATFORM == 'Android') {
		//			List<Map<String, String>> data = getAllItemDataAndroid()
		//			Set<String> seenTitles = new HashSet<>()
		//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm")
		//			boolean isTitleUnique = true
		//			boolean isTimeSorted = true
		//
		//			Date prevTime = null
		//			for (int i = 0; i < data.size(); i++) {
		//				String title = data[i].title
		//				String timeStr = data[i].time
		//
		//				if (seenTitles.contains(title)) {
		//					Utilities.logInfo("Title is duplicated -  index $i: '$title'")
		//					isTitleUnique = false
		//				} else {
		//					seenTitles.add(title)
		//				}
		//
		//				if (timeStr == "-") continue
		//
		//					Date currentTime = sdf.parse(timeStr)
		//				if (prevTime != null && prevTime.before(currentTime)) {
		//					Utilities.logInfo("Time is wrong -  index $i")
		//					Utilities.logInfo("   ➤ Trước:  ${data[i-1].title} - ${data[i-1].time}")
		//					Utilities.logInfo("   ➤ Sau:    ${data[i].title} - $timeStr")
		//					isTimeSorted = false
		//				}
		//				prevTime = currentTime
		//			}
		//
		//			if (isTitleUnique) {
		//				Utilities.logInfo("Title is not duplicated")
		//			}
		//			if (isTimeSorted) {
		//				Utilities.logInfo("Time is correctly")
		//			}
		//		} else {
		//			//			getAllItemDataIOS ()
		//			Utilities.logInfo("Ignore this checkpoint in IOS temporary")
		//		}
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

	List<String> getListDocumentTitle() {
		List<String> titles = []
		for (TestObject item : titleItems()) {
			titles.add(getText(item))
		}
		return titles
	}

	/**
	 * Gets a random document title from the list of available document titles.
	 * @return String A random document title, or null if no documents are available
	 */
	String getRandomDocumentTitle() {
		List<String> titles = getListDocumentTitle()
		Random random = new Random()
		int randomIndex = random.nextInt(titles.size())
		String selectedTitle = titles[randomIndex]
		return selectedTitle
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
			Thread.sleep(15)
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

	def filterByStatus (DocumentStatus status) {
		clickToElement(fillterBtn)
		clickToElement(filterDocumentStatusTab)
		clickToElement(documentStatusFilter(status.toString()))
		waitForNotPresentOf(loadingItem)
		Utilities.logInfo("Filter by ${status.toString()} status")
	}

	def filterByStatus (String status) {
		clickToElement(fillterBtn)
		clickToElement(filterDocumentStatusTab)
		clickToElement(documentStatusFilter(status))
		waitForNotPresentOf(loadingItem)
		Utilities.logInfo("Filter by ${status.toString()} status")
	}

	def isDocumentDisplayed(Document document) {
		boolean status = isDisplayed(documentItem(document.getTitle()))
		AssertUtilities.assertTrue(status, "Check ${document.getTitle()} is displayed")
	}

	def loadDataResult() {
		swipe('up', 0.5)
		waitForNotPresentOf(loadingSwipeIcon)
	}
}
