package locator

import static entities.LocatorType.ID
import static entities.LocatorType.XPATH

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import entities.User
import internal.GlobalVariable

public class PDFSignLocator extends TestObjectFactory{
	TestObject screenTitle, acceptErrorButton, errorMessagePopup, relatedTxt, actionBtn, getOpinionAction, saveFormAction, assignerEmailSearch, loadingMask,
	sendFormAction, assignerItem, mainFile, subFile, assignerTxt, opinionNoteTxt, submitDatePickerBtn, backBtn, priorityItem,
	submitFormBtn, descriptionTxt, priorityValue, titleTxt, doneBtn, relatedEmailSearch, doneTime, relatedMemberItem, iosOpenDatePicker, listUserLoadingMask, priorityTitle, emptyListCC,
	getCommentDocument, ignoreButton, addDefineProcess, userItemDefineProcess, submitUseSelection, searchEmailDefineProcess, listDefineProcess, submitProcess, bellIcon
	PDFSignLocator () {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				screenTitle = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_title")
				acceptErrorButton = createTestObject(LocatorType.XPATH, "//android.widget.FrameLayout[@resource-id='com.hdbank.ipaper:id/action_bar_root']//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/btn_ok']")
				errorMessagePopup = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_mes")
				relatedTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_text_cc")
				actionBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/fab")
				getOpinionAction = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/fab3")
				saveFormAction = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/fab1")
				assignerEmailSearch = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_search_email")
				loadingMask = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/loading_image")
				sendFormAction = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/fab2")
				mainFile = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_main_file_click")
				subFile = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/view_attach_file_click")
				assignerTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_text_assign")
				opinionNoteTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/edit_note")
				submitDatePickerBtn = createTestObject(LocatorType.ID, "android:id/button1")
				backBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_home_bar")
				submitFormBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/btn_ok_user")
				descriptionTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_value_note")
				priorityValue = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_value_priority")
				titleTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/edit_tv_title")
				doneBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_menu")
				relatedEmailSearch = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_search_email")
				doneTime = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_due_date")
				getCommentDocument = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text='Lấy ý kiến']/following-sibling::android.widget.ImageButton[1]")
				ignoreButton = createTestObject(LocatorType.XPATH, "")

				addDefineProcess = createTestObject(XPATH, "//android.widget.ImageView[@resource-id='com.hdbank.ipaper:id/icAddDefine']")
				submitUseSelection = createTestObject(ID, "com.hdbank.ipaper:id/btn_ok_user")
				searchEmailDefineProcess = createTestObject(ID, "com.hdbank.ipaper:id/search_src_text")
			//				processType = createTestObject(ID, "com.hdbank.ipaper:id/spinner_type")
				listDefineProcess = createTestObject(ID, "com.hdbank.ipaper:id/lvDefineNewTitle")
				break
			case "iOS":
				screenTitle = createTestObject(LocatorType.XPATH, "//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText")
				actionBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic_floating_action']")
				getOpinionAction = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Lấy ý kiến']//parent::XCUIElementTypeOther")
				assignerEmailSearch = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic-search']//following-sibling::XCUIElementTypeTextField")
				iosOpenDatePicker = createTestObject(LocatorType.XPATH, "//XCUIElementTypeDatePicker")
				relatedTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Cc']")
				listUserLoadingMask = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='footer_bar']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther")
				saveFormAction = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic_save']")
				assignerTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Email']")
				mainFile = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Tải lên tài liệu PDF']//ancestor::XCUIElementTypeCell")
				sendFormAction = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Gửi duyệt']/parent::*")
				backBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Xong']")
				opinionNoteTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Ý kiến']//parent::XCUIElementTypeOther//XCUIElementTypeTextView")
				subFile = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Tải lên file đính kèm liên quan']")
				descriptionTxt = createTestObject(LocatorType.XPATH, "(//XCUIElementTypeStaticText[@name='Mô tả:'])[1]//following-sibling::XCUIElementTypeTextView")
				submitFormBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Gửi']")
				doneBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Xong']")
				priorityTitle = createTestObject(LocatorType.XPATH, "(//XCUIElementTypeStaticText[@name='Ưu tiên:'])[2]")
				titleTxt = createTestObject(LocatorType.XPATH, "(//XCUIElementTypeTable//XCUIElementTypeTextView)[1]")
				doneTime = createTestObject(LocatorType.XPATH, "(//XCUIElementTypeStaticText[@name='Ngày hoàn thiện'])[2]//following-sibling::XCUIElementTypeButton")
				priorityValue = createTestObject(LocatorType.XPATH, "(//XCUIElementTypeButton[@name='ic edit pen'])[1]/preceding-sibling::*[1]")
				emptyListCC = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTable[@name='Empty list']")
				relatedEmailSearch = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic-search']//following-sibling::XCUIElementTypeTextField")
				getCommentDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Lấy ý kiến']//parent::XCUIElementTypeOther/XCUIElementTypeOther")
				ignoreButton = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Bỏ qua']")
				addDefineProcess = createTestObject(XPATH, "//XCUIElementTypeButton[@name='ic mis add option']")
				submitUseSelection = createTestObject(XPATH, "//XCUIElementTypeButton[@name='Xác nhận']")
				searchEmailDefineProcess = createTestObject(XPATH, "//XCUIElementTypeSearchField[@name='Tìm kiếm']")
				listDefineProcess = createTestObject(XPATH, "//XCUIElementTypeStaticText[@name='quy trình đã được định nghĩa']")
				submitProcess = createTestObject(XPATH, "//XCUIElementTypeButton[@name='Xác nhận']")
				bellIcon = createTestObject(XPATH, "//XCUIElementTypeButton[@name='ic bell']")
				break
		}
	}

	TestObject assignerItem (String userEmail) {
		switch (GlobalVariable.PLATFORM) {
			case "Android": return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_email' and contains(@text, '${userEmail}')]")
			case "iOS": return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[contains(@label, '${userEmail}')]/parent::*")
		}
	}

	TestObject priorityItem (String priority) {
		switch (GlobalVariable.PLATFORM) {
			case "Android": return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_filter' and @text='${priority}']")
			case "iOS": return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Ưu tiên']/parent::XCUIElementTypeOther//XCUIElementTypeStaticText[@name='${priority}']//following-sibling::*[1]")
		}
	}

	TestObject relatedMemberItem (String userEmail) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_email' and contains(@text, '${userEmail}')]")
			case "iOS": return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[contains(@label, '${userEmail}')]/parent::*")
		}
	}

	TestObject userItemDefineProcess (User user) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":return createTestObject(LocatorType.XPATH, "//android.widget.RelativeLayout[descendant::android.widget.TextView[@resource-id='com.hdbank.ipaper:id/content_tv' and contains(@text, '${user.getEmail()}')]]")
			case "iOS": return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[contains(@name, '${user.getEmail()}')]/parent::XCUIElementTypeCell")
		}
	}

	TestObject processDefined(String processName) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tvContent' and @text='${processName}']")
			case "iOS": return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Quy trình đã định nghĩa']/parent::XCUIElementTypeOther//XCUIElementTypeStaticText[@name='${processName}']")
		}
	}
}