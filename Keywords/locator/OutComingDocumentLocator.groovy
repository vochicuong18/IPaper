package locator

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable


public class OutComingDocumentLocator extends TestObjectFactory{
	TestObject approveWithCondition, documentItem, documentItems, fillterBtn, firstDocumentDate,
	firstDocumentTitle, headerBar, opinionTxt, quickApprove, relatedDocumentBtn, sendOpinionApprove, toastMessage, searchDocument, secondItem

	OutComingDocumentLocator() {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				secondItem = createTestObject(LocatorType.XPATH, "(//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item']//ancestor::android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/layout_inf']//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/view_infor'])[2]")

				fillterBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_filter")
				firstDocumentDate = createTestObject(LocatorType.XPATH, "(//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/view_infor'])[1]//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_date_approve']")
				firstDocumentTitle = createTestObject(LocatorType.XPATH, "(//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/view_infor'])[1]//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item']")
				headerBar = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_home_bar")
				opinionTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/edit_note")
				relatedDocumentBtn = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_filter' and @text='Hồ sơ liên quan']")
				sendOpinionApprove = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/btn_ok_user")
				toastMessage = createTestObject(LocatorType.XPATH, "//android.widget.Toast[@text=Thành công']")
				break
			case "iOS":
				approveWithCondition = createTestObject(LocatorType.XPATH, "")
				documentItem = createTestObject(LocatorType.XPATH, "")
				documentItems = createTestObject(LocatorType.XPATH, "")
				fillterBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic filter']")
				firstDocumentDate = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTable/XCUIElementTypeCell[1]//XCUIElementTypeStaticText[3]")
				firstDocumentTitle = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTable/XCUIElementTypeCell[1]//XCUIElementTypeStaticText[1]")
				headerBar = createTestObject(LocatorType.XPATH, "")
				opinionTxt = createTestObject(LocatorType.XPATH, "")
				relatedDocumentBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Hồ sơ liên quan']/parent::XCUIElementTypeCell")
				sendOpinionApprove = createTestObject(LocatorType.XPATH, "")
				toastMessage = createTestObject(LocatorType.XPATH, "")
				searchDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTextField[@value='Tìm kiếm hồ sơ']")
				break
		}
	}


	TestObject approveWithCondition(String documentTitle) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text='${documentTitle}']/parent::android.widget.RelativeLayout/parent::android.widget.LinearLayout//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/layout_btn_approve_condition']",[('documentTitle'): documentTitle])
			case "iOS":
				return createTestObject(LocatorType.XPATH, "", [('documentTitle'): documentTitle])
		}
	}

	TestObject documentItem(String documentTitle) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item' and @text='${documentTitle}']//ancestor::android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/layout_inf']", [('documentTitle'): documentTitle])
			case "iOS":
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='${documentTitle}']/parent::XCUIElementTypeCell", [('documentTitle'): documentTitle])
		}
	}

	TestObject documentItems(String index) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH,"(//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/view_infor'])[${index}]",[('index'): index])
			case "iOS":
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeTable/XCUIElementTypeCell[${index}]", [('index'): index])
		}
	}

	TestObject quickApprove(String documentTitle) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH,"//android.widget.TextView[@text='${documentTitle}']/parent::android.widget.RelativeLayout/parent::android.widget.LinearLayout//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/layout_btn_approve']",[('documentTitle'): documentTitle])
			case "iOS":
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='${documentTitle}']/parent::XCUIElementTypeCell/XCUIElementTypeStaticText[@name='Duyệt']", [('documentTitle'): documentTitle])
		}
	}
}
