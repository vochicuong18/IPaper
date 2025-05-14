
package locator

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

import drivers.Driver
import entities.LocatorType

public class TestObjectFactory {
	def TestObject createTestObject(LocatorType type, String locator, Map<String, Object> params = [:]) {
		if (params && !params.isEmpty()) {
			locator = replaceParams(locator, params)
		}
		TestObject to = new TestObject(locator)
		to.addProperty(type.toString(), ConditionType.EQUALS, locator)
		return to
	}

	List<TestObject> createTestObjects(LocatorType type, String locatorTemplate, Map<String, Object> params = [:]) {
		if (params && !params.isEmpty()) {
			locatorTemplate = replaceParams(locatorTemplate, params)
		}

		List<WebElement> elements
		List<TestObject> testObjects = []

		switch (type) {
			case LocatorType.XPATH:
				elements = Driver.driver.findElements(By.xpath(locatorTemplate))
				break
			case LocatorType.ID:
				elements = Driver.driver.findElements(By.id(locatorTemplate))
				break
			default:
				throw new IllegalArgumentException("Unsupported locator type: " + type)
		}


		// Create TestObjects for each element found
		for (int i = 1; i <= elements.size(); i++) {
			String indexedLocator = locatorTemplate + "[$i]"
			TestObject testObject = new TestObject()
			testObject.addProperty(type.toString(), ConditionType.EQUALS, indexedLocator)
			testObjects.add(testObject)
		}

		return testObjects
	}

	private String replaceParams(String locator, Map<String, Object> params) {
		params.each { k, v ->
			locator = locator.replace("${k}", v.toString())
		}
		return locator
	}
}
