package locator

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

public class CalendarLocator extends TestObjectFactory{
	TestObject cancelBtn, currentDate, day, nextBtn, previousBtn, selectedDate, submitBtn, year, yearItems,
	closeMonthYearSelected, currentMonthYear, dayItem, monthItems, submitDateBtn

	CalendarLocator () {
		switch (GlobalVariable.PLATFORM) {
			case 'Android':
				cancelBtn = createTestObject(LocatorType.XPATH, "//android.widget.Button[@resource-id='android:id/button2']")
				currentDate = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='android:id/date_picker_header_date']")
				nextBtn = createTestObject(LocatorType.ID, "android:id/next")
				previousBtn = createTestObject(LocatorType.ID, "android:id/prev")
				selectedDate = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='android:id/date_picker_header_date']")
				submitBtn = createTestObject(LocatorType.ID, "//android.widget.Button[@resource-id='android:id/button1']")
				year = createTestObject(LocatorType.ID, "android:id/date_picker_header_year")
				break
			case 'iOS':
				closeMonthYearSelected = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='DatePicker.Hide']")
				currentDate = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[contains(@name, 'Today')]")
				currentMonthYear = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='DatePicker.Show']")
				nextBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='DatePicker.NextMonth']")
				previousBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='DatePicker.PreviousMonth']")
				selectedDate = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='android:id/date_picker_header_date']")
				submitBtn = createTestObject(LocatorType.XPATH, "")
				submitDateBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Đồng ý']")
				monthItems = createTestObject(LocatorType.XPATH, "(//XCUIElementTypePickerWheel)[1]")
				yearItems = createTestObject(LocatorType.XPATH, "(//XCUIElementTypePickerWheel)[2]")
				break
		}
	}

	TestObject day (String day) {
		return createTestObject(LocatorType.XPATH, "//android.view.View[@text='${day}']")
	}

	TestObject yearItems (String year) {
		return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='android:id/text1' and @text='${year}']")
	}

	TestObject dayItem (String day) {
		return createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[.//XCUIElementTypeStaticText[@name='${day}']]")
	}
}
