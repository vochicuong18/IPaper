package screens

import com.kms.katalon.core.util.KeywordUtil

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

	public enum PerformAction {
		SEND_APPROVE, SEND_WITH_COMENT, SAVE

		String toString() {
			switch (this) {
				case SEND_APPROVE : return "Gửi duyệt"
				case SEND_WITH_COMENT : return "Lấy ý kiến"
				case SAVE : return "Lưu"
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

		switch (action) {
			case PerformAction.SAVE:
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
