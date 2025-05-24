package screens

import java.io.ObjectInputFilter.Status

import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import drivers.Driver
import groovy.transform.ThreadInterrupt
import locator.NotificationLocator
import utilities.AIUtilities
import utilities.AssertUtilities
import utilities.Utilities

public class NotificationScreen extends NotificationLocator implements BaseKeyword {
	def deleteAllNotification() {
		clickToElement(deleteAllNofi)
		clickToElement(submitDeleted)
		Thread.sleep(2000)
	}

	def readAllNotification() {
		clickToElement(readAllNoti)
	}

	def isNotificationEmpty() {
		boolean status = isDisplayed(emptyIcon)
		AssertUtilities.assertTrue(status,"Check notification is empty")
	}

	def isNotificationRead() {
		AssertUtilities.assertFalse(haveNotificationUnRead(), "Check notification all read")
	}

	def isNotificationUnRead() {
		AssertUtilities.assertTrue(haveNotificationUnRead(), "Check notification is not read")
	}
	// Add these methods to your BaseKeyword trait

	/**
	 * Take full screen screenshot and ask AI if there's ANY bold text on screen
	 * @return boolean true if AI detects any bold text on screen
	 */
	boolean haveNotificationUnRead() {
		try {
			// Take full screen screenshot
			File screenshot = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.FILE)
			File destination = new File("PDFScreen/screen_bold_scan_${System.currentTimeMillis()}.png")
			screenshot.renameTo(destination)

			String prompt = """
			Analyze the provided image of a notification list. Identify any unread notifications.
			Criteria for an unread notification:
			The main notification text (title and/or body) appears in bold.
			The timestamp associated with the notification is displayed in red.
			List the unread notifications found, or state if none are found.
			any have notification unread, only reply true or false """
			String result = AIUtilities.analyzeImage(destination.absolutePath, prompt)
			boolean hasBoldText = result.trim().toLowerCase().contains("true")
			Utilities.logInfo("🔍 AI scan: ${result}")

			return hasBoldText
		} catch (Exception e) {
			Utilities.logFailed("Screen bold text scan failed: ${e.message}")
			return false
		}
	}

	def areNotificationsSortedByTime() {
		try {
			// Take full screen screenshot
			File screenshot = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.FILE)
			File destination = new File("PDFScreen/time_sorting_check_${System.currentTimeMillis()}.png")
			screenshot.renameTo(destination)
			String prompt = """
			"Analyze the provided image of a notification list. Determine if the notifications are sorted in descending order by time (i.e., newest first).
	
			To be considered correctly sorted (newest first):
			1. Each notification's timestamp must be the same as or older than the timestamp of the notification directly above it.
			2. If all visible notifications share the exact same timestamp (e.g., all say '1 hour ago' or '1 tháng trước'), consider them correctly sorted.
			Respond ONLY with 'true' or 'false'."""

			String result = AIUtilities.analyzeImage(destination.absolutePath, prompt)

			// Parse AI response
			boolean isSorted = result.toLowerCase().contains("true")
			AssertUtilities.assertTrue(isSorted, "Check sort by time")
		} catch (Exception e) {
			Utilities.logFailed("AI time sorting analysis failed: ${e.message}")
			return false
		}
	}
}
