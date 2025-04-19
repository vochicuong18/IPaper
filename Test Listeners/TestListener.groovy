import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import drivers.Driver
import utilities.DataTest
class TestListener {

	@BeforeTestSuite
	def beforeSuite(TestSuiteContext testSuiteContext) {
		if (!CustomKeywords.'drivers.Driver.isAppiumRunning'())
			CustomKeywords.'drivers.Driver.startAppium'()

		Driver.driver = Driver.driver ?: CustomKeywords.'drivers.Driver.initMobileDriver'()
		//		CustomKeywords.'drivers.Driver.openApp'()
	}

	@BeforeTestCase
	def beforeTest(TestCaseContext testCaseContext) {
		DataTest.init()
		if (!CustomKeywords.'drivers.Driver.isAppiumRunning'())
			CustomKeywords.'drivers.Driver.startAppium'()

		Driver.driver = Driver.driver ?: CustomKeywords.'drivers.Driver.initMobileDriver'()

		CustomKeywords.'drivers.Driver.openApp'()
	}

	@AfterTestCase
	def afterTest(TestCaseContext testCaseContext) {
		Mobile.takeScreenshot("ErrorScreen/${testCaseContext.getTestCaseId()}.png")
		
		if (testCaseContext.getTestCaseId() != 'Test Cases') {
			if (testCaseContext.getTestCaseStatus() != 'PASSED') {
//				Mobile.takeScreenshot("ErrorScreen/${testCaseContext.getTestCaseId()}.png")
			}
		}
		CustomKeywords.'drivers.Driver.closeApp'()
		CustomKeywords.'drivers.Driver.stopAppium'()
	}

	@AfterTestSuite
	def afterSuite(TestSuiteContext testSuiteContext) {
		//		CustomKeywords.'drivers.Driver.closeApp'()
		if (testSuiteContext.getTestSuiteId() != 'Test Suites/Test') {
			CustomKeywords.'drivers.Driver.stopAppium'()
		}
	}
}