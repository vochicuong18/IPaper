package locator

import static entities.LocatorType.ID
import static entities.LocatorType.XPATH

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

public class DocumentInformationLocator extends TestObjectFactory {
	TestObject assigner, backBtn, createDate, description, requestType, documentActionBtn, documentTitle, finishDate, getOpinoionTxt,
	menuIcon, mainFile, priority, processComment, rePresentTxt, sendApprovalTxt, senderName, status, submitTxt, approveDocument, getOpinionTxt, submitApproveBtn,fillterBtn,
	ccOnPopupTxt, rejectDocument, withdrawDocument, getComment, completeDocument, warningPopup, ignoreBtn, returnDocument, downloadSubFile, useFolder, acceptUseFolder, loadingImage,
	mainFileAttachIcon, btnUploadFile, acceptNotifiDownloadSuccess,	defindeProcessTitle, expandDefineProcess, defindeProcessComponent, okBtn, showMoreComment, assignTransfer, transferExcution, issueMis,
	transferEmailSearch, listUserLoadingMask, doneBtn, bellIcon, editFileAttachIcon, imageViewLoading
	DocumentInformationLocator () {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				assigner = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/email_tv")
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
				completeDocument = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text='Hoàn tất']/following-sibling::android.widget.ImageButton[1]")
				withdrawDocument = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text = 'Rút lại']/following-sibling::android.widget.ImageButton[1]")
				getComment = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text='Gửi ý kiến']/following-sibling::android.widget.ImageButton[1]")
				requestType = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_value_process_type")
				warningPopup = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text = 'Cảnh báo' and @resource-id='com.hdbank.ipaper:id/tv_title_mes']")
				ignoreBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/btn_ok")
				returnDocument = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text='Trả về']/following-sibling::android.widget.ImageButton[1]")
				downloadSubFile = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_delete")
				useFolder = createTestObject(LocatorType.XPATH, "//android.widget.Button[@text='SỬ DỤNG THƯ MỤC NÀY']")
				acceptUseFolder = createTestObject(LocatorType.XPATH, "//android.widget.Button[@text='CHO PHÉP']")
				loadingImage = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/loading_image")
				mainFileAttachIcon = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_menu_attach")
				btnUploadFile = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/btn_upload_server")
				defindeProcessComponent = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/rcvDefine")
				expandDefineProcess = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/imgArrowShowDefine")
				okBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/btn_ok")
				showMoreComment = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_show_more_approval")
				assignTransfer = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_text_transfer")
				transferExcution = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text='Chuyển thực thi']/following-sibling::android.widget.ImageButton[1]")
				issueMis = createTestObject(LocatorType.XPATH, "//android.widget.TextView[@text='Ban hành lên MIS']/following-sibling::android.widget.ImageButton[1]")
				transferEmailSearch = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_search_email")
				doneBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/layout_menu")

				editFileAttachIcon = createTestObject(LocatorType.ID,"com.hdbank.ipaper:id/btn_change_edit_mode")
				imageViewLoading = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_view_loading")
				break
			case "iOS":
				assigner = createTestObject(LocatorType.XPATH, "//XCUIElementTypeOther[@name='Chuyển tới']/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeStaticText[contains(@label, 'hdbank.com.vn')]")
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
				completeDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Hoàn tất']")
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
				withdrawDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Rút lại']/parent::XCUIElementTypeOther")
				getComment = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Gửi ý kiến']/parent::XCUIElementTypeOther")
				requestType = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Loại yêu cầu:']/following-sibling::XCUIElementTypeStaticText[contains(@name, 'Quy trình PDF') or contains(@name, 'Review process')]")
				warningPopup = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Cảnh báo']")
				ignoreBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Bỏ qua']")
				returnDocument = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic_return']/parent::XCUIElementTypeOther")
				downloadSubFile = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic download gray']")
				acceptUseFolder = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Lưu']")
				acceptNotifiDownloadSuccess = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Đồng ý']")
				mainFileAttachIcon = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic attach file white']")
				defindeProcessTitle = createTestObject(LocatorType.XPATH, "//XCUIElementTypeOther[@name='Định nghĩa quy trình']")
				expandDefineProcess = createTestObject(LocatorType.XPATH, "//XCUIElementTypeOther[@name='Định nghĩa quy trình']/XCUIElementTypeButton")

				okBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Có']")
				showMoreComment = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_show_more_approval")
				assignTransfer = createTestObject(LocatorType.XPATH, "//XCUIElementTypeTextField[@value='Email']")
				transferExcution = createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[@name='Chuyển thực thi ']")
				transferEmailSearch = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='ic-search']//following-sibling::XCUIElementTypeTextField")
				listUserLoadingMask = createTestObject(LocatorType.XPATH, "//XCUIElementTypeImage[@name='footer_bar']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther")
				doneBtn = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='Xong']")
				bellIcon = createTestObject(LocatorType.XPATH, "//XCUIElementTypeButton[@name='ic bell']")


				break
		}
	}

	List<TestObject> subFiles(){
		switch (GlobalVariable.PLATFORM) {
			case'Android':
				return createTestObjects(LocatorType.XPATH, "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.hdbank.ipaper:id/lv_attach_file']//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_version']")
			case'iOS':
				return createTestObjects(LocatorType.XPATH, "//XCUIElementTypeOther[@name='Tài liệu liên quan đính kèm']/following-sibling::XCUIElementTypeCell[1]/XCUIElementTypeStaticText")
		}
	}

	TestObject processComment(String name) {
		return createTestObject(LocatorType.XPATH, "//android.widget.TextView[contains(@text, '${name}')]/parent::android.widget.RelativeLayout//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_comment']")
	}

	List<TestObject> processComments(String nameOfUser) {
		switch (GlobalVariable.PLATFORM) {
			case 'Android' :
				return createTestObjects(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_user_name' and contains(@text, '${nameOfUser}')]/parent::android.widget.RelativeLayout//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_comment']")
			case 'iOS' :
				return createTestObjects(LocatorType.XPATH, "//XCUIElementTypeCell[.//XCUIElementTypeStaticText[contains(@name, '${nameOfUser}')] and .//XCUIElementTypeButton[@name='ic dot deactive']]/XCUIElementTypeStaticText[1]")
		}
	}

	TestObject ccUserItemOnPopup(String userEmail) {
		switch (GlobalVariable.PLATFORM) {
			case 'Android' :
				return createTestObject(LocatorType.XPATH, "")
			case 'iOS' :
				return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[contains(@name, '${userEmail}')]")
		}
	}

	List<TestObject> listProcessDefineUser(){
		switch (GlobalVariable.PLATFORM) {
			case 'Android' :
				return createTestObjects(LocatorType.XPATH, "(//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/email_tv'])")
			case 'iOS' :
				return createTestObjects(LocatorType.XPATH, "(//XCUIElementTypeCell[following-sibling::XCUIElementTypeOther[@name='Quy trình phê duyệt'] and preceding-sibling::XCUIElementTypeOther[@name='Định nghĩa quy trình']]//XCUIElementTypeStaticText[contains(@name,'@hdbank.com.vn') and not(contains(@name, 'Auto Test User'))])")
		}
	}

	TestObject assignerItem (String userEmail) {
		switch (GlobalVariable.PLATFORM) {
			case "Android": return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tv_email' and contains(@text, '${userEmail}')]")
			case "iOS": return createTestObject(LocatorType.XPATH, "//XCUIElementTypeStaticText[contains(@label, '${userEmail}')]/parent::*")
		}
	}
}
