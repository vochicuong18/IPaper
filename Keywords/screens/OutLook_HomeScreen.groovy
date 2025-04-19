package screens

import base.BaseKeyword
import locator.Outlook_HomeLocator



public class OutLook_HomeScreen extends Outlook_HomeLocator implements BaseKeyword{
	def searchEmail(String title) {
//		clickToElement(search)
		tapAtPosition(1010, 202) //Handle can not click search icon
	}
}
