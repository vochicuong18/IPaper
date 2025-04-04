package utilities

import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling


public class AssertUtilities {
	public static void checkEquals(String gui, String data) {
		try {
			Mobile.verifyEqual(gui, data, FailureHandling.OPTIONAL)
		} catch (StepFailedException e) {
			Utilities.log("Verify equals GUI: ${gui} - DATA: ${data}")
		}
	}
}
