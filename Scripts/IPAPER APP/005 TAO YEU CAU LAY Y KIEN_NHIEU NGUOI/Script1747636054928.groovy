import entities.DocumentStatus
import entities.User
import ipaper.IPaper
import screens.DocumentInformationScreen.ActionType
import screens.PDFSignScreen.PerformAction
import utilities.DataTest

String REQUEST_NAME = 'Trình ký PDF có sẵn'

String USER_CREATE_COMMENT = 'Commented by user create document'

String AUTO6_COMMENT = 'Commented by auto6'
	
String AUTO7_COMMENT = 'Commented by auto7'

String AUTO8_COMMENT = 'Commented by auto8'

User auto5 = DataTest.getUserTest5()

User auto6 = DataTest.getUserTest6()

User auto7 = DataTest.getUserTest7()

User auto8 = DataTest.getUserTest8() 

def document = DataTest.createDocumentTest(auto5, auto6, null, 'dummy.pdf', 'dummy.pdf')

//create document

IPaper.loginScreen.login(auto5)

IPaper.homeScreen.openRequestList()

IPaper.homeScreen.createRequest(REQUEST_NAME)

IPaper.pdfSignScreen.fillInTitle(document.getTitle())

IPaper.pdfSignScreen.selectPriority(document.getPriority())

IPaper.pdfSignScreen.selectTime(document.getTime())

IPaper.pdfSignScreen.fillInDescription(document.getDescription())

IPaper.pdfSignScreen.selectAssigner(document.getAssigner().getEmail())

IPaper.pdfSignScreen.selectAssigner(auto7)

IPaper.pdfSignScreen.selectAssigner(auto8)

IPaper.pdfSignScreen.openMainFileBrowser()

IPaper.fileBrowserScreen.attachFile(document.getMainFileName())

IPaper.pdfSignScreen.openSubFileBrowser()

IPaper.fileBrowserScreen.attachFile(document.getSubFileName())

IPaper.pdfSignScreen.performAction(PerformAction.SEND_WITH_COMENT)

IPaper.pdfSignScreen.fillInOpinion(USER_CREATE_COMMENT)

IPaper.pdfSignScreen.submitRequest()

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

document.setStatus(DocumentStatus.WAIT_GET_COMMENT)

//assigner check document

document.setAssigner(auto5)

//========================== CHECK DOCUMENT IN AUTO 6 ==========================================

IPaper.loginScreen.login(auto6)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.isSendCommentDisplayed()

IPaper.documentInformationScreen.checkDocumentTitle(document)

IPaper.documentInformationScreen.checkSender(document)

IPaper.documentInformationScreen.checkCreateDate()

IPaper.documentInformationScreen.checkFinishDate(document)

IPaper.documentInformationScreen.checkPriority(document)

IPaper.documentInformationScreen.checkDescription(document)

IPaper.documentInformationScreen.checkAssigner(document)

IPaper.documentInformationScreen.checkPresentFileName(document)

IPaper.documentInformationScreen.checkAttachFileName(document)

IPaper.documentInformationScreen.performAction(document, ActionType.COMMENT, AUTO6_COMMENT)

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

//========================== CHECK DOCUMENT IN AUTO7 ==========================================

IPaper.loginScreen.login(auto7)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.isSendCommentDisplayed()

IPaper.documentInformationScreen.checkDocumentTitle(document)

IPaper.documentInformationScreen.checkSender(document)

IPaper.documentInformationScreen.checkCreateDate()

IPaper.documentInformationScreen.checkFinishDate(document)

IPaper.documentInformationScreen.checkPriority(document)

IPaper.documentInformationScreen.checkDescription(document)

IPaper.documentInformationScreen.checkAssigner(document)

IPaper.documentInformationScreen.checkPresentFileName(document)

IPaper.documentInformationScreen.checkAttachFileName(document)

IPaper.documentInformationScreen.performAction(document, ActionType.COMMENT, AUTO7_COMMENT)

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

//========================== CHECK DOCUMENT IN AUTO8 ==========================================

IPaper.loginScreen.login(auto8)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.isSendCommentDisplayed()

IPaper.documentInformationScreen.checkDocumentTitle(document)

IPaper.documentInformationScreen.checkSender(document)

IPaper.documentInformationScreen.checkCreateDate()

IPaper.documentInformationScreen.checkFinishDate(document)

IPaper.documentInformationScreen.checkPriority(document)

IPaper.documentInformationScreen.checkDescription(document)

IPaper.documentInformationScreen.checkAssigner(document)

IPaper.documentInformationScreen.checkPresentFileName(document)

IPaper.documentInformationScreen.checkAttachFileName(document)

IPaper.documentInformationScreen.performAction(document, ActionType.COMMENT, AUTO8_COMMENT)

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

//========================== CHECK DOCUMENT IN AUTO5 AFTER COMMENTED ==========================================

IPaper.loginScreen.login(auto5)

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.checkDocumentTitle(document)

IPaper.documentInformationScreen.showMoreComment()

IPaper.documentInformationScreen.checkComment(auto6, AUTO6_COMMENT)

IPaper.documentInformationScreen.checkComment(auto7, AUTO7_COMMENT)

IPaper.documentInformationScreen.checkComment(auto8, AUTO8_COMMENT)