package utilities

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import internal.GlobalVariable

public class CalendarUtilities extends BaseKeyword {

	int getCurrentMonth() {
		String currentDate = getText(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/currentDate"))
		return currentDate.split("thg ")[1].toInteger()
	}

	String getCurrentYear() {
		return getText(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/year"))
	}

	def selectDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy")
		Date parsedDate = sdf.parse(date)

		String day = new SimpleDateFormat("d").format(parsedDate)
		int month = new SimpleDateFormat("M").format(parsedDate).toInteger()
		String year = new SimpleDateFormat("yyyy").format(parsedDate)

		selectYear(year)
		selectMonth(month)
		selectDay(day)
	}

	def selectYear(String year) {
		if (getCurrentYear() != year) {
			clickToElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/year"))
			def yearItem = findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/yearItems", [('year'): year])
			clickToElement(yearItem)
		}
	}

	def selectMonth(int expectedMonth) {
		int currentMonth = getCurrentMonth()
		while (currentMonth < expectedMonth) {
			clickToElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/nextBtn"))
			Thread.sleep(200)
			currentMonth++
		}
	}

	def selectDay(String day) {
		def dayItem = findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/day", [('day'): day])
		clickToElement(dayItem)
	}

	//--------------------------------------------------------------------------------------------------------------------------------------------


	def selectDate1(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy")
		Date parsedDate = sdf.parse(date)
		String day = new SimpleDateFormat("d").format(parsedDate)
		int month = new SimpleDateFormat("M").format(parsedDate).toInteger()
		int year = new SimpleDateFormat("yyyy").format(parsedDate).toInteger()


		String currentDate = getText(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/currentMonthYear"))
		SimpleDateFormat currentSdf = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
		Date currentParsedDate = currentSdf.parse(currentDate)

		int currentMonth = new SimpleDateFormat("M").format(currentParsedDate).toInteger()
		int currentYear = new SimpleDateFormat("yyyy").format(currentParsedDate).toInteger()

		clickToElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/currentMonthYear"))
		selectMonth1(currentMonth, month)
		selectYear1(currentYear, year)
		clickToElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/closeMonthYearSelected"))
		selectDay1(day)
		tapOutSideElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/currentMonthYear"))
		clickToElement(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/submitDateBtn"))
	}

	def selectMonth1(int currentMonth, int expectMonth) {
		if(currentMonth < expectMonth) {
			while (currentMonth < expectMonth) {
				verticalSwipeYearIOS(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/monthItems"), "down")
				Thread.sleep(200)
				currentMonth++
			}
		}
	}

	def selectYear1(int currentYear, int expectYear) {
		if (currentYear < expectYear) {
			while (currentYear < expectYear) {
				verticalSwipeYearIOS(findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/yearItems"), "down")
				Thread.sleep(200)
				currentYear++
			}
		}
	}

	def selectDay1(String day) {
		def item = findTestObject("Object Repository/Elements/${GlobalVariable.PLATFORM}/Calendar/dayItems", [('day'): day])
		clickToElement(item)
	}

	def verticalSwipeYearIOS(TestObject to, String direction) {
		int xPosition = Mobile.getElementLeftPosition(to, 10) + 20
		int yPosition = Mobile.getElementTopPosition(to, 10) + 100
		println "-------------" + xPosition + "-- " + yPosition
		if (direction == "down") {
			Mobile.swipe(xPosition, yPosition, xPosition, yPosition - 30)
		}

		else if (direction == "up") {
			Mobile.swipe(xPosition, yPosition, xPosition, yPosition - 20)
		}
	}

	def tapOutSideElement(TestObject to) {
		int xPosition = Mobile.getElementLeftPosition(to, 10) - 20
		int yPosition = Mobile.getElementTopPosition(to, 10) - 20
		Mobile.tapAtPosition(xPosition, yPosition)
	}
}
