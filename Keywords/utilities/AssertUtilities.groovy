package utilities

import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.util.KeywordUtil


public class AssertUtilities {
	private static final KeywordLogger logger = KeywordLogger.getInstance(KeywordUtil.class);

	public static void checkEquals(String gui, String data) {
		if (gui.equals(data)) {
			logger.logPassed("Verify equals GUI: ${gui} - DATA: ${data}")
		} else {
			logger.logFailed("Verify equals GUI: ${gui} - DATA: ${data}")
		}
	}


	public static void checkContains(String gui, String data) {
		if (gui.contains(data)) {
			logger.logPassed("Verify contains GUI: ${gui} - DATA: ${data}")
		} else {
			logger.logFailed("Verify contains GUI: ${gui} - DATA: ${data}")
		}
	}

	public static void assertTrue(boolean condition, String message = "Condition is not true") {
		if (condition) {
			logger.logPassed(message)
		} else logger.logFailed(message)i
	}

	public static void assertFalse(boolean condition, String message = "Condition is not true") {
		if (!condition) {
			logger.logPassed(message)
		} else logger.logFailed(message)i
	}
}
