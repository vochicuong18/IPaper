import java.text.SimpleDateFormat

import ipaper.IPaper
import utilities.DataTest

String REQUEST_NAME = 'Trình ký PDF có sẵn'

String STATUS = 'Đang đợi xử lý'

String TO_DAY = new SimpleDateFormat('dd/MM/yyyy').format(new Date())

String documentTitle = 'Trình ký ' + System.currentTimeMillis()

def userA3NV = DataTest.getUserA3NVTest() // user create document

def userA4NV = DataTest.getUserA4NVTest() // assigner

def userA5NV = DataTest.getUserA5NVTest() // CC

def document = DataTest.createDocumentTest(userA4NV, userA5NV, "dummy.pdf", "dummy.pdf")

//create document

IPaper.loginScreen.login(userA3NV)

IPaper.homeScreen.openRequestList()

IPaper.homeScreen.createRequest(REQUEST_NAME)

IPaper.pdfSignScreen.fillInTitle(document.title)

IPaper.pdfSignScreen.selectPriority(document.priority)

IPaper.pdfSignScreen.selectTime(document.time)

IPaper.pdfSignScreen.fillInDescription(document.description)

IPaper.pdfSignScreen.selectAssigner(document.assigner)

IPaper.pdfSignScreen.openMainFileBrowser()

IPaper.fileBrowserScreen.attachFile(document.mainFileName)

IPaper.pdfSignScreen.openSubFileBrowser()

IPaper.fileBrowserScreen.attachFile(document.subFileName)

IPaper.pdfSignScreen.selectRelatedMember(document.cc)

IPaper.pdfSignScreen.sendRequest()

IPaper.pdfSignScreen.fillInOpinion(document.opinion)

IPaper.pdfSignScreen.submitRequest()

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewInformationDocument(documentTitle)

IPaper.documentInformationScreen.checkDocumentTitle(documentTitle)

IPaper.documentInformationScreen.checkSender(userA3NV.getName())

IPaper.documentInformationScreen.checkStatus(STATUS)

IPaper.documentInformationScreen.checkCreateDate(TO_DAY)

IPaper.documentInformationScreen.checkFinishDate(document.time)

IPaper.documentInformationScreen.checkPriority(document.priority)

IPaper.documentInformationScreen.checkDescription(document.description)

IPaper.documentInformationScreen.checkAssigner(document.assigner)

IPaper.documentInformationScreen.checkPresentFileName(document.mainFileName)

IPaper.documentInformationScreen.checkAttachFileName(document.subFileName)

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

// Check document in assinger account

IPaper.loginScreen.login(userA4NV)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(documentTitle)

IPaper.documentInformationScreen.checkDocumentTitle(documentTitle)

IPaper.documentInformationScreen.checkSender(userA3NV.getName())

IPaper.documentInformationScreen.checkStatus(STATUS)

IPaper.documentInformationScreen.checkCreateDate(TO_DAY)

IPaper.documentInformationScreen.checkFinishDate(document.time)

IPaper.documentInformationScreen.checkPriority(document.priority)

IPaper.documentInformationScreen.checkDescription(document.description)

IPaper.documentInformationScreen.checkAssigner(userA3NV.getEmail())

IPaper.documentInformationScreen.checkPresentFileName(document.mainFileName)

IPaper.documentInformationScreen.checkAttachFileName(document.subFileName)

IPaper.documentInformationScreen.backToHome()

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

IPaper.documentInformationScreen.checkFinishDate(document.time)

IPaper.documentInformationScreen.checkPriority(document.priority)

IPaper.documentInformationScreen.checkDescription(document.description)

IPaper.documentInformationScreen.checkAssigner(userA4NV.getEmail())

IPaper.documentInformationScreen.checkPresentFileName(document.mainFileName)

IPaper.documentInformationScreen.checkAttachFileName(document.subFileName)

IPaper.documentInformationScreen.checkUserCannotEditDocument()

IPaper.documentInformationScreen.backToHome()






