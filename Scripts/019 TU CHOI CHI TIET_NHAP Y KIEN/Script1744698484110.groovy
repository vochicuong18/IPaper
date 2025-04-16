import java.text.SimpleDateFormat

import ipaper.IPaper
import screens.PDFSignScreen
import utilities.DataTest
String REQUEST_NAME = 'Trình ký PDF có sẵn'

String WAIT_PROCESS_STATUS = 'Đang đợi xử lý'

String REJECT_STATUS = 'Từ chối'

String COMMENT = 'Từ chối có ý kiến case số 19'

String TO_DAY = new SimpleDateFormat('dd/MM/yyyy').format(new Date())

String documentTitle = 'Trình ký ' + System.currentTimeMillis()

def userCreateDocs = DataTest.getUserA5NVTest() // user create document

def assigner = DataTest.getUserA4NVTest() // assigner

def dataDocument = [
	title         : documentTitle,
	mainFileName  : "dummy.pdf",
	subFileName   : "dummy.pdf",
	priority      : PDFSignScreen.Priority.CAO,
	time          : "01/5/2025",
	description   : "Cuong description",
	assigner      : "test0004@hdbank.com.vn",
	cc 			  : "test0006@hdbank.com.vn",
	opinion       : "Cho ý kiến",
]

//create document

IPaper.loginScreen.login(userCreateDocs)

IPaper.homeScreen.openRequestList()

IPaper.homeScreen.createRequest(REQUEST_NAME)

IPaper.pdfSignScreen.fillInTitle(dataDocument.title)

IPaper.pdfSignScreen.selectPriority(dataDocument.priority)

IPaper.pdfSignScreen.selectTime(dataDocument.time)

IPaper.pdfSignScreen.fillInDescription(dataDocument.description)

IPaper.pdfSignScreen.selectAssigner(dataDocument.assigner)

IPaper.pdfSignScreen.openMainFileBrowser()

IPaper.fileBrowserScreen.attachFile(dataDocument.mainFileName)

IPaper.pdfSignScreen.openSubFileBrowser()

IPaper.fileBrowserScreen.attachFile(dataDocument.subFileName)

IPaper.pdfSignScreen.sendRequest()

IPaper.pdfSignScreen.fillInOpinion(dataDocument.opinion)

IPaper.pdfSignScreen.submitRequest()

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

//assigner check document

IPaper.loginScreen.login(assigner)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(documentTitle)

IPaper.documentInformationScreen.checkDocumentTitle(documentTitle)

IPaper.documentInformationScreen.checkSender(userCreateDocs.getName())

IPaper.documentInformationScreen.checkStatus(WAIT_PROCESS_STATUS)

IPaper.documentInformationScreen.checkCreateDate(TO_DAY)

IPaper.documentInformationScreen.checkFinishDate(dataDocument.time)

IPaper.documentInformationScreen.checkPriority(dataDocument.priority)

IPaper.documentInformationScreen.checkDescription(dataDocument.description)

IPaper.documentInformationScreen.checkAssigner(dataDocument.assigner)

IPaper.documentInformationScreen.checkPresentFileName(dataDocument.mainFileName)

IPaper.documentInformationScreen.checkAttachFileName(dataDocument.subFileName)

//Assinger reject document

IPaper.documentInformationScreen.reject(COMMENT)

IPaper.documentInformationScreen.backToHome()

//Assigner check document after approve

IPaper.inComingDocument.checkItemInDocument()

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewInformationDocument(documentTitle)

IPaper.documentInformationScreen.checkDocumentTitle(documentTitle)

IPaper.documentInformationScreen.checkSender(userCreateDocs.getName())

IPaper.documentInformationScreen.checkStatus(REJECT_STATUS)

IPaper.documentInformationScreen.checkCreateDate(TO_DAY)

IPaper.documentInformationScreen.checkFinishDate(dataDocument.time)

IPaper.documentInformationScreen.checkPriority(dataDocument.priority)

IPaper.documentInformationScreen.checkDescription(dataDocument.description)

IPaper.documentInformationScreen.isSenderDisplayed()

IPaper.documentInformationScreen.checkPresentFileName(dataDocument.mainFileName)

IPaper.documentInformationScreen.checkAttachFileName(dataDocument.subFileName)

IPaper.documentInformationScreen.checkComment(assigner, COMMENT)

IPaper.documentInformationScreen.backToHome()

IPaper.inComingDocument.checkItemInDocument()
