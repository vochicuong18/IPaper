package screens
import java.text.SimpleDateFormat

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

import base.BaseKeyword
import entities.Document
import entities.DocumentStatus
import entities.User
import groovy.transform.ThreadInterrupt
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
		RETURN,
		COMPLETE,
		TRANSFER_EXCUTION,
		ISSUE_MIS
	}

	def performAction(Document document, ActionType actionType, String comment = "", User user = null) {
		waitForPresentOf(documentActionBtn)
		clickToElement(documentActionBtn)
		switch (actionType) {
			case ActionType.APPROVE:
				clickToElement(approveDocument)
				if (comment) inputText(getOpinionTxt, comment)
				clickToElement(submitApproveBtn)
				document.setStatus(DocumentStatus.APPROVED)
				break

			case ActionType.REJECT:
				clickToElement(rejectDocument)
				if (comment) inputText(getOpinionTxt, comment)
				clickToElement(submitApproveBtn)
				document.setStatus(DocumentStatus.REJECT)
				break

			case ActionType.COMMENT:
				clickToElement(getComment)
				if (comment) inputText(getOpinionTxt, comment)
				clickToElement(submitApproveBtn)
				document.setStatus(DocumentStatus.COMMENTED)
				break

			case ActionType.APPROVE_ADD_CC:
				clickToElement(approveDocument)
				if (comment) inputText(getOpinionTxt, comment)
				selectCCUser(user)
				clickToElement(submitApproveBtn)
				document.setStatus(DocumentStatus.APPROVED)
				break

			case ActionType.WITHDRAW_DOCUMENT:
				clickToElement(withdrawDocument)
				if (comment) inputText(getOpinionTxt, comment)
				clickToElement(submitApproveBtn)
				document.setStatus(DocumentStatus.WAIT_PROCESS)
				break

			case ActionType.RETURN:
				clickToElement(returnDocument)
				if (comment) inputText(getOpinionTxt, comment)
				clickToElement(submitApproveBtn)
				document.setStatus(DocumentStatus.RETURNED)
				break

			case ActionType.COMPLETE:
				clickToElement(completeDocument)
				clickToElement(okBtn)
				document.setStatus(DocumentStatus.COMPLETE)
				break

			case ActionType.TRANSFER_EXCUTION:
				clickToElement(transferExcution)
				break
			case ActionType.ISSUE_MIS:
				clickToElement(issueMis)
				return
		}
		if (GlobalVariable.PLATFORM == 'Android') {
			waitForNotPresentOf(loadingImage)
		} else {
			backToHome()
			waitForPresentOf(bellIcon)
		}
	}


	def backToHome() {
		int attempts = 0
		while (!isDisplayed(fillterBtn) && attempts++ < 5) {
			clickToElement(backBtn)
			Thread.sleep(500)
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

	def checkStatus(Document document) {
		AssertUtilities.checkEquals(getText(status), document.getStatus().toString())
	}

	def checkStatus(DocumentStatus documentStatus) {
		AssertUtilities.checkEquals(getText(status), documentStatus.toString())
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

	def checkAssigner(User user) {
		swipe('down')
		AssertUtilities.checkContains(getText(assigner), user.getEmail())
	}

	def checkAssigners(User... users) {
		expandDefineProcess()
		swipe('down')
		List<String> listEmails = []
		for (TestObject item : listProcessDefineUser()) {
			listEmails.add(getText(item))
		}
		boolean allEmailsFound = true
		for (User user : users) {
			boolean emailFound = listEmails.any { email ->
				email.contains(user.getEmail())
			}
		}

		AssertUtilities.assertTrue(allEmailsFound, "Checking if all user emails are present in the list")
	}

	//only check file name
	def checkPresentFileName(Document doc) {
		swipeToElement(mainFile)
		String gui = getText(mainFile).split('_')[0]
		String data = doc.getMainFileName().split('.pdf')[0]
		AssertUtilities.checkContains(getText(mainFile), data)
	}

	def checkAttachFileName(Document doc) {
		String data = doc.getSubFileName().replace(".pdf", "").replace(".docx", "").replace(".doc", "")
		swipe('down')
		boolean status = subFiles().any {
			getText(it).contains(data)
		}
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

	def showMoreComment() {
		swipe('down', 0.5)
		if (isDisplayed(showMoreComment)) {
			clickToElement(showMoreComment)
			Thread.sleep(500)
		}
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

	def isAssignerDisplayed() {
		AssertUtilities.assertTrue(!isDisplayed(assigner), "Check sender not displayed")
	}

	def isSendCommentDisplayed() {
		clickToElement(documentActionBtn) //click to open actions pop-up
		boolean status = isDisplayed(getComment)
		AssertUtilities.assertTrue(status)
		clickToElement(documentActionBtn) //click to close actions pop-up
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


	def downloadFileAtMainFile() {
		waitForPresentOf(mainFileAttachIcon)
		clickToElement(mainFileAttachIcon)
		downLoadFileFromDocument()
		submitDownload()
		//		waitForPresentOf(mainFileAttachIcon)
		waitForPresentOf(downloadSubFile) //android
	}

	def downloadFile() {
		waitForPresentOf(requestType)
		waitForVisibilityOf(requestType)
		downLoadFileFromDocument()
		submitDownload()
		waitForPresentOf(documentActionBtn)
	}

	def downLoadFileFromDocument() {
		swipeToElement(downloadSubFile)
		clickToElement(downloadSubFile)
		if(GlobalVariable == 'Android') {
			waitForNotPresentOf(loadingImage)
		}
	}

	def submitDownload() {
		if(GlobalVariable.PLATFORM == 'iOS') {
			clickToElement(acceptUseFolder)
			clickToElement(acceptNotifiDownloadSuccess)
		} else {
			waitForPresentOf(useFolder)
			clickToElement(useFolder)
			clickToElement(acceptUseFolder)
		}
	}

	def expandDefineProcess() {
		if (GlobalVariable.PLATFORM == 'iOS') {
			swipeToElement(defindeProcessTitle)
			clickToElement(defindeProcessTitle)
		} else {
			swipeToElement(expandDefineProcess)
			swipe("down", 0.2)
			boolean check = isDisplayed(defindeProcessComponent)
			if (!isDisplayed(defindeProcessComponent)) {
				clickToElement(expandDefineProcess)
				waitForPresentOf(defindeProcessComponent)
			}
		}
	}

	def selectUserTransfered(User user) {
		clickToElement(assignTransfer)
		inputText(transferEmailSearch, user.getEmail())
		enterText(transferEmailSearch)
		if (GlobalVariable.PLATFORM == "iOS") {
			waitForNotPresentOf(listUserLoadingMask)
		}
		waitForPresentOf(assignerItem(user.getEmail()))
		clickToElement(assignerItem(user.getEmail()))
	}

	/**
	 * Selects a CC user in document approval process.
	 * @param user User to be added as CC
	 */
	def selectCCUser(User user) {
		inputText(ccOnPopupTxt, user.getEmail())

		if (GlobalVariable.PLATFORM == 'Android') {
			// Handle Android suggestion pop-up issue - direct element selection not possible
			clickToElement(ccOnPopupTxt)
			Utilities.runCommand("adb shell input keyevent 123") // Move cursor to end
			Utilities.runCommand("adb shell input keyevent 67")  // Delete 1 char to trigger suggestions
			Thread.sleep(5000)                                   // Wait for suggestions to appear
			clickToElement(submitApproveBtn)                     // Select first suggestion
		} else {
			// iOS: Direct element selection works
			clickToElement(ccUserItemOnPopup(user.getEmail()))
		}
	}

	def isEditFileIconDisplayed() {
		boolean status = isDisplayed(editFileAttachIcon, 20)
		AssertUtilities.assertTrue(status, "Check edit file icon is displayed")
	}

	def isEditFileIconNotDisplayed() {
		boolean status = isDisplayed(editFileAttachIcon, 20)
		AssertUtilities.assertFalse(status, "Check edit file icon is not displayed")
	}
}
