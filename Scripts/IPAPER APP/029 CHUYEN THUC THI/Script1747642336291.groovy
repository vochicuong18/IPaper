import entities.DocumentStatus
import entities.User
import ipaper.IPaper
import screens.DocumentInformationScreen.ActionType
import screens.PDFSignScreen.PerformAction
import utilities.DataTest as DataTest

String REQUEST_NAME = 'Trình ký PDF có sẵn'

String COMMENT = 'Commented by automation team'

def auto5 = DataTest.getUserTest5()

def auto6 = DataTest.getUserTest6()

def auto7 = DataTest.getUserTest7()

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

IPaper.pdfSignScreen.performAction(PerformAction.SEND_APPROVE)

IPaper.pdfSignScreen.fillInOpinion(document.getComment())

IPaper.pdfSignScreen.submitRequest()

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

document.setStatus(DocumentStatus.WAIT_APPROVE)

document.setAssigner(auto5)

//assigner check document
IPaper.loginScreen.login(auto6)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewMainFile(document)

//Assinger approve document
document.setSender(auto6)

IPaper.documentInformationScreen.performAction(document, ActionType.APPROVE, COMMENT)

IPaper.documentInformationScreen.backToHome()

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

IPaper.documentInformationScreen.checkAssigner(document)

IPaper.documentInformationScreen.checkPresentFileName(document)

IPaper.documentInformationScreen.checkAttachFileName(document)

IPaper.documentInformationScreen.checkComment(auto6, COMMENT)

IPaper.documentInformationScreen.backToHome()

IPaper.inComingDocument.checkItemInDocument()

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

IPaper.loginScreen.login(auto5)

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.performAction(document, ActionType.COMPLETE)

IPaper.outComingDocument.loadDataResult()

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

IPaper.documentInformationScreen.checkComment(auto6, COMMENT)

//===================== CHUYỂN THỰC THI CHO USER AUTO7 ================

IPaper.documentInformationScreen.selectUserTransfered(auto7)

IPaper.documentInformationScreen.performAction(document, ActionType.TRANSFER_EXCUTION)

//===================== CHECK DOCUMENT INFORMATION IN AUTO7 ================

IPaper.homeScreen.logout()

IPaper.loginScreen.login(auto7)

IPaper.homeScreen.goToRelatedDocument()

IPaper.relatedDocumentScreen.viewInformationExecuteDocument(document)

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