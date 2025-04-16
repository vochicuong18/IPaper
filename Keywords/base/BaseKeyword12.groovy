package base

import static org.junit.Assert.assertTrue

import java.time.Duration

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.MobileTestObject
import com.kms.katalon.core.testobject.TestObject

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.nativekey.AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent
import utilities.Utilities

public class BaseKeyword {
	AppiumDriver driver
	final int TIMEOUT = 30

	BaseKeyword() {
	}

	def hideKeyboard() {
		Mobile.hideKeyboard()
	}

	def getLocator(TestObject element) {
		MobileTestObject mObj = (MobileTestObject) element
		return mObj.getMobileLocator()
	}

	def getValueAttributeOf(TestObject to, attribute) {
		return Mobile.getAttribute(to, attribute, TIMEOUT)
	}

	String getText(TestObject element) {
		return Mobile.getText(element, TIMEOUT)
	}

	def inputText(TestObject element, String text) {
		Mobile.delay(0.1)
		Mobile.tap(element, TIMEOUT)
		Mobile.clearText(element, TIMEOUT)
		Mobile.sendKeys(element, text)
		Utilities.log("Entered ${text} into ${getLocator(element)}")
	}

	def enterText(TestObject to) {
		if (GlobalVariable.PLATFORM=="Android") {
			AndroidDriver driver = MobileDriverFactory.getDriver()
			driver.pressKey(new KeyEvent(AndroidKey.ENTER))
		} else Mobile.sendKeys(to, Keys.chord(Keys.RETURN))
	}


	def clickToElement(TestObject element) {
		Mobile.delay(0.1)
		Mobile.tap(element, TIMEOUT)
	}

	def swipe(String direction) {
		int height  = Mobile.getDeviceHeight()
		int weight = Mobile.getDeviceWidth()
		int startX = weight/2
		int startY = height/2
		int endX = weight/2
		int endY = height/2
		if (direction == 'down') {
			Mobile.swipe(startX, startY, endX, endY - 200)
		}
		else if (direction == 'up') {
			Mobile.swipe(1050, 400, 1050, 1200)
		}
	}

	def horizontalSwipeFromElement(TestObject to, String direction) {
		int xPosition = Mobile.getElementLeftPosition(to, 10) + 400
		int yPosition = Mobile.getElementTopPosition(to, 10) + 200

		if (direction == "right") {
			Mobile.swipe(xPosition, yPosition, xPosition + 300, yPosition)
		}

		else if (direction == "left") {
			Mobile.swipe(xPosition, yPosition, xPosition - 300, yPosition)
		}
	}


	def swipeToElement(TestObject element, String direction) {
		int MAX_ATTEMPTS = 5
		int attempts = 0
		while (attempts < MAX_ATTEMPTS && isDisplayed(element)) {
			Utilities.log("Swipe ${direction} to find ${getLocator(element)}")
			swipe(direction)
			attempts++
		}
		assertTrue(isDisplayed(element))
	}


	boolean isDisplayed(TestObject element) {
		boolean status = Mobile.verifyElementVisible(element, 1, FailureHandling.OPTIONAL)
		Utilities.log("Verify ${getLocator(element)} is displayed: ${status}")
		return status
	}

	boolean isExisted(TestObject element) {
		boolean status = Mobile.verifyElementExist(element, 1, FailureHandling.OPTIONAL)
		Utilities.log("Verify ${getLocator(element)} is existed: ${status}")
		return status
	}

	/**
	 * Wait until element attached to DOM
	 *
	 * @param by to be checked
	 * @throws TimeoutException
	 */

	def waitForPresentOf(TestObject testObject) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
		wait.until { driver.findElements(convertToBy(testObject)).size() > 0 }
	}

	/**
	 * Wait until element detached to DOM
	 *
	 * @param by to be checked
	 * @throws TimeoutException
	 */

	def waitForNotPresentOf(TestObject testObject) {
		WebDriver driver = MobileDriverFactory.getDriver()
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3))
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
		wait.until { driver.findElements(convertToBy(testObject)).size() == 0 }
	}

	def waitForVisibilityOf(TestObject testObject) {
		WebElement element = convertTestObjectToWebElement(testObject)
		return waitForCondition(testObject) { ExpectedConditions.visibilityOf(element) }
	}

	def waitForInVisibilityOf(TestObject testObject) {
		WebElement element = convertTestObjectToWebElement(testObject)
		return waitForCondition(testObject) { ExpectedConditions.invisibilityOf(element) }
	}

	def waitForCondition(TestObject testObject, Closure<Boolean> condition) {
		WebDriver driver = MobileDriverFactory.getDriver()
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
		return wait.until(condition)
	}

	WebElement convertTestObjectToWebElement(TestObject testObject) {
		WebDriver driver = MobileDriverFactory.getDriver()
		return driver.findElement(convertToBy(testObject))
	}

	By convertToBy(TestObject testObject) {
		MobileTestObject mobileObject = (MobileTestObject) testObject
		String method = mobileObject.getMobileLocatorStrategy().toString()
		String locator = mobileObject.getMobileLocator()

		switch (method) {
			case "XPATH": return By.xpath(locator)
			case "CSS": return By.cssSelector(locator)
			case "ID": return By.id(locator)
			default: throw new IllegalArgumentException("Not supported locator type: " + method)
		}
	}
}
