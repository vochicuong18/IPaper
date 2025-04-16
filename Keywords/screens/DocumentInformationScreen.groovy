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


	def checkDocumentTitle(String data) {
		waitForVisibilityOf(documentActionBtn)
		AssertUtilities.checkEquals(getText(documentTitle), data)
	}

	def checkSender(String data) {
		AssertUtilities.checkEquals(getText(senderName), data)
	}

	def isSenderDisplayed() {
		AssertUtilities.assertTrue(!isDisplayed(senderName), "Check sender not displayed after reject")
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

	def checkPresentFileName(String data) {
		data.replace(".pdf", "")
		swipe('down')
		AssertUtilities.checkContains(getText(mainFile), data)
	}

	def checkAttachFileName(String data) {
		data.replace(".pdf", "")
		swipe('down')
		AssertUtilities.checkContains(getText(subFile), data)
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
}
