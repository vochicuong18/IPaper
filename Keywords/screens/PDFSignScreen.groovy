package screens

import com.kms.katalon.core.util.KeywordUtil

import base.BaseKeyword
import entities.User
import internal.GlobalVariable
import locator.PDFSignLocator
import utilities.AssertUtilities
import utilities.CalendarUtilities
import utilities.Utilities
public class PDFSignScreen extends PDFSignLocator implements BaseKeyword{

	public enum Priority {
		KHAN_CAP, CAO, BINH_THUONG

		String toString() {
			switch (this) {
				case KHAN_CAP : return "Khẩn cấp"
				case CAO : return "Cao"
				case BINH_THUONG : return "Bình thường"
				default : return "please define"
			}
		}
	}

	public enum PerformAction {
		SEND_APPROVE, SEND_WITH_COMENT, SAVE_DRAFT

		String toString() {
			switch (this) {
				case SEND_APPROVE : return "Gửi duyệt"
				case SEND_WITH_COMENT : return "Lấy ý kiến"
				case SAVE_DRAFT : return "Lưu"
				default : return "please define"
			}
		}

		String toStringEmail() {
			switch (this) {
				case SEND_APPROVE : return "TRÌNH PHÊ DUYỆT"
				case SEND_WITH_COMENT : return "GÓP Ý TRÌNH DUYỆT"
				default : return "please define"
			}
		}
	}

	public enum Process {
		GET_COMMENT, PARALLEL_PROCESS

		String toString() {
			switch (this) {
				case GET_COMMENT : return "Lấy ý kiến"
				case PARALLEL_PROCESS : return "Quy trình song song"
				default : return "please define"
			}
		}
	}

	def fillInTitle(String title) {
		waitForPresentOf(titleTxt)
		inputText(titleTxt, title)
	}

	def selectPriority (Priority priority) {
		swipeToElement(priorityValue)
		clickToElement(priorityValue)
		Thread.sleep(300) //wait animation
		clickToElement(priorityItem(priority.toString()))
	}

	def selectTime (String date) {
		CalendarUtilities calendarUtilities = new CalendarUtilities()
		openDatePicker()
		calendarUtilities.selectDate(date)
		submitDate()
	}

	def fillInDescription(String decs) {
		swipeToElement(descriptionTxt)
		inputText(descriptionTxt, decs)
	}

	def selectAssigner(String email) {
		swipeToElement(assignerTxt)
		clickToElement(assignerTxt)
		inputText(assignerEmailSearch, email)
		enterText(assignerEmailSearch)
		if (GlobalVariable.PLATFORM == "iOS") {
			waitForNotPresentOf(listUserLoadingMask)
		}
		waitForPresentOf(assignerItem(email))
		clickToElement(assignerItem(email))

		if (GlobalVariable.PLATFORM == "iOS") {
			clickToElement(doneBtn) // click to "Xong"
		}
	}

	def selectAssigner(User user) {
		swipeToElement(assignerTxt)
		clickToElement(assignerTxt)
		inputText(assignerEmailSearch, user.getEmail())
		enterText(assignerEmailSearch)
		if (GlobalVariable.PLATFORM == "iOS") {
			waitForNotPresentOf(listUserLoadingMask)
		}
		waitForPresentOf(assignerItem(user.getEmail()))
		clickToElement(assignerItem(user.getEmail()))

		if (GlobalVariable.PLATFORM == "iOS") {
			clickToElement(doneBtn) // click to "Xong"
		}
	}

	def openMainFileBrowser() {
		swipeToElement(mainFile)
		clickToElement(mainFile)
	}

	def openSubFileBrowser() {
		swipeToElement(subFile)
		clickToElement(subFile)
	}

	def selectRelatedMember(String email) {
		waitForPresentOf(relatedTxt)
		clickToElement(relatedTxt)
		inputText(relatedEmailSearch, email)
		enterText(relatedEmailSearch)
		if (GlobalVariable.PLATFORM == "iOS") {
			waitForNotPresentOf(listUserLoadingMask)
		}
		waitForPresentOf(relatedMemberItem(email))
		clickToElement(relatedMemberItem(email))
		clickToElement(doneBtn)
	}

	def openDatePicker() {
		clickToElement(doneTime)
		if (GlobalVariable.PLATFORM == "Android") {
			waitForVisibilityOf(submitDatePickerBtn)
		} else {
			clickToElement(iosOpenDatePicker)
		}
	}

	def performAction(PerformAction action) {
		clickToElement(actionBtn)
		Thread.sleep(500)
		switch (action) {
			case PerformAction.SAVE_DRAFT:
				clickToElement(saveFormAction)
				break
			case PerformAction.SEND_WITH_COMENT:
				clickToElement(getOpinionAction)
				break
			case PerformAction.SEND_APPROVE:
				clickToElement(sendFormAction)
				break
			default:
				KeywordUtil.logInfo("Unknown action: ${action}")
		}
	}


	def fillInOpinion(String guest) {
		waitForPresentOf(opinionNoteTxt)
		inputText(opinionNoteTxt, guest)
	}

	def submitErrorPopup() {
		clickToElement(acceptErrorButton)
	}

	def submitRequest() {
		waitForPresentOf(submitFormBtn)
		clickToElement(submitFormBtn)
		Thread.sleep(1)
		if (GlobalVariable.PLATFORM == "Android") {
			waitForNotPresentOf(loadingMask)
		} else {
			waitForPresentOf(bellIcon) // Back to home when submit request
		}
	}

	def submitDate() {
		if (GlobalVariable.PLATFORM == "Android") {
			clickToElement(submitDatePickerBtn)
		}
	}

	String getErrorMessage() {
		return getText(errorMessage)
	}

	def selectProcessDefined(String processName) {
		swipeToElement(listDefineProcess)
		clickToElement(listDefineProcess)
		waitForPresentOf(processDefined(processName))
		clickToElement(processDefined(processName))

		if (GlobalVariable.PLATFORM == 'iOS'){
			clickToElement(submitProcess)
		}
	}

	def processDefinition(User ...users) {
		openUserList()
		searchSelectUser(users)
	}

	def openUserList() {
		swipeToElement(addDefineProcess)
		clickToElement(addDefineProcess)
	}

	def searchSelectUser(User ...users) {
		waitForPresentOf(searchEmailDefineProcess)
		for (User user : users) {
			inputText(searchEmailDefineProcess, user.getEmail())
			waitForPresentOf(userItemDefineProcess(user))
			Thread.sleep(500)
			clickToElement(userItemDefineProcess(user))
		}
		clickToElement(submitUseSelection)
	}

	def addUserIntoProcess (int stepProcess, User user) {
		clickToElement(addUserProcessDefine(stepProcess))
		searchSelectUser(user)
	}

	def deleteUserInProcess(User user) {
		swipe('down', 0.5)
		clickToElement(deleteUserInProcessDefine(user))
	}

	def clickOnBackBtn() {
		clickToElement(backBtn)
	}

	def submitSaveDocument() {
		clickToElement(submitWarningPopup)
	}

	def rejectSaveDocument() {
		clickToElement(rejectWarningPopup)
	}

	def isEyeIconEnabled() {
		clickToElement(eyeIcon)
		boolean status = isDisplayed(listDefineProcessPopup, 2)
		status ? Utilities.back() : println()
		AssertUtilities.assertTrue(status, "Check eye icon in process define is enabled")
	}

	def isEyeIconDisabled() {
		clickToElement(eyeIcon)
		boolean status = isDisplayed(listDefineProcessPopup, 2)
		AssertUtilities.assertFalse(status, "Check eye icon in process define is enabled")
	}

	def checkProcessDefineNumber(int number) {
		swipeToElement(listDefineProcess)
		swipe('down', 0.3)
		String gui = getText(defineProcessNumber)
		AssertUtilities.checkContains(gui, number.toString())
	}

	def isAttachIconDisplayed() {
		boolean status = isDisplayed(addDefineProcess)
		AssertUtilities.assertTrue(status, "Check attach icon is displayed")
	}

	def backToHome() {
		clickToElement(backBtn)
		clickToElement(submitWarningPopup)
	}
}
