package screens

import base.BaseKeyword
import internal.GlobalVariable
import locator.FileManager_HomeLocator

public class FileManager_HomeScreen extends FileManager_HomeLocator implements BaseKeyword{
	def goToIPaperFolder(String folderName){
		if(GlobalVariable.PLATFORM == 'iOS') {
			clickToElement(iosBrowseBtn)
			clickToElement(iosBrowseBtn)
		}
		swipeToElement(folder(folderName))
		clickToElement(folder(folderName))
	}
}
