package screens
import java.text.SimpleDateFormat

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import entities.User
import internal.GlobalVariable
import locator.DocumentInformationLocator
import utilities.AssertUtilities

public class DocumentInformationScreen extends DocumentInformationLocator implements BaseKeyword {
	def backToHome() {
		int attempts = 0
		while (!isDisplayed(fillterBtn) && attempts++ < 5) {
			clickToElement(backBtn)
			Mobile.delay(0.5)
		}
	}

	def checkDocumentInformation(String documentTitle, User sender, String status, String createDate,
			String finishDate, PDFSignScreen.Priority priority,
			String description, String assigner,
			String mainFileName, String subFileName) {
		checkDocumentTitle(documentTitle)
		checkSender(sender.getName())
		checkStatus(status)
		checkCreateDate(createDate)
		checkFinishDate(finishDate)
		checkPriority(priority)
		checkDescription(description)
		checkAssigner(assigner)
		checkPresentFileName(mainFileName)
		checkAttachFileName(subFileName)
	}


	def checkDocumentTitle(String data) {
		waitForPresentOf(requestType)
		waitForVisibilityOf(requestType)
		AssertUtilities.checkEquals(getText(documentTitle), data)
	}

	def checkSender(String data) {
		AssertUtilities.checkEquals(getText(senderName), data)
	}

	def isAssignerDisplayed() {
		AssertUtilities.assertTrue(!isDisplayed(assigner), "Check sender not displayed after reject")
	}

	def checkStatus(String data) {
		AssertUtilities.checkEquals(getText(status), data)
	}

	def checkCreateDate(String data) {
		String gui = getText(createDate)
		Date parsedData = new SimpleDateFormat("d/M/yyyy").parse(data)
		String formattedData = new SimpleDateFormat("dd/MM/yyyy").format(parsedData)
		AssertUtilities.checkEquals(gui, formattedData)
	}

	def checkFinishDate(String data) {
		swipe('down')
		String gui = getText(finishDate)
		Date parsedData = new SimpleDateFormat("d/M/yyyy").parse(data)
		String formattedData = new SimpleDateFormat("dd/MM/yyyy").format(parsedData)
		AssertUtilities.checkEquals(gui, formattedData)
	}

	def checkPriority(PDFSignScreen.Priority data) {
		AssertUtilities.checkEquals(getText(priority), data.toString())
	}

	def checkDescription(String data) {
		swipe('down')
		AssertUtilities.checkEquals(getText(description), data)
	}

	def checkAssigner(String data) {
		swipe('down')
		AssertUtilities.checkContains(getText(assigner), data)
	}

	//only check file name

	def checkPresentFileName(String file) {
		String gui = getText(mainFile).split('_')[0]
		String data = file.split('.pdf')[0]
		swipe('down')
		AssertUtilities.checkContains(getText(mainFile), data)
	}

	def checkAttachFileName(String data) {
		data.replace(".pdf", "")
		swipe('down')
		AssertUtilities.checkContains(data, getText(subFile))
	}

	def checkUserCannotEditDocument() {
		boolean status = false
		clickToElement(documentActionBtn)
		status = isDisplayed(rePresentTxt) && !isDisplayed(submitTxt) && !isDisplayed(getOpinoionTxt) && !isDisplayed(sendApprovalTxt)
		clickToElement(documentActionBtn)
		AssertUtilities.assertTrue(status, "Check user can not edit document")
	}

	def checkComment(User user, String dataComment) {
		swipe('down', 1)
		boolean status = false
		List<TestObject> comments = processComments(user.getName())
		for (TestObject comment : comments) {
			String gui = getText(comment)
			if (gui.equals(dataComment)) {
				status = true
				break
			}
		}
		AssertUtilities.assertTrue(status)
	}

	// APPROVE

	def approveAddCC (User user) {
		clickToElement(documentActionBtn)
		clickToElement(approveDocument)
		inputText(ccOnPopupTxt, user.getEmail())
		if (GlobalVariable.PLATFORM == 'Android') {
			Thread.sleep(5000)
			clickToElement(submitApproveBtn)
		} else {
			clickToElement(ccUserItemOnPopup(user.getEmail()))
		}
		clickToElement(submitApproveBtn)
	}

	def approveAddCC (User user, String comment) {
		clickToElement(documentActionBtn)
		clickToElement(approveDocument)
		inputText(getOpinionTxt, comment)
		inputText(ccOnPopupTxt, user.getEmail())
		if (GlobalVariable == 'Android') {
			Thread.sleep(5000)
			clickToElement(submitApproveBtn) // handle user item is not have in DOM
		} else {
			clickToElement(ccUserItemOnPopup(user.getEmail()))
		}
		clickToElement(submitApproveBtn)
	}

	def approve(String comment) {
		clickToElement(documentActionBtn)
		clickToElement(approveDocument)
		inputText(getOpinionTxt, comment)
		clickToElement(submitApproveBtn)
	}

	def approve() {
		clickToElement(documentActionBtn)
		clickToElement(approveDocument)
		clickToElement(submitApproveBtn)
	}

	// REJECT

	def reject() {
		clickToElement(documentActionBtn)
		clickToElement(rejectDocument)
		clickToElement(submitApproveBtn)
	}

	def reject(String comment) {
		clickToElement(documentActionBtn)
		clickToElement(rejectDocument)
		inputText(getOpinionTxt, comment)
		clickToElement(submitApproveBtn)
	}

	// Comment

	def getComment() {
		clickToElement(documentActionBtn)
		clickToElement(getComment)
		clickToElement(submitApproveBtn)
	}

	def getComment(String comment) {
		clickToElement(documentActionBtn)
		clickToElement(getComment)
		inputText(getOpinionTxt, comment)
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
}
