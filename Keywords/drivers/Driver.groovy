package drivers

import org.openqa.selenium.remote.DesiredCapabilities

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import data.Capabilities
import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import utilities.DataTest
import utilities.Utilities

public class Driver {

	private static final String APPIUM_IP_ADDRESS = "127.0.0.1"
	private static final int APPIUM_PORT = 4723
	private static final String APPIUM_BASE_PATH = "/wd/hub"
	private static final String APPIUM_LOG_LEVEL = "info"
	private static final String JAVA_ENCODING = "UTF-8"

	public static AppiumDriverLocalService service
	public static AppiumDriver driver

	@Keyword
	def startAppium() {
		def buildService = new AppiumServiceBuilder()
				.withIPAddress(APPIUM_IP_ADDRESS)
				.usingPort(APPIUM_PORT)
				.withArgument({ "--base-path" }, APPIUM_BASE_PATH)
				.withEnvironment(["JAVA_TOOL_OPTIONS": "-Dfile.encoding=UTF-8"])
		service = AppiumDriverLocalService.buildService(buildService);
		Utilities.logInfo("Appium will be started...")
		service.start()
	}

	@Keyword
	def stopAppium() {
		if(isAppiumRunning()) {
			Utilities.logInfo("Appium will be stopped...")
			service.stop()
		}
	}

	@Keyword
	def isAppiumRunning() {
		boolean status = service != null && service.isRunning()
		Utilities.logInfo("Appium running: ${status.toString()}")
		return status
	}

	// FOR ADB ==================================================================================================================
	@Keyword
	def startAppADB() {
	}

	@Keyword
	def forceStopAppADB() {
	}


	// FOR APP ==================================================================================================================
	@Keyword
	def openApp() {
		String appPath = DataTest.APP[GlobalVariable.PLATFORM]
		GlobalVariable.PLATFORM == 'Android' ? Mobile.startApplication(appPath, false) : Mobile.startExistingApplication(appPath)
		Mobile.delay(1)
	}

	@Keyword
	def closeApp() {
		Mobile.closeApplication()
	}

	@Keyword
	def initMobileDriver() {
		DesiredCapabilities cap = new DesiredCapabilities();
		URL appiumServerURL = new URL("http://127.0.0.1:4723/wd/hub");
		def platformCaps = Capabilities.CAP[GlobalVariable.PLATFORM]
		if (GlobalVariable.PLATFORM == 'Android') {
			cap.setCapability("platformName", platformCaps.platformName)
			cap.setCapability("appium:deviceName", platformCaps.deviceName)
			cap.setCapability("appium:automationName", platformCaps.automationName)
			cap.setCapability("appium:noReset", platformCaps.noReset)
			cap.setCapability("appium:fullReset", platformCaps.fullReset)
			cap.setCapability("appium:waitForIdleTimeout", platformCaps.waitForIdleTimeout)
			return new AndroidDriver(appiumServerURL, cap)
		}
		else if (GlobalVariable.PLATFORM == 'iOS') {
			cap.setCapability("platformName", platformCaps.platformName)
			cap.setCapability("appium:platformVersion", platformCaps.platformVersion)
			cap.setCapability("appium:deviceName", platformCaps.deviceName)
			cap.setCapability("appium:udid", platformCaps.udid)
			cap.setCapability("appium:automationName", platformCaps.automationName)
			cap.setCapability("appium:noReset", platformCaps.noReset)
			return new IOSDriver(appiumServerURL, cap)
		}
	}

	@Keyword
	def terminateApp() {
	}

	@Keyword
	def installApp(String app_package) {
	}

	@Keyword
	def uninstallApp(String app_package) {
	}
}
