package utilities

import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.util.KeywordUtil


class AssertUtilities {
	private static final KeywordLogger logger = KeywordLogger.getInstance(KeywordUtil.class)

	static void checkEquals(String gui, String data) {
		logResult(gui == data, "Verify equals GUI: $gui - DATA: $data")
	}

	static void checkContains(String gui, String data) {
		logResult(gui?.contains(data), "Verify contains GUI: $gui - DATA: $data")
	}

	static void assertTrue(boolean condition, String message = "Expected true condition") {
		logResult(condition, message)
	}

	static void assertFalse(boolean condition, String message = "Expected false condition") {
		logResult(!condition, message)
	}
	
	private static void logResult(boolean passed, String message) {
		if (passed) {
			logger.logPassed(message)
		} else {
			KeywordUtil.markFailed(message)
		}
	}
}


