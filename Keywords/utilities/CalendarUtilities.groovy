package utilities

import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate

import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.PointerInput

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import drivers.Driver
import internal.GlobalVariable
import io.appium.java_client.touch.offset.PointOption
import locator.CalendarLocator
public class CalendarUtilities extends CalendarLocator implements BaseKeyword {

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

	def selectYearAndroid(String yearExpect) {
		if (getCurrentYear() != yearExpect) {
			clickToElement(year)
			clickToElement(yearItems(yearExpect))
		}
	}

	def selectMonthAndroid(int expectedMonth) {
		int currentMonth = getCurrentMonth()
		while (currentMonth < expectedMonth) {
			clickToElement(nextBtn)
			Thread.sleep(200)
			currentMonth++
		}
	}

	def selectDayAndroid(String expecteDay) {
		waitForPresentOf(day(expecteDay))
		clickToElement(day(expecteDay))
	}

	//TH 6, 25 thg4 và T.6, 25 th4

	int getCurrentMonth() {
		String currentDate = getText(currentDate)

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
		return getText(year)
	}

	/** --------------------------------------- DATE PICKER IOS --------------------------------------- */

	def selectDateIOS(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy")
		Date parsedDate = sdf.parse(date)
		String day = new SimpleDateFormat("d").format(parsedDate)
		int month = new SimpleDateFormat("M").format(parsedDate).toInteger()
		int year = new SimpleDateFormat("yyyy").format(parsedDate).toInteger()


		String currentDate = getText(currentMonthYear)
		SimpleDateFormat currentSdf = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
		Date currentParsedDate = currentSdf.parse(currentDate)

		int currentMonth = new SimpleDateFormat("M").format(currentParsedDate).toInteger()
		int currentYear = new SimpleDateFormat("yyyy").format(currentParsedDate).toInteger()

		clickToElement(currentMonthYear)
		selectMonthIOS(currentMonth, month)
		selectYearIOS(currentYear, year)
		clickToElement(closeMonthYearSelected)
		selectDayIOS(day)
		tapOutSideElement(currentMonthYear)
		clickToElement(submitDateBtn)
	}

	def selectMonthIOS(int currentMonth, int expectMonth) {
		if(currentMonth < expectMonth) {
			while (currentMonth < expectMonth) {
				verticalSwipeYearIOS(monthItems, "down")
				currentMonth++
			}
		}
	}

	def selectYearIOS(int currentYear, int expectYear) {
		if (currentYear < expectYear) {
			while (currentYear < expectYear) {
				verticalSwipeYearIOS(yearItems, "down")
				currentYear++
			}
		}
	}

	def selectDayIOS(String day) {
		clickToElement(dayItem(day))
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
}
