import entities.DocumentStatus
import entities.User
import ipaper.IPaper
import screens.PDFSignScreen.PerformAction
import utilities.DataTest
String REQUEST_NAME = 'Trình ký PDF có sẵn'

String USER_CREATE_COMMENT = 'Commented by user create document'

String ASSIGNER_COMMENT = 'Comment by assigner'

String documentTitle = 'Trình ký ' + System.currentTimeMillis()

User auto5 = DataTest.getUserTest5()

User auto6 = DataTest.getUserTest6()

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

IPaper.loginScreen.login(auto6)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.checkDocumentTitle(document)

IPaper.documentInformationScreen.checkSender(document)

IPaper.documentInformationScreen.checkStatus(document)

IPaper.documentInformationScreen.checkCreateDate()

IPaper.documentInformationScreen.checkFinishDate(document)

IPaper.documentInformationScreen.checkPriority(document)

IPaper.documentInformationScreen.checkDescription(document)

IPaper.documentInformationScreen.checkAssigner(document)

IPaper.documentInformationScreen.checkPresentFileName(document)

IPaper.documentInformationScreen.checkAttachFileName(document)

//Assinger get document

IPaper.documentInformationScreen.getComment()

IPaper.documentInformationScreen.isPopupRequireCommentDisplayed()

IPaper.documentInformationScreen.ignoreWarningPopup()

IPaper.documentInformationScreen.fillInComment(USER_CREATE_COMMENT)

IPaper.documentInformationScreen.submitAction()

IPaper.documentInformationScreen.backToHome()

document.setStatus(DocumentStatus.COMMENTED)

document.setSender(auto6)

//Assigner check document after approve

IPaper.inComingDocument.checkItemInDocument()

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.checkDocumentTitle(document)

IPaper.documentInformationScreen.checkSender(document)

IPaper.documentInformationScreen.checkStatus(document)

IPaper.documentInformationScreen.checkCreateDate()

IPaper.documentInformationScreen.checkFinishDate(document)

IPaper.documentInformationScreen.checkPriority(document)

IPaper.documentInformationScreen.checkDescription(document)

IPaper.documentInformationScreen.isAssignerDisplayed()

IPaper.documentInformationScreen.checkPresentFileName(document)

IPaper.documentInformationScreen.checkAttachFileName(document)

IPaper.documentInformationScreen.checkComment(auto5, USER_CREATE_COMMENT)

IPaper.documentInformationScreen.checkComment(auto6, ASSIGNER_COMMENT)

IPaper.documentInformationScreen.backToHome()

IPaper.inComingDocument.checkItemInDocument()
