package screens
import java.text.SimpleDateFormat

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

import base.BaseKeyword
import entities.Document
import entities.DocumentStatus
import entities.User
import internal.GlobalVariable
import locator.DocumentInformationLocator
import utilities.AssertUtilities
import utilities.Utilities

public class DocumentInformationScreen extends DocumentInformationLocator implements BaseKeyword {

	enum ActionType {
		APPROVE,
		REJECT,
		COMMENT,
		APPROVE_ADD_CC,
		WITHDRAW_DOCUMENT,
		RETURN
	}

	def performAction(Document document, ActionType actionType, String comment = "", User user = null) {
		String TO_DAY = new SimpleDateFormat('dd/MM/yyyy').format(new Date())
		waitForPresentOf(documentActionBtn)
		clickToElement(documentActionBtn)
		switch (actionType) {
			case ActionType.APPROVE:
				clickToElement(approveDocument)
				if (comment) inputText(getOpinionTxt, comment)
				clickToElement(submitApproveBtn)
				document.setStatus(DocumentStatus.APPROVED)
				document.setTime(TO_DAY)
				break

			case ActionType.REJECT:
				clickToElement(rejectDocument)
				if (comment) inputText(getOpinionTxt, comment)
				clickToElement(submitApproveBtn)
				document.setStatus(DocumentStatus.REJECT)
				document.setTime(TO_DAY)
				break

			case ActionType.COMMENT:
				clickToElement(getComment)
				if (comment) inputText(getOpinionTxt, comment)
				clickToElement(submitApproveBtn)
				document.setStatus(DocumentStatus.COMMENTED)
				document.setTime(TO_DAY)
				break

			case ActionType.APPROVE_ADD_CC:
				clickToElement(approveDocument)
				if (comment) inputText(getOpinionTxt, comment)
				inputText(ccOnPopupTxt, user.getEmail())
				if (GlobalVariable.PLATFORM == 'Android') {
					Thread.sleep(5000)
					clickToElement(submitApproveBtn) // can't click user item
				} else {
					clickToElement(ccUserItemOnPopup(user.getEmail()))
				}
				clickToElement(submitApproveBtn)
				document.setStatus(DocumentStatus.APPROVED)
				document.setTime(TO_DAY)
				break

			case ActionType.WITHDRAW_DOCUMENT:
				clickToElement(withdrawDocument)
				if (comment) inputText(getOpinionTxt, comment)
				clickToElement(submitApproveBtn)
				document.setStatus(DocumentStatus.WAIT_PROCESS)
				document.setTime(TO_DAY)
				break

			case ActionType.RETURN:
				clickToElement(returnDocument)
				if (comment) inputText(getOpinionTxt, comment)
				clickToElement(submitApproveBtn)
				document.setStatus(DocumentStatus.RETURNED)
				document.setTime(TO_DAY)
				break
		}
	}


	def backToHome() {
		int attempts = 0
		while (!isDisplayed(fillterBtn) && attempts++ < 5) {
			clickToElement(backBtn)
			Mobile.delay(0.5)
		}
	}

	def checkDocumentTitle(Document document) {
		waitForPresentOf(requestType)
		waitForVisibilityOf(requestType)
		AssertUtilities.checkEquals(getText(documentTitle), document.getTitle())
	}

	def checkSender(Document document) {
		AssertUtilities.checkEquals(getText(senderName), document.getSender().getName())
	}

	def isAssignerDisplayed() {
		AssertUtilities.assertTrue(!isDisplayed(assigner), "Check sender not displayed")
	}

	def checkStatus(Document document) {
		AssertUtilities.checkEquals(getText(status), document.getStatus().toString())
	}

	def checkCreateDate() {
		String gui = getText(createDate)
		String toDay = new SimpleDateFormat('dd/MM/yyyy').format(new Date())
		AssertUtilities.checkEquals(gui, toDay)
	}

	def checkFinishDate(Document document) {
		String gui = getText(finishDate)
		Date date = new SimpleDateFormat("d/M/yyyy").parse(document.getTime())
		String formattedData = new SimpleDateFormat("dd/MM/yyyy").format(date)
		AssertUtilities.checkEquals(gui, formattedData)
	}

	def checkPriority(Document doc) {
		AssertUtilities.checkEquals(getText(priority), doc.getPriority().toString())
	}

	def checkDescription(Document doc) {
		AssertUtilities.checkEquals(getText(description), doc.getDescription())
	}

	def checkAssigner(Document doc) {
		swipe('down')
		AssertUtilities.checkContains(getText(assigner), doc.getAssigner().getEmail())
	}

	//only check file name

	def checkPresentFileName(Document doc) {
		String gui = getText(mainFile).split('_')[0]
		String data = doc.getMainFileName().split('.pdf')[0]
		swipe('down')
		AssertUtilities.checkContains(getText(mainFile), data)
	}

	def checkAttachFileName(Document doc) {
		String data = doc.getSubFileName().replace(".pdf", "")
		swipe('down')
		boolean status = subFiles().any { getText(it).contains(data) }
		AssertUtilities.assertTrue(status)
	}


	def checkUserCannotEditDocument() {
		clickToElement(documentActionBtn)
		boolean status = isDisplayed(rePresentTxt) && !isDisplayed(submitTxt) && !isDisplayed(getOpinoionTxt) && !isDisplayed(sendApprovalTxt)
		clickToElement(documentActionBtn)
		AssertUtilities.assertTrue(status, "Check user can not edit document")
	}

	def checkComment(User user, String dataComment) {
		swipe('down', 0.5)
		boolean status = false
		List<TestObject> comments = processComments(user.getName())
		for (TestObject comment : comments) {
			String gui = getText(comment).trim().replaceAll("[\\r\\n]", "")
			Utilities.logInfo("GUI COMMENT: ${gui} - DATA COMMENT: ${dataComment}")
			if (gui.contains(dataComment)) {
				status = true
				break
			}
		}
		AssertUtilities.assertTrue(status, "Check comment of ${user.getName()} is ${dataComment}")
	}

	// APPROVE

	def approveAddCC(User user, String comment = "") {
		clickToElement(documentActionBtn)
		clickToElement(approveDocument)

		if (comment) {
			inputText(getOpinionTxt, comment)
		}

		inputText(ccOnPopupTxt, user.getEmail())

		if (GlobalVariable.PLATFORM == 'Android') {
			Thread.sleep(5000)
			clickToElement(submitApproveBtn) //Can not click on user item
		} else {
			clickToElement(ccUserItemOnPopup(user.getEmail()))
			clickToElement(submitApproveBtn)
		}
	}

	def approve(String comment = "") {
		clickToElement(documentActionBtn)
		clickToElement(approveDocument)

		if (comment) {
			inputText(getOpinionTxt, comment)
		}

		clickToElement(submitApproveBtn)
	}

	// REJECT

	def reject(String comment = "") {
		clickToElement(documentActionBtn)
		clickToElement(rejectDocument)

		if (comment) {
			inputText(getOpinionTxt, comment)
		}

		clickToElement(submitApproveBtn)
	}

	// Comment

	def getComment(String comment = "") {
		clickToElement(documentActionBtn)
		clickToElement(getComment)
		if (comment) {
			inputText(getOpinionTxt, comment)
		}
		clickToElement(submitApproveBtn)
	}


	def clickOnGetComment() {
		clickToElement(documentActionBtn)
		clickToElement(getComment)
	}

	def fillInComment(String comment) {
		inputText(getOpinionTxt, comment)
	}

	def submitAction() {
		clickToElement(submitApproveBtn)
	}

	boolean isPopupRequireCommentDisplayed () {
		boolean status = isDisplayed(warningPopup)
		AssertUtilities.assertTrue(status)
	}

	def ignoreWarningPopup () {
		clickToElement(ignoreBtn)
	}

	def waitStatusChangeTo(DocumentStatus docStatus) {
		int timeoutInSeconds = 300
		long startTime = System.currentTimeMillis()
		while ((System.currentTimeMillis() - startTime) < timeoutInSeconds * 1000) {
			swipe('up', 0.5) // swipe to load new data
			if (getText(status).equals(docStatus)) {
				long elapsedTime = (System.currentTimeMillis() - startTime) / 1000
				KeywordUtil.logInfo("Status change to ${docStatus.toString()} in ${elapsedTime} seconds.")
				return
			}
			Mobile.delay(10)
		}
		KeywordUtil.markFailed("Status not change within ${timeoutInSeconds} seconds.")
	}
}
