package utilities

import com.kms.katalon.core.logging.KeywordLogger;
import com.kms.katalon.core.util.KeywordUtil

import base.BaseApp
import drivers.Driver
import internal.GlobalVariable


public class Utilities extends BaseApp{
	private static final KeywordLogger logger = KeywordLogger.getInstance(KeywordUtil.class);
	static String testCaseId

	def static openOutlookApp() {
		String bundleIdOrPackage = GlobalVariable.PLATFORM == 'Android' ? "com.microsoft.office.outlook" : "com.microsoft.Office.Outlook"
		Driver.driver.activateApp(bundleIdOrPackage)
	}

	def static openFileManagerApp() {
		String bundleIdOrPackage = GlobalVariable.PLATFORM == 'Android' ? "com.coloros.filemanager" : "com.apple.DocumentsApp"
		Driver.driver.activateApp(bundleIdOrPackage)
	}


	def static openIPaperApp() {
		Utilities utility = new Utilities()
		String bundleIdOrPackage = DataTest.APP[GlobalVariable.PLATFORM]
		Driver.driver.activateApp(bundleIdOrPackage)
		utility.waitAppLauch()
	}

	def static closeCurentApp() {
		def capabilities = Driver.driver.getCapabilities()

		String appId = null
		if (GlobalVariable.PLATFORM == 'Android') {
			appId = capabilities.getCapability("appPackage")
		} else {
			appId = capabilities.getCapability("bundleId")
		}

		if (appId instanceof String && appId) {
			Driver.driver.terminateApp(appId)
		}
	}

	def static getOS() {
		def osName = System.getProperty("os.name").toLowerCase()
		if (osName.contains("win")) {
			return "Windows"
		}
		else if (osName.contains("mac")) {
			return "Mac"
		}
		else {
			return "Other"
		}
	}

	def static logInfo(String text) {
		KeywordUtil.logInfo(text)
	}

	def static logPass(String text) {
		logger.logPassed(text)
	}

	def static logFailed(String text) {
		logger.logFailed(text)
	}


	def static runCommand(String command) {
		def process
		if (getOS() == 'Windows') {
			process = ["cmd", "/c", command].execute()
		}
		else {
			process =  ["zsh", "-c", command].execute()
		}


		def reader = new BufferedReader(new InputStreamReader(process.getInputStream()))

		StringBuilder output = new StringBuilder()
		String line

		while ((line = reader.readLine()) != null) {
			output.append(line).append("\n")
		}

		process.waitFor() // Wait for the process to finish
		return output.toString()
	}


	def static back() {
		if (GlobalVariable.PLATFORM == "Android") {
			Utilities.runCommand("adb shell input keyevent 4")
		} else {
		}
	}
}
