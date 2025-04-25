package utilities

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat
import java.time.LocalDate

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import internal.GlobalVariable

public class CalendarUtilities implements BaseKeyword {

	def selectDate (String date) {
		GlobalVariable.PLATFORM == "Android" ? selectDateAndroid(date) : selectDateIOS(date)
	}

	/** --------------------------------------- DATE PICKER ANDROID --------------------------------------- */

	def selectDateAndroid(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy")
		Date parsedDate = sdf.parse(date)

		String day = new SimpleDateFormat("d").format(parsedDate)
		int month = new SimpleDateFormat("M").format(parsedDate).toInteger()
		String year = new SimpleDateFormat("yyyy").format(parsedDate)

		selectYearAndroid(year)
		selectMonthAndroid(month)
		selectDayAndroid(day)
	}

	def selectYearAndroid(String year) {
		if (getCurrentYear() != year) {
			clickToElement(findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/year"))
			def yearItem = findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/yearItems", [('year'): year])
			clickToElement(yearItem)
		}
	}

	def selectMonthAndroid(int expectedMonth) {
		int currentMonth = getCurrentMonth()
		while (currentMonth < expectedMonth) {
			clickToElement(findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/nextBtn"))
			Thread.sleep(200)
			currentMonth++
		}
	}

	def selectDayAndroid(String day) {
		def dayItem = findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/day", [('day'): day])
		clickToElement(dayItem)
	}

	//TH 6, 25 thg4 và T.6, 25 th4

	int getCurrentMonth() {
		String currentDate = getText(findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/currentDate"))

		try {
			if (currentDate.contains("thg")) {
				return currentDate.split("thg")[1].trim().toInteger()
			} else if (currentDate.contains("Th")) {
				return currentDate.split("Th")[1].trim().toInteger()
			} else {
				throw new Exception("Không tìm thấy định dạng tháng")
			}
		} catch (Exception e) {
			return LocalDate.now().getMonthValue()
		}
	}



	String getCurrentYear() {
		return getText(findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/year"))
	}

	/** --------------------------------------- DATE PICKER IOS --------------------------------------- */

	def selectDateIOS(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy")
		Date parsedDate = sdf.parse(date)
		String day = new SimpleDateFormat("d").format(parsedDate)
		int month = new SimpleDateFormat("M").format(parsedDate).toInteger()
		int year = new SimpleDateFormat("yyyy").format(parsedDate).toInteger()


		String currentDate = getText(findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/currentMonthYear"))
		SimpleDateFormat currentSdf = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
		Date currentParsedDate = currentSdf.parse(currentDate)

		int currentMonth = new SimpleDateFormat("M").format(currentParsedDate).toInteger()
		int currentYear = new SimpleDateFormat("yyyy").format(currentParsedDate).toInteger()

		clickToElement(findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/currentMonthYear"))
		selectMonthIOS(currentMonth, month)
		selectYearIOS(currentYear, year)
		clickToElement(findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/closeMonthYearSelected"))
		selectDayIOS(day)
		tapOutSideElement(findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/currentMonthYear"))
		clickToElement(findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/submitDateBtn"))
	}

	def selectMonthIOS(int currentMonth, int expectMonth) {
		if(currentMonth < expectMonth) {
			while (currentMonth < expectMonth) {
				verticalSwipeYearIOS(findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/monthItems"), "down")
				currentMonth++
			}
		}
	}

	def selectYearIOS(int currentYear, int expectYear) {
		if (currentYear < expectYear) {
			while (currentYear < expectYear) {
				verticalSwipeYearIOS(findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/yearItems"), "down")
				currentYear++
			}
		}
	}

	def selectDayIOS(String day) {
		def item = findTestObject("Object Repository/${GlobalVariable.PLATFORM}/Calendar/dayItems", [('day'): day])
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
