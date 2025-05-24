package locator

import com.kms.katalon.core.testobject.TestObject

import entities.LocatorType
import internal.GlobalVariable

public class MisFormLocator extends TestObjectFactory{
	TestObject documentNameTxt,finishDateTxt, issueDateTxt, effectiveDateTxt, headTxt, signUserTxt, relatedTypeTxt, businessType, responsibleUnitTxt, issueLevelTxt, documentTypeTxt, unitReceivedTxt,
	submitSelectedBtn, submitDatePickerBtn, actionBtn, sendApproveBtn, commentTxt, sendComment

	TestObject itemBottomSheet(String item) {
		switch (GlobalVariable.PLATFORM) {
			case "Android":
				return createTestObject(LocatorType.XPATH, "//android.widget.TextView[@resource-id='com.hdbank.ipaper:id/tvContent' and @text='${item}']")
			case "iOS":
				return createTestObject(LocatorType.XPATH, "")
		}
	}

	MisFormLocator () {
		switch (GlobalVariable.PLATFORM) {
			case 'Android':
				documentNameTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/edtDocumentTitle")
				finishDateTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_due_date")
				issueDateTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_publish_date")
				effectiveDateTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/img_effect_date")
				headTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_leader")
				signUserTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_signer")
				relatedTypeTxt =createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tv_title_assign_to")
				businessType = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tvBusinessType")
				responsibleUnitTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tvLiability")
				issueLevelTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tvLevel")
				documentTypeTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tvDocumentType")
				unitReceivedTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/tvDepartment")
				submitSelectedBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/btn_ok_user")
				submitDatePickerBtn = createTestObject(LocatorType.ID, "android:id/button1")
				actionBtn = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/fab")
				sendApproveBtn = createTestObject(LocatorType.XPATH, "//android.widget.ImageButton[@resource-id='com.hdbank.ipaper:id/fab1']")
				commentTxt = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/edit_note")
				sendComment = createTestObject(LocatorType.ID, "com.hdbank.ipaper:id/btn_ok_user")
				break
			case 'iOS':
				break
		}
	}
}
