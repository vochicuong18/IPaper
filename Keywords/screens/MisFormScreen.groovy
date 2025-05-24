package screens

import java.text.SimpleDateFormat

import base.BaseKeyword
import internal.GlobalVariable
import locator.MisFormLocator
import utilities.CalendarUtilities

public class MisFormScreen extends MisFormLocator implements BaseKeyword{

	private static String getNextDayFormatted() {
		Calendar calendar = Calendar.getInstance()
		calendar.add(Calendar.DATE, 1)
		return new SimpleDateFormat("d/M/yyyy").format(calendar.getTime())
	}

	def selectBottomSheetItem(String item) {
		clickToElement(itemBottomSheet(item))
	}
	
	def sendApprove() {
		clickToElement(actionBtn)
		clickToElement(sendApproveBtn)
		waitForPresentOf(commentTxt)
		inputText(commentTxt, "Auto comment")
		clickToElement(sendComment)
	}

	def fillDocumentName(String name) {
		inputText(documentNameTxt, name)
	}

	def selectFinishDate() {
		swipeToElement(finishDateTxt)
		CalendarUtilities calendarUtilities = new CalendarUtilities()
		clickToElement(finishDateTxt)
		calendarUtilities.selectDate(getNextDayFormatted())
		submitDate()
	}
	def selectIssueDate() {
		swipeToElement(issueDateTxt)
		CalendarUtilities calendarUtilities = new CalendarUtilities()
		clickToElement(issueDateTxt)
		calendarUtilities.selectDate(getNextDayFormatted())
		submitDate()
	}
	def selectEffectDate() {
		swipeToElement(effectiveDateTxt)
		CalendarUtilities calendarUtilities = new CalendarUtilities()
		clickToElement(effectiveDateTxt)
		calendarUtilities.selectDate(getNextDayFormatted())
		submitDate()
	}
	def selectHeadUnit(String head) {
		swipeToElement(headTxt)
		clickToElement(headTxt)
		selectBottomSheetItem(head)
	}
	def selectSignUser(String userName) {
		swipeToElement(signUserTxt)
		clickToElement(signUserTxt)
		selectBottomSheetItem(userName)
	}
	def selectRelatedType(String type) {
		swipeToElement(relatedTypeTxt)
		clickToElement(relatedTypeTxt)
		selectBottomSheetItem(type)
		clickToElement(submitSelectedBtn)
	}
	def selectBusinessType(String type) {
		swipeToElement(businessType)
		clickToElement(businessType)
		selectBottomSheetItem(type)
		clickToElement(submitSelectedBtn)
	}
	def selectResponsibleUnit(String unit) {
		swipeToElement(responsibleUnitTxt)
		clickToElement(responsibleUnitTxt)
		selectBottomSheetItem(unit)
		clickToElement(submitSelectedBtn)
	}
	def selectIssueLevel(String level) {
		swipeToElement(issueLevelTxt)
		clickToElement(issueLevelTxt)
		selectBottomSheetItem(level)
		clickToElement(submitSelectedBtn)
	}
	def selectDocumentType(String type) {
		swipeToElement(documentTypeTxt)
		clickToElement(documentTypeTxt)
		selectBottomSheetItem(type)
		clickToElement(submitSelectedBtn)
	}
	def selectUnitReceived(String received) {
		swipeToElement(unitReceivedTxt)
		clickToElement(unitReceivedTxt)
		selectBottomSheetItem(received)
		clickToElement(submitSelectedBtn)
	}

	def submitDate() {
		if (GlobalVariable.PLATFORM == "Android") {
			clickToElement(submitDatePickerBtn)
		}
	}
}
