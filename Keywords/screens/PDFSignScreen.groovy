package screens

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import internal.GlobalVariable
import utilities.CalendarUtilities
public class PDFSignScreen extends BaseKeyword{
	TestObject doneTime, submitDatePickerBtn, mailFile, subFile, titleTxt, priorityValue, descriptionTxt, assignerTxt, assignerEmailSearch,
	relatedTxt, relatedEmailSearch, actionBtn, getOpinionAction, saveFormAction, sendFormAction, backBtn, opinionNoteTxt, submitFormBtn, iosOpenDatePicker,
	submitSelectedFile

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

	public PDFSignScreen() {
		String platformPath = "Object Repository/Elements/${GlobalVariable.PLATFORM}/PDFSignScreen/"
		doneTime = findTestObject("${platformPath}doneTime")
		submitDatePickerBtn = findTestObject("${platformPath}submitDatePickerBtn")
		mailFile = findTestObject("${platformPath}mainFile")
		subFile = findTestObject("${platformPath}subFile")
		titleTxt = findTestObject("${platformPath}titleTxt")
		priorityValue = findTestObject("${platformPath}priorityValue")
		descriptionTxt = findTestObject("${platformPath}descriptionTxt")
		assignerTxt = findTestObject("${platformPath}assignerTxt")
		assignerEmailSearch = findTestObject("${platformPath}assignerEmailSearch")
		relatedTxt = findTestObject("${platformPath}relatedTxt")
		relatedEmailSearch = findTestObject("${platformPath}relatedEmailSearch")
		actionBtn = findTestObject("${platformPath}actionBtn")
		getOpinionAction = findTestObject("${platformPath}getOpinionAction")
		saveFormAction = findTestObject("${platformPath}saveFormAction")
		sendFormAction = findTestObject("${platformPath}sendFormAction")
		backBtn = findTestObject("${platformPath}backBtn")
		opinionNoteTxt = findTestObject("${platformPath}opinionNoteTxt")
		submitFormBtn = findTestObject("${platformPath}submitFormBtn")
		iosOpenDatePicker = findTestObject("${platformPath}iosOpenDatePicker")
		submitSelectedFile = findTestObject("${platformPath}submitSelectedFile")
	}

	def fillInTitle(String title) {
		inputText(titleTxt, title)
	}

	def selectPriority (Priority priority) {
		swipe('down')
		def priorityItem = findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/PDFSignScreen/priorityItem", [('priority') : priority.toString()])
		clickToElement(priorityValue)
		clickToElement(priorityItem)
	}

	def selectTime (String date) {
		CalendarUtilities calendarUtilities = new CalendarUtilities()
		openDatePicker()
		calendarUtilities.selectDate(date)
		submitDate()
	}

	def fillInDescription(String decs) {
		inputText(descriptionTxt, decs)
	}

	def selectAssigner(String email) {
		def assignerItem = findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/PDFSignScreen/assignerItem", [('email') : email])
		swipe('down')
		clickToElement(assignerTxt)
		inputText(assignerEmailSearch, email)
		enterText(assignerEmailSearch)
		clickToElement(assignerItem)

		if (GlobalVariable.PLATFORM == "iOS") {
			clickToElement(backBtn) // click to "Xong"
		}
	}

	def openMainFileBrowser() {
		swipe('down')
		clickToElement(mailFile)
	}

	def openSubFileBrowser() {
		clickToElement(subFile)
	}

	def selectRelatedMember(String email) {
		def relatedMemberItem = findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/PDFSignScreen/relatedMemberItem", [('email') : email])
		clickToElement(relatedTxt)
		inputText(relatedEmailSearch, email)
		enterText(relatedEmailSearch)
		clickToElement(relatedMemberItem)
		clickToElement(backBtn)
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

	def submitRequest() {
		clickToElement(submitFormBtn)
	}

	def submitDate() {
		if (GlobalVariable.PLATFORM == "Android") {
			clickToElement(submitDatePickerBtn)
		}
	}
}
