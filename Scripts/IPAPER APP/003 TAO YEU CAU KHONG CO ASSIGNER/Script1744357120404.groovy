import java.text.SimpleDateFormat

import ipaper.IPaper
import screens.PDFSignScreen.PerformAction
import utilities.DataTest

String REQUEST_NAME = 'Trình ký PDF có sẵn'

String STATUS = 'Đang đợi xử lý'

String TO_DAY = new SimpleDateFormat('dd/MM/yyyy').format(new Date())

String ERROR_MESSAGE = "Vui lòng nhập Email người nhận"

String documentTitle = 'Trình ký ' + System.currentTimeMillis()

def auto5 = DataTest.getUserTest("auto5")

def auto6 = DataTest.getUserTest("auto6")

def auto7 = DataTest.getUserTest("auto7")s

def document = DataTest.createDocumentTest(auto5, auto6, auto7, 'dummy.pdf', 'dummy.pdf')

//create document

IPaper.loginScreen.login(auto5)

IPaper.homeScreen.openRequestList()

IPaper.homeScreen.createRequest(REQUEST_NAME)

IPaper.pdfSignScreen.fillInTitle(document.title)

IPaper.pdfSignScreen.selectPriority(document.priority)

IPaper.pdfSignScreen.selectTime(document.time)

IPaper.pdfSignScreen.fillInDescription(document.description)

IPaper.pdfSignScreen.selectAssigner(document.getAssigner().getEmail())

IPaper.pdfSignScreen.openMainFileBrowser()

IPaper.fileBrowserScreen.attachFile(document.mainFileName)

IPaper.pdfSignScreen.openSubFileBrowser()

IPaper.fileBrowserScreen.attachFile(document.subFileName)

IPaper.pdfSignScreen.selectRelatedMember(document.getCc().getEmail())

IPaper.pdfSignScreen.performAction(PerformAction.SEND_APPROVE)

IPaper.pdfSignScreen.fillInOpinion(document.getComment())

IPaper.pdfSignScreen.submitRequest()

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewInformationDocument(documentTitle)

IPaper.documentInformationScreen.checkDocumentTitle(documentTitle)

IPaper.documentInformationScreen.checkSender(userA3NV.getName())

IPaper.documentInformationScreen.checkStatus(STATUS)

IPaper.documentInformationScreen.checkCreateDate(TO_DAY)

IPaper.documentInformationScreen.checkFinishDate(dataDocument.time)

IPaper.documentInformationScreen.checkPriority(dataDocument.priority)

IPaper.documentInformationScreen.checkDescription(dataDocument.description)

IPaper.documentInformationScreen.checkAssigner(dataDocument.assigner)

IPaper.documentInformationScreen.checkPresentFileName(dataDocument.mainFileName)

IPaper.documentInformationScreen.checkAttachFileName(dataDocument.subFileName)

IPaper.documentInformationScreen.backToHome()

IPaper.outComingDocument.checkItemInDocument()

IPaper.homeScreen.logout()

// Check document in assinger account

IPaper.loginScreen.login(userA4NV)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(documentTitle)

IPaper.documentInformationScreen.checkDocumentTitle(documentTitle)

IPaper.documentInformationScreen.checkSender(userA3NV.getName())

IPaper.documentInformationScreen.checkStatus(STATUS)

IPaper.documentInformationScreen.checkCreateDate(TO_DAY)

IPaper.documentInformationScreen.checkFinishDate(dataDocument.time)

IPaper.documentInformationScreen.checkPriority(dataDocument.priority)

IPaper.documentInformationScreen.checkDescription(dataDocument.description)

IPaper.documentInformationScreen.checkAssigner(userA3NV.getEmail())

IPaper.documentInformationScreen.checkPresentFileName(dataDocument.mainFileName)

IPaper.documentInformationScreen.checkAttachFileName(dataDocument.subFileName)

IPaper.documentInformationScreen.backToHome()

IPaper.inComingDocument.checkItemInDocument()

IPaper.homeScreen.logout()

// Check account CC

IPaper.loginScreen.login(userA5NV)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.goToRelatedDocument()

IPaper.relatedDocumentScreen.viewInformationDocument(documentTitle)

IPaper.documentInformationScreen.checkDocumentTitle(documentTitle)

IPaper.documentInformationScreen.checkSender(userA3NV.getName())

IPaper.documentInformationScreen.checkStatus(STATUS)

IPaper.documentInformationScreen.checkCreateDate(TO_DAY)

IPaper.documentInformationScreen.checkFinishDate(dataDocument.time)

IPaper.documentInformationScreen.checkPriority(dataDocument.priority)

IPaper.documentInformationScreen.checkDescription(dataDocument.description)

IPaper.documentInformationScreen.checkAssigner(userA4NV.getEmail())

IPaper.documentInformationScreen.checkPresentFileName(dataDocument.mainFileName)

IPaper.documentInformationScreen.checkAttachFileName(dataDocument.subFileName)

IPaper.documentInformationScreen.checkUserCannotEditDocument()

IPaper.documentInformationScreen.backToHome()








