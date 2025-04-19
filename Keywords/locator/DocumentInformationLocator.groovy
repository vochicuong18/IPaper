package locator

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

public class DocumentInformationLocator extends TestObjectFactory {

	TestObject assigner, subFile, backBtn, createDate, description, requestType, documentActionBtn, documentTitle, finishDate, getOpinoionTxt,
	menuIcon, mainFile, priority, processComment, rePresentTxt, sendApprovalTxt, senderName, status, submitTxt, approveDocument, getOpinionTxt, submitApproveBtn,fillterBtn,
	ccOnPopupTxt, rejectDocument, getComment, warningPopup, ignoreBtn
	DocumentInformationLocator () {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				assigner = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/email_tv")
				subFile = createTestObject(LocatorType.XPATH, "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.hdbank.ipaper:id/lv_attach_file']//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_version']")
				backBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_home_bar")
				createDate = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_value_status_date")
				description = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_value_note")
				documentActionBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/fab")
				documentTitle = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_title_item")
				finishDate = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_value_due_date")
				getOpinoionTxt = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text = 'Lấy ý kiến']")
				menuIcon = createTestObject(LocatorType.XPATH, "//android.widget.RelativeLayout[@resource-id='com.hdbank.ipaper:id/layout_home_bar']")
				mainFile = createTestObject(LocatorType.XPATH, "//android.widget.RelativeLayout[@resource-id='com.hdbank.ipaper:id/lv_main_file']//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_version']")
				priority = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_value_priority")
				rePresentTxt = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text = 'Trình lại']")
				sendApprovalTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_approve")
				senderName = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_value_author")
				status = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_value_status")
				submitTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_submit")
				approveDocument = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text='Duyệt']/following-sibling::android.widget.ImageButton[1]")
				getOpinionTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/edit_note")
				submitApproveBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/btn_ok_user")
				fillterBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_filter")
				ccOnPopupTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/et_auto_cc")
				rejectDocument = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text='Từ chối']/following-sibling::android.widget.ImageButton[1]")
				getComment = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text='Gửi ý kiến']/following-sibling::android.widget.ImageButton[1]")
				requestType = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_value_process_type")
				warningPopup = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text = 'Cảnh báo' and @resource-id='com.hdbank.ipaper:id/tv_title_mes']")
				ignoreBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/btn_ok")
				break
			case "iOS":
				assigner = createTestObject(LocatorType.XPATH, "//XCUIElementTypeOther[@name='Chuyển tới']/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]")
				subFile = createTestObject(LocatorType.XPATH, "//XCUIElementTypeOther[@name='Tài liệu đính kèm']/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeStaticText")
				backBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic back']")
				createDate = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Ngày hoàn thiện:']/following-sibling::XCUIElementTypeStaticText[1]")
				description = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Mô tả']/following-sibling::XCUIElementTypeTextView[1]")
				documentActionBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic_floating_action']/parent::XCUIElementTypeOther")
				documentTitle = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='bg-nav-header']/following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText")
				finishDate = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Ngày hoàn thiện:']/following-sibling::XCUIElementTypeStaticText[2]")
				getOpinoionTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Lấy ý kiến']")
				menuIcon = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic user info']")
				mainFile = createTestObject(LocatorType.XPATH, "//XCUIElementTypeOther[@name='Tờ trình đính kèm']/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeStaticText")
				priority = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Ưu tiên:']/following-sibling::XCUIElementTypeStaticText[1]")
				processComment = createTestObject(LocatorType.XPATH, "")
				rePresentTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Trình lại']")
				sendApprovalTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Gửi']")
				senderName = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Người gửi:']/following-sibling::XCUIElementTypeStaticText[1]")
				status = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Trạng thái:']//following-sibling::XCUIElementTypeStaticText[1]")
				submitTxt = createTestObject(LocatorType.XPATH, "")
				approveDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic_approve']/parent::XCUIElementTypeOther")
				getOpinionTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Ý kiến']/following-sibling::XCUIElementTypeTextView")
				submitApproveBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Gửi']")
				fillterBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic filter']")
				ccOnPopupTxt = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTextField[@value='Cc']")
				rejectDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic_reject']/parent::XCUIElementTypeOther")
				getComment = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Gửi ý kiến']/parent::XCUIElementTypeOther")
				requestType = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Loại yêu cầu:']/following-sibling::XCUIElementTypeStaticText")
				warningPopup = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Cảnh báo']")
				ignoreBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Bỏ qua']")
				break
		}
	}

	TestObject processComment(String name) {
		return createTestObject(LocatorType.XPATH, "//android.widget.TextView[contains(@text, '${name}')]/parent::android.widget.RelativeLayout//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_comment']",
				[('name') : name])
	}

	List<TestObject> processComments(String nameOfUser) {
		switch (GlobalVariable.PLATFORM) {
			case 'Android' :
				return createTestObjects(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_user_name' and contains(@text, '${nameOfUser}')]/parent::android.widget.RelativeLayout//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_comment']",
				[('nameOfUser') : nameOfUser])
			case 'iOS' :
				return createTestObjects(LocatorType.XPATH, "//XCUIElementTypeCell[.//XCUIElementTypeStaticText[contains(@name, '${nameOfUser}')] and .//XCUIElementTypeButton[@name='ic dot deactive']]/XCUIElementTypeStaticText[2]",
				[('nameOfUser') : nameOfUser])
		}
	}

	TestObject ccUserItemOnPopup(String userEmail) {
		switch (GlobalVariable.PLATFORM) {
			case 'Android' :
				return createTestObject(LocatorType.XPATH, "",
				[('userEmail') : userEmail])
			case 'iOS' :
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[contains(@name, '${userEmail}')]",
				[('userEmail') : userEmail])
		}
	}
}
