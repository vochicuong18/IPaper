import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.main.CustomKeywordDelegatingMetaClass
import com.kms.katalon.core.annotation.Keyword

import drivers.Driver
import utilities.DataTest
class TestListener {

	@BeforeTestSuite
	def beforeSuite(TestSuiteContext testSuiteContext) {
		if (!CustomKeywords.'drivers.Driver.isAppiumRunning'())
			CustomKeywords.'drivers.Driver.startAppium'()

		Driver.driver = Driver.driver ?: CustomKeywords.'drivers.Driver.initMobileDriver'()
		CustomKeywords.'drivers.Driver.openApp'()
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
		if (testCaseContext.getTestCaseId() != 'Test Cases') {
			if (testCaseContext.getTestCaseStatus() != 'PASSED') {
				Mobile.takeScreenshot("ErrorScreen/${testCaseContext.getTestCaseId()}.png")
			}
		}
		
		if (testCaseContext.getTestCaseStatus() != 'FAILED') {
			CustomKeywords.'drivers.Driver.closeApp'()
		}
		CustomKeywords.'drivers.Driver.stopAppium'()
	}

	@AfterTestSuite
	def afterSuite(TestSuiteContext testSuiteContext) {
		CustomKeywords.'drivers.Driver.closeApp'()
		if (testSuiteContext.getTestSuiteId() != 'Test Suites/Test') {
			CustomKeywords.'drivers.Driver.stopAppium'()
		}
	}
}