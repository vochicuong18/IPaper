package base
import java.time.Duration

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty

import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.nativekey.AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent

trait BaseKeyword {
	AppiumDriver driver
	final int TIMEOUT = 30

	def hideKeyboard() {
		Mobile.hideKeyboard()
	}

	boolean getValueAttributeOf(TestObject to, attribute) {
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
	}

	def enterText(TestObject to) {
		if (GlobalVariable.PLATFORM == "Android") {
			AndroidDriver driver = MobileDriverFactory.getDriver()
			driver.pressKey(new KeyEvent(AndroidKey.ENTER))
		} else Mobile.sendKeys(to, Keys.chord(Keys.RETURN))
	}


	def clickToElement(TestObject element) {
		Mobile.delay(0.1)
		Mobile.tap(element, TIMEOUT)
	}

	def tapAtPosition(int x, int y) {
		Mobile.delay(0.1)
		Mobile.tapAtPosition(x, y)
	}

	def swipe(String direction) {
		int height = Mobile.getDeviceHeight()
		int width = Mobile.getDeviceWidth()
		int startX = width / 2
		int startY = height / 2
		int distance = (height * 0.2) as int
		int endY = direction == 'down' ? startY - distance : startY + distance
		Mobile.swipe(startX, startY, startX, endY)
	}

	def swipe(String direction, double distancePercent) {
		int height = Mobile.getDeviceHeight()
		int width = Mobile.getDeviceWidth()
		int startX = width / 2
		int startY = height / 2
		int distance = (height * distancePercent) as int
		int endY = direction == 'down' ? startY - distance : startY + distance
		Mobile.swipe(startX, startY, startX, endY)
	}

	def horizontalSwipeFromElement(TestObject to, String direction) {
		int screenWidth = Mobile.getDeviceWidth()
		int screenHeight = Mobile.getDeviceHeight()
		int elementX = Mobile.getElementLeftPosition(to, 10)
		int elementY = Mobile.getElementTopPosition(to, 10)
		int screenCenterX = screenWidth / 2
		int elementCenterX = elementX + 100
		int elementCenterY = elementY + 50
		int deltaX = screenCenterX - elementCenterX
		int swipeDistance = (int)(screenWidth * 0.5)
		int startX = elementCenterX
		int endX = (direction == "right") ? startX + swipeDistance : startX - swipeDistance
		Mobile.swipe(startX, elementCenterY, endX, elementCenterY)
	}

	def swipeToElement(TestObject element) {
		int MAX_ATTEMPTS = 10
		int attempts = 0

		while (attempts < MAX_ATTEMPTS && !isDisplayed(element)) {
			swipe("down")
			attempts++
		}

		if (!isDisplayed(element)) {
			throw new StepFailedException("Element not found after $MAX_ATTEMPTS swipes")
		}
	}



	def swipeToBottom() {
		int screenHeight = Mobile.getDeviceHeight()
		int screenWidth = Mobile.getDeviceWidth()
		int startX = screenWidth / 2
		int startY = (int)(screenHeight * 0.8)
		int endY = (int)(screenHeight * 0.2)
		Mobile.swipe(startX, startY, startX, endY)
	}

	def scrollToAnchor(TestObject item, TestObject anchor) {
		int itemTop = Mobile.getElementTopPosition(item, 5)
		int anchorBottom = Mobile.getElementTopPosition(anchor, 5) + Mobile.getElementHeight(anchor, 5)
		int offset = itemTop - anchorBottom
		if (offset > 0) {
			int centerX = Mobile.getDeviceWidth() / 2
			int deviceHeight = Mobile.getDeviceHeight()
			int stepSize = (int)(deviceHeight * 0.09)
			int totalSteps = Math.ceil(offset / stepSize)

			for (int i = 0; i < totalSteps; i++) {
				int currentStartY = itemTop - (i * stepSize)
				int currentEndY = currentStartY - stepSize

				if (currentEndY < anchorBottom) {
					currentEndY = anchorBottom
				}
				Mobile.swipe(centerX, currentStartY, centerX, currentEndY)
				Mobile.delay(0.5)
			}
		}
	}

	boolean isDisplayed(TestObject element) {
		isDisplayed(element, 1)
	}

	boolean isDisplayed(TestObject to, int timeout) {
		WebDriver driver = MobileDriverFactory.getDriver()
		Duration defaultTimeout = driver.manage().timeouts().getImplicitWaitTimeout()
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout))
		def elements = driver.findElements(convertToBy(to))
		for (def element in elements) {
			if (element.isDisplayed()) {
				return true
			}
		}
		driver.manage().timeouts().implicitlyWait(defaultTimeout)
		return false
		//		return WebUiCommonHelper.findWebElement(element, timeout).isDisplayed()
		//		boolean status = Mobile.verifyElementVisible(element, timeout, FailureHandling.CONTINUE_ON_FAILURE)
		//		return status
	}

	boolean isExisted(TestObject element) {
		//		boolean status = Mobile.verifyElementExist(element, 1, FailureHandling.CONTINUE_ON_FAILURE)
		//		return status
	}

	/**
	 * Wait until element attached to DOM
	 *
	 * @param by to be checked
	 * @throws TimeoutException
	 */

	def waitForPresentOf(TestObject testObject) {
		WebDriver driver = MobileDriverFactory.getDriver()
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

	def waitForNotPresentOf(By by) {
		WebDriver driver = MobileDriverFactory.getDriver()
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3))
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
		wait.until {driver.findElements(by).size() == 0}
	}


	def waitForVisibilityOf(TestObject testObject) {
		WebElement element = convertTestObjectToWebElement(testObject)
		return waitForCondition(testObject) { ExpectedConditions.visibilityOf(element) }
	}

	def waitForInVisibilityOf(TestObject testObject) {
		WebElement element = convertTestObjectToWebElement(testObject)
		return waitForCondition(testObject) { ExpectedConditions.invisibilityOf(element) }
	}

	def waitForAttributeValueOf(TestObject testObject, String attributeName, String expectedValue, int timeout = TIMEOUT) {
		WebDriver driver = MobileDriverFactory.getDriver()
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout))
		By locator = convertToBy(testObject)

		wait.until {
			List<WebElement> elements = driver.findElements(locator)
			if (elements.isEmpty()) return false

			String actualValue = elements[0].getAttribute(attributeName)
			return actualValue == expectedValue
		}
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

	By convertToBy(TestObject to) {
		List<TestObjectProperty> properties = to.getProperties()

		if (properties == null || properties.isEmpty()) {
			throw new IllegalArgumentException("TestObject has no properties defined")
		}
		TestObjectProperty prop = properties.get(0)
		String method = prop.getName().toUpperCase()
		String locator = prop.getValue()

		switch (method) {
			case "XPATH": return By.xpath(locator)
			case "CSS": return By.cssSelector(locator)
			case "RESOURCE-ID": return By.id(locator)
			case "NAME": return By.name(locator)
			case "CLASS_NAME": return By.className(locator)
			default:
				throw new IllegalArgumentException("Unsupported locator strategy: " + method)
		}
	}
}
