import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import drivers.Driver
import internal.GlobalVariable
import utilities.DataTest
import utilities.Utilities
class TestListener {

	protected def tsContext


	@BeforeTestSuite
	def beforeSuite(TestSuiteContext testSuiteContext) {
		this.tsContext = testSuiteContext
		logReportFolder()
		CustomKeywords.'drivers.Driver.startAppium'()
		Driver.driver = Driver.driver ?: CustomKeywords.'drivers.Driver.initMobileDriver'()
	}

	@BeforeTestCase
	def beforeTest(TestCaseContext testCaseContext) {
		DataTest.init()
		Utilities.testCaseId = testCaseContext.getTestCaseId().split("/").last()
		if (!CustomKeywords.'drivers.Driver.isAppiumRunning'()) {
			CustomKeywords.'drivers.Driver.startAppium'()
		}

		if (Driver.driver == null || Driver.driver.getSessionId() == null) {
			Driver.driver = CustomKeywords.'drivers.Driver.initMobileDriver'()
		}

		CustomKeywords.'drivers.Driver.openApp'()
	}

	@AfterTestCase
	def afterTest(TestCaseContext testCaseContext) {
		if(testCaseContext.testCaseStatus != "PASSED") {
			Mobile.takeScreenshot("ErrorScreen/${GlobalVariable.PLATFORM}/${testCaseContext.getTestCaseId()}.png")
		}
		CustomKeywords.'drivers.Driver.closeApp'()

		if(this.tsContext == null) {
			CustomKeywords.'drivers.Driver.stopAppium'()
		}
	}

	@AfterTestSuite
	def afterSuite(TestSuiteContext testSuiteContext) {
		CustomKeywords.'drivers.Driver.stopAppium'()
//		CustomKeywords.'drivers.Driver.closeApp'()
//		Utilities.runCommand("cd Plugins/allure && python3 allure-for-kes.py --platform android && allure serve allure-results-android")
	}

	def logReportFolder() {
		String report_folder = RunConfiguration.getReportFolder()
		String report_logs = "Reports/reports_${GlobalVariable.PLATFORM}.log".toLowerCase()
		File file = new File(report_logs)
		file.withWriterAppend('UTF-8') { writer ->
			writer.writeLine(report_folder)
		}
	}
}