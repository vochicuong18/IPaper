package drivers

import org.openqa.selenium.remote.DesiredCapabilities

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.appium.driver.AppiumDriverManager
import com.kms.katalon.core.mobile.driver.MobileDriverType
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import base.BaseApp
import data.Capabilities
import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import utilities.DataTest
import utilities.Utilities

class Driver extends BaseApp {

	private static final String APPIUM_IP_ADDRESS = "127.0.0.1"
	private static final int APPIUM_PORT = 4723
	private static final String APPIUM_BASE_PATH = "/wd/hub"

	public static AppiumDriverLocalService service
	public static AppiumDriver driver

	@Keyword
	def startAppium() {
		service = AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder()
				.withIPAddress(APPIUM_IP_ADDRESS)
				.usingPort(APPIUM_PORT)
				.withArgument({ "--base-path" }, APPIUM_BASE_PATH)
				)
		service.start()
	}


	@Keyword
	def stopAppium() {
		if (isAppiumRunning()) {
			service.stop()
		}
	}

	@Keyword
	def isAppiumRunning() {
		boolean status = service != null && service.isRunning()
		Utilities.logInfo("Appium running: ${status}")
		return status
	}

	@Keyword
	def openApp() {
		String appPackage = DataTest.APP[GlobalVariable.PLATFORM]
		driver.activateApp(appPackage)
		waitAppLauch()
	}

	@Keyword
	def closeApp() {
		String appPackage = DataTest.APP[GlobalVariable.PLATFORM]
		driver.terminateApp(appPackage)
	}

	@Keyword
	def initMobileDriver() {
		Utilities.logInfo("Init driver")
		DesiredCapabilities cap = new DesiredCapabilities()
		URL appiumServerURL = new URL("http://${APPIUM_IP_ADDRESS}:${APPIUM_PORT}${APPIUM_BASE_PATH}")
		def platformCaps = Capabilities.CAP[GlobalVariable.PLATFORM]

		switch (GlobalVariable.PLATFORM) {
			case 'Android':
				setAndroidCapabilities(cap, platformCaps)
				return AppiumDriverManager.createMobileDriver(MobileDriverType.ANDROID_DRIVER, cap, appiumServerURL)
			case 'iOS':
				setIOSCapabilities(cap, platformCaps)
				return AppiumDriverManager.createMobileDriver(MobileDriverType.IOS_DRIVER, cap, appiumServerURL)
		}
	}

	private void setAndroidCapabilities(DesiredCapabilities cap, def platformCaps) {
		cap.setCapability("platformName", platformCaps.platformName)
		cap.setCapability("appium:deviceName", platformCaps.deviceName)
		cap.setCapability("appium:automationName", platformCaps.automationName)
		cap.setCapability("appium:noReset", platformCaps.noReset)
		cap.setCapability("appium:fullReset", platformCaps.fullReset)
		cap.setCapability("appium:waitForIdleTimeout", platformCaps.waitForIdleTimeout)
	}

	private void setIOSCapabilities(DesiredCapabilities cap, def platformCaps) {
		cap.setCapability("platformName", platformCaps.platformName)
		cap.setCapability("appium:udid", platformCaps.udid)
		cap.setCapability("appium:automationName", platformCaps.automationName)
		cap.setCapability("appium:noReset", platformCaps.noReset)
		cap.setCapability("appium:fullReset", platformCaps.fullReset)
	}

	@Keyword
	def terminateApp() {
		// Reserved for future implementation
	}

	@Keyword
	def installApp(String appPackage) {
		// Reserved for future implementation
	}

	@Keyword
	def uninstallApp(String appPackage) {
		// Reserved for future implementation
	}

	@Keyword
	def startAppADB() {
		// Reserved for future implementation
	}

	@Keyword
	def forceStopAppADB() {
		// Reserved for future implementation
	}
}
