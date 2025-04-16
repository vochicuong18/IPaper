import java.text.SimpleDateFormat

import ipaper.IPaper
import screens.PDFSignScreen
import utilities.AssertUtilities
import utilities.DataTest

String REQUEST_NAME = 'Trình ký PDF có sẵn'

String STATUS = 'Đang đợi xử lý'

String TO_DAY = new SimpleDateFormat('dd/MM/yyyy').format(new Date())

String ERROR_MESSAGE = "Vui lòng nhập Email người nhận"

String documentTitle = 'Trình ký ' + System.currentTimeMillis()

def userA3NV = DataTest.getUserA3NVTest() // user create document

def userA4NV = DataTest.getUserA4NVTest() // assigner

def userA5NV = DataTest.getUserA5NVTest() // CC

def dataDocument = [
	title         : documentTitle,
	mainFileName  : "dummy.pdf",
	subFileName   : "dummy.pdf",
	priority      : PDFSignScreen.Priority.CAO,
	time          : "01/5/2025",
	description   : "Cuong description",
	assigner      : "test0004@hdbank.com.vn",
	cc 			  : "test0005@hdbank.com.vn",
	opinion       : "Cho ý kiến",
]

//create document

IPaper.loginScreen.login(userA3NV)

IPaper.homeScreen.openRequestList()

IPaper.homeScreen.createRequest(REQUEST_NAME)

IPaper.pdfSignScreen.fillInTitle(dataDocument.title)

IPaper.pdfSignScreen.selectPriority(dataDocument.priority)

IPaper.pdfSignScreen.selectTime(dataDocument.time)

IPaper.pdfSignScreen.fillInDescription(dataDocument.description)

IPaper.pdfSignScreen.openMainFileBrowser()

IPaper.fileBrowserScreen.attachFile(dataDocument.mainFileName)

IPaper.pdfSignScreen.openSubFileBrowser()

IPaper.fileBrowserScreen.attachFile(dataDocument.subFileName)

IPaper.pdfSignScreen.sendRequest()

AssertUtilities.checkEquals(IPaper.pdfSignScreen.getErrorMessage(), ERROR_MESSAGE)

IPaper.pdfSignScreen.submitErrorPopup()

IPaper.pdfSignScreen.selectAssigner(dataDocument.assigner)

IPaper.pdfSignScreen.selectAssigner(dataDocument.assigner)

IPaper.pdfSignScreen.sendRequest()

IPaper.pdfSignScreen.fillInOpinion(dataDocument.opinion)

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








