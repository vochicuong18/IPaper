import entities.User as User
import ipaper.IPaper as IPaper
import utilities.DataTest as DataTest
import utilities.Utilities as Utilities

String REQUEST_NAME = 'Trình ký PDF có sẵn'

User auto5 = DataTest.getUserTest('auto5')

User auto1 = DataTest.getUserTest('auto1')

//============================= CHECK ACCOUNT HAVE PROCESS DEFINE =============================
IPaper.loginScreen.login(auto5)
	
IPaper.homeScreen.openRequestList()

IPaper.homeScreen.createRequest(REQUEST_NAME)

IPaper.pdfSignScreen.checkProcessDefineNumber(5)

IPaper.pdfSignScreen.isEyeIconEnabled()

IPaper.pdfSignScreen.isAttachIconDisplayed()

IPaper.pdfSignScreen.backToHome()

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

//============================= CHECK ACCOUNT DOSE NOT HAVE PROCESS DEFINE  =============================
IPaper.loginScreen.login(auto1)

IPaper.homeScreen.openRequestList()

IPaper.homeScreen.createRequest(REQUEST_NAME)

IPaper.pdfSignScreen.checkProcessDefineNumber(0)

IPaper.pdfSignScreen.isEyeIconDisabled()

IPaper.pdfSignScreen.isAttachIconDisplayed()