package screens

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import base.BaseKeyword
import internal.GlobalVariable
import locator.PDFSignLocator
import utilities.CalendarUtilities
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

	def fillInTitle(String title) {
		inputText(titleTxt, title)
	}

	def selectPriority (Priority priority) {
		swipeToElement(priorityValue)
		clickToElement(priorityValue)
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
		clickToElement(assignerItem(email))

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
		clickToElement(relatedTxt)
		inputText(relatedEmailSearch, email)
		enterText(relatedEmailSearch)
		if (GlobalVariable.PLATFORM == "iOS") {
			waitForNotPresentOf(listUserLoadingMask)
		}
		clickToElement(relatedMemberItem(email))
		clickToElement(doneBtn)
	}

	def saveRequest() {
		clickToElement(actionBtn)
		clickToElement(saveFormAction)
	}

	def getOpinion() {
		clickToElement(actionBtn)
		clickToElement(getOpinionAction)
	}

	def sendRequest() {
		clickToElement(actionBtn)
		clickToElement(sendFormAction)
	}

	def openDatePicker() {
		clickToElement(doneTime)
		if (GlobalVariable.PLATFORM == "Android") {
			waitForVisibilityOf(submitDatePickerBtn)
		}

		else if (GlobalVariable.PLATFORM == "iOS") {
			clickToElement(iosOpenDatePicker)
		}
	}

	def fillInOpinion(String guest) {
		inputText(opinionNoteTxt, guest)
	}

	def submitErrorPopup() {
		clickToElement(acceptErrorButton)
	}

	def submitRequest() {
		clickToElement(submitFormBtn)
		if (GlobalVariable.PLATFORM == "Android") {
			waitForNotPresentOf(loadingMask)
		} else {
			waitForAttributeValueOf(screenTitle, "name", "Tạo yêu cầu theo mẫu")
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
}
