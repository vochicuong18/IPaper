package base
import java.time.Duration
import java.util.concurrent.TimeUnit

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Pause
import org.openqa.selenium.interactions.PointerInput
import org.openqa.selenium.interactions.PointerInput.Origin
import org.openqa.selenium.interactions.Sequence
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty

import drivers.Driver
import internal.GlobalVariable
import utilities.Utilities

trait BaseKeyword{
	final int TIMEOUT = 30

	/**
	 * Hides the keyboard on the mobile device.
	 */
	def hideKeyboard() {
		if (GlobalVariable.PLATFORM == 'Android') {
			Mobile.hideKeyboard()
		} else {
//			Driver.driver.findElementByAccessibilityId("Hide keyboard").click();
		}
	}

	/**
	 * Gets the value of a specified attribute from a TestObject.
	 * @param to TestObject to get attribute from
	 * @param attribute Name of the attribute to retrieve 
	 * @return value of the specified attribute as String
	 */
	String getValueAttributeOf(TestObject to, String attribute) {
		WebElement element = Driver.driver.findElement(convertToBy(to))
		println element.getAttribute(attribute)
		return element.getAttribute(attribute)
	}

	/**
	 * Gets the text content from a TestObject.
	 * @param to TestObject to get text from
	 * @return String containing the text of the element
	 */
	String getText(TestObject to) {
		WebElement element = Driver.driver.findElement(convertToBy(to))
		return element.getText()
	}

	/**
	 * Sets text in an input field after clearing its current content.
	 * @param to TestObject representing the input field
	 * @param text Text to input
	 */
	def inputText(TestObject to, String text) {
		WebElement element = Driver.driver.findElement(convertToBy(to))
		element.clear()
		element.sendKeys(text)
	}

	/**
	 * Submits text input by pressing Enter/Return key.
	 * Uses platform-specific approach for Android vs iOS.
	 * @param to TestObject representing the input field
	 */
	def enterText(TestObject to) {
		if (GlobalVariable.PLATFORM == "Android") {
			Utilities.runCommand("adb shell input keyevent 66")
			println("A")
		} else {
			WebElement element = Driver.driver.findElement(convertToBy(to))
			element.sendKeys(Keys.RETURN)
		}
	}

	/**
	 * Clicks on a TestObject element.
	 * @param to TestObject to click
	 */
	def clickToElement(TestObject to) {
		def element = Driver.driver.findElement(convertToBy(to))
		element.click()
	}

	/**
	 * Taps at specific x,y coordinates on the screen.
	 * @param x X-coordinate to tap
	 * @param y Y-coordinate to tap
	 */
	def tapAt(int x, int y) {
		def driver = MobileDriverFactory.getDriver()
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger")
		def sequence = new Sequence(finger, 0)
		sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y))
		sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
		Driver.driver.perform(Arrays.asList(sequence))
	}

	/**
	 * Performs a tap and hold (long press) on a TestObject element.
	 * @param to TestObject to tap and hold
	 */
	def tapAndHold(TestObject to) {
		WebElement element = Driver.driver.findElement(convertToBy(to))

		int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2)
		int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2)

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger")
		Sequence longPress = new Sequence(finger, 0)

		longPress.addAction(finger.createPointerMove(Duration.ofMillis(0), Origin.viewport(), centerX, centerY))
		longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		longPress.addAction(new Pause(finger, Duration.ofSeconds(1)))
		longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))

		Driver.driver.perform(Arrays.asList(longPress))
	}

	/**
	 * Taps outside a given element (20px to the left and up).
	 * @param to TestObject to tap outside of
	 */
	def tapOutSideElement(TestObject to) {
		WebElement element = Driver.driver.findElement(convertToBy(to))
		int xPosition = element.getLocation().getX() - 20
		int yPosition = element.getLocation().getY() - 20
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger")
		def sequence = new Sequence(finger, 0)
		sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), xPosition, yPosition))
		sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
		Driver.driver.perform(Arrays.asList(sequence))
	}

	/**
	 * Performs vertical swipe on iOS date picker elements.
	 * @param to TestObject to swipe
	 * @param direction Direction to swipe ("up" or "down")
	 */
	def verticalSwipeYearIOS(TestObject to, String direction) {
		WebElement element = Driver.driver.findElement(convertToBy(to))
		int xPosition = element.getLocation().getX() + 20
		int yStart = element.getLocation().getY() + 100
		int yEnd = direction == "down" ? yStart + 30 : yStart - 20
		println "Swipe from (${xPosition}, ${yStart}) to (${xPosition}, ${yEnd})"
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger")
		Sequence swipe = new Sequence(finger, 0)

		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), xPosition, yStart))
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), xPosition, yEnd))
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
		Driver.driver.perform([swipe])
	}

	/**
	 * Performs a vertical swipe on the screen.
	 * @param direction Direction to swipe ("up" or "down")
	 * @param distancePercent Percentage of screen height to swipe (default: 0.2)
	 */
	def swipe(String direction, double distancePercent = 0.2) {
		def size = Driver.driver.manage().window().getSize()
		int startX = size.getWidth() / 2
		int startY = size.getHeight() / 2
		int distance = (size.getHeight() * distancePercent) as int
		int endY = direction == 'down' ? startY - distance : startY + distance

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger")
		Sequence swipe = new Sequence(finger, 1)

		swipe.addAction(finger.createPointerMove(Duration.ZERO, Origin.viewport(), startX, startY))
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), Origin.viewport(), startX, endY))
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
		Driver.driver.perform(Arrays.asList(swipe))
	}

	/**
	 * Swipes down repeatedly until a specified element is visible.
	 * @param element TestObject to swipe to
	 * @throws StepFailedException if element not found after MAX_ATTEMPTS
	 */
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

	/**
	 * Performs horizontal swipe from an element in specified direction.
	 * @param to TestObject to swipe from
	 * @param direction Direction to swipe ("left" or "right")
	 */
	def horizontalSwipeFromElement(TestObject to, String direction) {
		WebElement element = Driver.driver.findElement(convertToBy(to))

		int screenWidth = Driver.driver.manage().window().getSize().getWidth()
		int elementX = element.getLocation().getX()
		int elementY = element.getLocation().getY()
		int elementCenterX = elementX + 100
		int elementCenterY = elementY + 50
		int swipeDistance = (int)(screenWidth * 0.5)
		int startX = elementCenterX
		int endX = direction == "right" ? startX + swipeDistance : startX - swipeDistance
		performSwipe(Driver.driver, startX, elementCenterY, endX, elementCenterY)
	}

	/**
	 * Swipes from bottom to top of screen (revealing content below).
	 */
	def swipeToBottom() {
		int screenHeight = Driver.driver.manage().window().getSize().getHeight()
		int screenWidth = Driver.driver.manage().window().getSize().getWidth()
		int startX = screenWidth / 2
		int startY = (int)(screenHeight * 0.8)
		int endY = (int)(screenHeight * 0.2)
		performSwipe(Driver.driver, startX, startY, startX, endY)
	}

	/**
	 * Scrolls to make an item visible relative to an anchor element.
	 * @param item TestObject to scroll to
	 * @param anchor TestObject to use as reference point
	 */
	def scrollToAnchor(TestObject item, TestObject anchor) {
		WebElement itemEl = Driver.driver.findElement(convertToBy(item))
		WebElement anchorEl = Driver.driver.findElement(convertToBy(anchor))
		int itemTop = itemEl.getLocation().getY()
		int anchorBottom = anchorEl.getLocation().getY() + anchorEl.getSize().getHeight()
		int offset = itemTop - anchorBottom

		if (offset > 0) {
			int centerX = Driver.driver.manage().window().getSize().getWidth() / 2
			int deviceHeight = Driver.driver.manage().window().getSize().getHeight()
			int stepSize = (int)(deviceHeight * 0.09)
			int totalSteps = Math.ceil(offset / stepSize)

			for (int i = 0; i < totalSteps; i++) {
				int currentStartY = itemTop - (i * stepSize)
				int currentEndY = Math.max(currentStartY - stepSize, anchorBottom)
				performSwipe(Driver.driver, centerX, currentStartY, centerX, currentEndY)
				Thread.sleep(500)
			}
		}
	}


	//	def scrollToAnchor(TestObject item, TestObject anchor) {
	//		int itemTop = Mobile.getElementTopPosition(item, 5)
	//		int anchorBottom = Mobile.getElementTopPosition(anchor, 5) + Mobile.getElementHeight(anchor, 5)
	//		int offset = itemTop - anchorBottom
	//		if (offset > 0) {
	//			int centerX = Mobile.getDeviceWidth() / 2
	//			int deviceHeight = Mobile.getDeviceHeight()
	//			int stepSize = (int)(deviceHeight * 0.09)
	//			int totalSteps = Math.ceil(offset / stepSize)
	//
	//			for (int i = 0; i < totalSteps; i++) {
	//				int currentStartY = itemTop - (i * stepSize)
	//				int currentEndY = currentStartY - stepSize
	//
	//				if (currentEndY < anchorBottom) {
	//					currentEndY = anchorBottom
	//				}
	//				Mobile.swipe(centerX, currentStartY, centerX, currentEndY)
	//				Mobile.delay(0.5)
	//			}
	//		}
	//	}

	/**
	 * Performs a swipe gesture from one point to another.
	 * @param driver WebDriver instance
	 * @param startX Starting X coordinate
	 * @param startY Starting Y coordinate
	 * @param endX Ending X coordinate
	 * @param endY Ending Y coordinate
	 */
	def performSwipe(driver, int startX, int startY, int endX, int endY) {
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger")
		Sequence swipe = new Sequence(finger, 0)
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY))
		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY))
		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
		Driver.driver.perform([swipe])
	}

	/**
	 * Checks if a TestObject is displayed using default timeout.
	 * @param element TestObject to check
	 * @return true if element is displayed, false otherwise
	 */
	boolean isDisplayed(TestObject element) {
		isDisplayed(element, 3)
	}

	/**
	 * Checks if a TestObject is displayed with custom timeout.
	 * @param to TestObject to check
	 * @param timeout Timeout in seconds
	 * @return true if element is displayed, false otherwise
	 */
	boolean isDisplayed(TestObject to, int timeout) {
		Driver.driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS)
		def elements = Driver.driver.findElements(convertToBy(to))
		for (def element in elements) {
			if (element.isDisplayed()) {
				return true
			}
		}
		return false
	}

	/**
	 * Wait until element attached to DOM
	 *
	 * @param by to be checked
	 * @throws TimeoutException
	 */

	def waitForPresentOf(TestObject testObject) {
		WebDriverWait wait = new WebDriverWait(Driver.driver, TIMEOUT)
		wait.until {
			Driver.driver.findElements(convertToBy(testObject)).size() > 0
		}
	}

	/**
	 * Wait until element detached to DOM
	 *
	 * @param by to be checked
	 * @throws TimeoutException
	 */

	def waitForNotPresentOf(TestObject testObject) {
		Driver.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS)
		WebDriverWait wait = new WebDriverWait(Driver.driver, TIMEOUT)
		wait.until {
			Driver.driver.findElements(convertToBy(testObject)).size() == 0
		}
	}

	def waitForNotPresentOf(By by) {
		Driver.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS)
		WebDriverWait wait = new WebDriverWait(Driver.driver, TIMEOUT)
		wait.until {
			Driver.driver.findElements(by).size() == 0
		}
	}


	def waitForVisibilityOf(TestObject testObject) {
		WebElement element = convertTestObjectToWebElement(testObject)
		return waitForCondition(testObject) {
			ExpectedConditions.visibilityOf(element)
		}
	}

	def waitForInVisibilityOf(TestObject testObject) {
		WebElement element = convertTestObjectToWebElement(testObject)
		return waitForCondition(testObject) {
			ExpectedConditions.invisibilityOf(element)
		}
	}

	def waitForAttributeValueOf(TestObject testObject, String attributeName, String expectedValue, int timeout = TIMEOUT) {
		WebDriverWait wait = new WebDriverWait(Driver.driver, TIMEOUT)
		By locator = convertToBy(testObject)

		wait.until {
			List<WebElement> elements = Driver.driver.findElements(locator)
			if (elements.isEmpty()) return false
			String actualValue = elements[0].getAttribute(attributeName)
			return actualValue == expectedValue
		}
	}


	def waitForCondition(TestObject testObject, Closure<Boolean> condition) {
		WebDriverWait wait = new WebDriverWait(Driver.driver, TIMEOUT)
		return wait.until(condition)
	}

	WebElement convertTestObjectToWebElement(TestObject testObject) {
		return Driver.driver.findElement(convertToBy(testObject))
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
