package screens


import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot

import com.kms.katalon.core.testobject.TestObject

import base.BaseKeyword
import drivers.Driver
import internal.GlobalVariable
import locator.FileManager_FolderLocator
import utilities.AIUtilities
import utilities.AssertUtilities
import utilities.Utilities

public class FileManager_FolderScreen extends FileManager_FolderLocator implements BaseKeyword{
	String PROMT = "Does this image have a correct Vietnamese or English font? Only reply true or false."

	def checkPDFFiles() {
		boolean status = true
		//wait folder load
		if (GlobalVariable.PLATFORM == 'iOS') {
		} else {
			waitForPresentOf(addBtn)
		}
		for (TestObject fileItem : files()) {
			clickToElement(fileItem)
			waitForPresentOf(editBtn)
			status &= checkFileContentByAI(getPDFImagePath())
			clickToElement(backBtn)
		}
		AssertUtilities.assertTrue(status,"Check image content")
	}

	//screenshot and return image path
	String getPDFImagePath() {
		File screenshot = ((TakesScreenshot) Driver.driver).getScreenshotAs(OutputType.FILE)
		File destination = new File("PDFScreen/${System.currentTimeMillis()}.png")
		screenshot.renameTo(destination)
		return destination.absolutePath
	}

	boolean checkFileContentByAI(String filePath) {
		def result = AIUtilities.analyzeImage(filePath, PROMT)
		boolean status = result.trim().equalsIgnoreCase("true")
		return status
	}

	def deleteAllFiles() {
		if (GlobalVariable.PLATFORM == 'Android') {
			tapAndHold(files().get(0))
			if (isDisplayed(selectAllBtn)) {
				clickToElement(selectAllBtn)
			}
			clickToElement(deleteBtn)
			clickToElement(confirmDelete)
			Utilities.back()
		} else {
			int fileSize =  files().size()
			for(int i = 0; i < fileSize; i++) {
				tapAndHold(files().get(0))
				clickToElement(deleteBtn)
				Thread.sleep(500) //wait animation
			}
		}
	}
}
