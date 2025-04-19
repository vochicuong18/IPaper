import java.text.SimpleDateFormat

import ipaper.IPaper
import screens.PDFSignScreen
import utilities.DataTest

String REQUEST_NAME = 'Trình ký PDF có sẵn'

String WAIT_COMMET_STATUS = 'Chờ lấy ý kiến'

String COMMENT_STATUS = 'Đã lấy ý kiến'

String USER_CREATE_COMMENT = 'Đây là bình luận của người tạo'

String ASSIGNER_COMMENT = 'Không có bình luận'

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
	assigner      : assigner.getEmail(),
	cc 			  : "test0005@hdbank.com.vn",
	opinion       : "case 37 duyệt lấy ý kiến",
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

IPaper.pdfSignScreen.getOpinion()

IPaper.pdfSignScreen.fillInOpinion(USER_CREATE_COMMENT)

IPaper.pdfSignScreen.submitRequest()

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

//assigner check document

IPaper.loginScreen.login(assigner)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(documentTitle)

IPaper.documentInformationScreen.checkDocumentTitle(documentTitle)

IPaper.documentInformationScreen.checkSender(userCreateDocs.getName())

IPaper.documentInformationScreen.checkStatus(WAIT_COMMET_STATUS)

IPaper.documentInformationScreen.checkCreateDate(TO_DAY)

IPaper.documentInformationScreen.checkFinishDate(dataDocument.time)

IPaper.documentInformationScreen.checkPriority(dataDocument.priority)

IPaper.documentInformationScreen.checkDescription(dataDocument.description)

IPaper.documentInformationScreen.checkAssigner(dataDocument.assigner)

IPaper.documentInformationScreen.checkPresentFileName(dataDocument.mainFileName)

IPaper.documentInformationScreen.checkAttachFileName(dataDocument.subFileName)

//Assinger approve document

IPaper.documentInformationScreen.backToHome()

IPaper.inComingDocument.sendComment(documentTitle, ASSIGNER_COMMENT)

//Assigner check document after sendComment

IPaper.inComingDocument.checkItemInDocument()

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewInformationDocument(documentTitle)

IPaper.documentInformationScreen.checkDocumentTitle(documentTitle)

IPaper.documentInformationScreen.checkSender(userCreateDocs.getName())

IPaper.documentInformationScreen.checkStatus(COMMENT_STATUS)

IPaper.documentInformationScreen.checkCreateDate(TO_DAY)

IPaper.documentInformationScreen.checkFinishDate(dataDocument.time)

IPaper.documentInformationScreen.checkPriority(dataDocument.priority)

IPaper.documentInformationScreen.checkDescription(dataDocument.description)

IPaper.documentInformationScreen.checkAssigner(dataDocument.assigner)

IPaper.documentInformationScreen.checkPresentFileName(dataDocument.mainFileName)

IPaper.documentInformationScreen.checkAttachFileName(dataDocument.subFileName)

IPaper.documentInformationScreen.checkComment(assigner, ASSIGNER_COMMENT)

IPaper.documentInformationScreen.checkComment(userCreateDocs, USER_CREATE_COMMENT)

IPaper.documentInformationScreen.backToHome()

IPaper.inComingDocument.checkItemInDocument()
