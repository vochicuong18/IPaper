package locator

import com.kms.katalon.core.testobject.TestObject

import entities.DocumentStatus
import entities.LocatorType
import internal.GlobalVariable


public class IncomingDocumentLocator extends TestObjectFactory{
	TestObject loadingItem, approveWithCondition, documentItem, documentItems, fillterBtn, firstDocumentDate,
	firstDocumentTitle, headerBar, opinionTxt, quickApproveBtn, relatedDocumentBtn, sendOpinionApprove, toastMessage, searchDocument, documentTitle, secondItem, backBtn,
	filterDocumentStatusTab, requestType, submitWarningPopup, loadingSwipeIcon, imageViewLoading
	IncomingDocumentLocator () {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				loadingItem = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_loading_item")
				secondItem = createTestObject(LocatorType.XPATH, "(//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item']//ancestor::android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/layout_inf']//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/view_infor'])[2]")
				fillterBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_filter")
				firstDocumentDate = createTestObject(LocatorType.XPATH, "(//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/view_infor'])[1]//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_date_approve']")
				firstDocumentTitle = createTestObject(LocatorType.XPATH, "(//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/view_infor'])[1]//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item']")
				headerBar = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_home_bar")
				opinionTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/edit_note")
				relatedDocumentBtn = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_filter' and @text='Hồ sơ liên quan']")
				sendOpinionApprove = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/btn_ok_user")
				toastMessage = createTestObject(LocatorType.XPATH, "//android.widget.Toast[@text=Thành công']")
				backBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_home_bar")

				filterDocumentStatusTab = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tab_status")
				requestType = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_value_process_type")
				submitWarningPopup = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/btn_ok")
				loadingSwipeIcon = createTestObject(LocatorType.XPATH, "//android.view.ViewGroup[@resource-id='com.hdbank.ipaper:id/swipe_layout']/android.widget.ImageView")
				searchDocument = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_search_view")
				imageViewLoading = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_view_loading")
				break
			case "iOS":
				loadingItem = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_loading_item")
				secondItem = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item']//ancestor::android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/layout_inf'][2]")
			//				approveWithCondition = createTestObject(LocatorType.XPATH, "")
			//				documentItem = createTestObject(LocatorType.XPATH, "")
			//				documentItems = createTestObject(LocatorType.XPATH, "")
				fillterBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic filter']")
				firstDocumentDate = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTable/XCUIElementTypeCell[1]//XCUIElementTypeStaticText[3]")
				firstDocumentTitle = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTable/XCUIElementTypeCell[1]//XCUIElementTypeStaticText[1]")
				headerBar = createTestObject(LocatorType.XPATH, "")
				opinionTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Ý kiến']/parent::XCUIElementTypeOther/XCUIElementTypeTextView")
				relatedDocumentBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Hồ sơ liên quan']/parent::XCUIElementTypeCell")
				sendOpinionApprove = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Gửi']")
				toastMessage = createTestObject(LocatorType.XPATH, "")
				searchDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic-search']/parent::XCUIElementTypeOther/XCUIElementTypeTextField")
				backBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic back']")

				requestType = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Loại yêu cầu:']/following-sibling::XCUIElementTypeStaticText[contains(@name, 'Quy trình PDF')]")

				submitWarningPopup = createTestObject(LocatorType.XPATH, "")
				loadingSwipeIcon = createTestObject(LocatorType.XPATH, "//XCUIElementTypeActivityIndicator[@name='Progress halted']")
				imageViewLoading = createTestObject(LocatorType.XPATH, "")

				break
		}
	}


	TestObject approveWithCondition(String documentTitle) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text='${documentTitle}']/parent::android.widget.RelativeLayout/parent::android.widget.LinearLayout//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/layout_btn_approve_condition']")
			case "iOS":
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='${documentTitle}']/parent::XCUIElementTypeCell//XCUIElementTypeStaticText[@name='Duyệt với ĐK']")
		}
	}

	TestObject documentItem(String documentTitle) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item' and @text='${documentTitle}']")
			case "iOS":
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='${documentTitle}']/parent::XCUIElementTypeCell")
		}
	}

	TestObject documentItems(String index) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH,"(//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/view_infor'])[${index}]")
			case "iOS":
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeTable/XCUIElementTypeCell[${index}]")
		}
	}

	List<TestObject> titleItems() {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObjects(LocatorType.XPATH,"(//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item'])")
			case "iOS":
				return createTestObjects(LocatorType.XPATH, "")
		}
	}

	TestObject quickApproveBtn(String documentTitle) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH,"//android.widget.TextView[@text='${documentTitle}']/parent::android.widget.RelativeLayout/parent::android.widget.LinearLayout//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/layout_btn_approve']")
			case "iOS":
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='${documentTitle}']/parent::XCUIElementTypeCell/XCUIElementTypeStaticText[@name='Duyệt']")
		}
	}

	TestObject quickRejectBtn(String documentTitle) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH,"//android.widget.TextView[@text='${documentTitle}']/parent::android.widget.RelativeLayout/parent::android.widget.LinearLayout//android.widget.LinearLayout[@resource-id='com.hdbank.ipaper:id/layout_btn_reject']")
			case "iOS":
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='${documentTitle}']/parent::XCUIElementTypeCell/XCUIElementTypeStaticText[@name='Từ chối']")
		}
	}

	TestObject sendCommentBtn(documentTitle) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH,"//android.widget.TextView[@text='${documentTitle}']/parent::android.widget.RelativeLayout/parent::android.widget.LinearLayout//android.widget.TextView[@text='Gửi ý kiến']//parent::android.widget.LinearLayout")
			case "iOS":
				return createTestObject(LocatorType.XPATH, "")
		}
	}

	TestObject documentTitle (String documentTitle) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH,"//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_title_item' and @text='${documentTitle}']")
			case "iOS":
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='${documentTitle}']")
		}
	}

	TestObject documentStatusFilter(String documentStatus) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH,"//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_filter' and @text='${documentStatus}']")
			case "iOS":
				return createTestObject(LocatorType.XPATH, "")
		}
	}
}
