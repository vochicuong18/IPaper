package locator

import static entities.LocatorType.ID
import static entities.LocatorType.XPATH

import com.kms.katalon.core.testobject.TestObject

import internal.GlobalVariable

public class FileManager_FolderLocator extends TestObjectFactory  {
	TestObject backBtn, editBtn, addBtn, selectAllBtn, deleteBtn, confirmDelete, iosSearchFile

	FileManager_FolderLocator() {
		switch (GlobalVariable.PLATFORM) {
			case 'Android':
				editBtn = createTestObject(ID, "andes.oplus.documentsreader:id/mode")
				backBtn = createTestObject(ID, "andes.oplus.documentsreader:id/coui_toolbar_back_view")
				addBtn = createTestObject(ID, "com.coloros.filemanager:id/action_add")
				selectAllBtn = createTestObject(XPATH, "//android.widget.Button[@text='Chọn tất cả']")
				deleteBtn = createTestObject(ID, "com.coloros.filemanager:id/navigation_delete")
				confirmDelete = createTestObject(ID, "android:id/button3")
				break
			case 'iOS':
				editBtn = createTestObject(XPATH, "//XCUIElementTypeSwitch[@name='QLOverlayMarkupButtonAccessibilityIdentifier']")
				backBtn = createTestObject(XPATH, "//XCUIElementTypeButton[@name='QLOverlayDoneButtonAccessibilityIdentifier']")
				iosSearchFile = createTestObject(XPATH, "//XCUIElementTypeImage[@name='magnifyingglass']")
			//				selectAllBtn = createTestObject(XPATH, "//android.widget.Button[@text='Chọn tất cả']")
				deleteBtn = createTestObject(XPATH, "//XCUIElementTypeButton[@name='Xóa']/parent::XCUIElementTypeCell")
			//				confirmDelete = createTestObject(ID, "android:id/button3")
				break
		}
	}

	List<TestObject> files() {
		switch (GlobalVariable.PLATFORM) {
			case 'Android':
				return createTestObjects(XPATH, "//android.view.ViewGroup[@resource-id='com.coloros.filemanager:id/file_browser_item_list_root']")
			case 'iOS':
				return createTestObjects(XPATH, "//XCUIElementTypeCollectionView[@name='File View']/XCUIElementTypeCell")
		}
	}
}
