import java.text.SimpleDateFormat

import entities.DocumentStatus
import ipaper.IPaper
import screens.PDFSignScreen
import utilities.DataTest
String REQUEST_NAME = 'Trình ký PDF có sẵn'

String WAIT_COMMET_STATUS = 'Chờ lấy ý kiến'

String COMMENT_STATUS = 'Đã lấy ý kiến'
 
String CREATER_COMMNET = 'Automation comment user create document'

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

IPaper.pdfSignScreen.getOpinion()

IPaper.pdfSignScreen.fillInOpinion(CREATER_COMMNET)

IPaper.pdfSignScreen.submitRequest()

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

//assigner check document

IPaper.loginScreen.login(assigner)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(documentTitle)

IPaper.documentInformationScreen.checkDocumentTitle(documentTitle)

IPaper.documentInformationScreen.checkSender(userCreateDocs.getName())

IPaper.documentInformationScreen.checkStatus(DocumentStatus.WAIT_GET_COMMENT.toString())

IPaper.documentInformationScreen.checkCreateDate(TO_DAY)

IPaper.documentInformationScreen.checkFinishDate(dataDocument.time)

IPaper.documentInformationScreen.checkPriority(dataDocument.priority)

IPaper.documentInformationScreen.checkDescription(dataDocument.description)

IPaper.documentInformationScreen.checkAssigner(userCreateDocs.getEmail())

IPaper.documentInformationScreen.checkPresentFileName(dataDocument.mainFileName)

IPaper.documentInformationScreen.checkAttachFileName(dataDocument.subFileName)

//Assinger get document

IPaper.documentInformationScreen.getComment()

IPaper.documentInformationScreen.isPopupRequireCommentDisplayed()

IPaper.documentInformationScreen.ignoreWarningPopup()

IPaper.documentInformationScreen.fillInComment(ASSIGNER_COMMENT)

IPaper.documentInformationScreen.submitAction()

IPaper.documentInformationScreen.backToHome()

//Assigner check document after approve

IPaper.inComingDocument.checkItemInDocument()

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewInformationDocument(documentTitle)

IPaper.documentInformationScreen.checkDocumentTitle(documentTitle)

IPaper.documentInformationScreen.checkSender(assigner.getName())

IPaper.documentInformationScreen.checkStatus(DocumentStatus.COMMENTED.toString())

IPaper.documentInformationScreen.checkCreateDate(TO_DAY)

IPaper.documentInformationScreen.checkFinishDate(dataDocument.time)

IPaper.documentInformationScreen.checkPriority(dataDocument.priority)

IPaper.documentInformationScreen.checkDescription(dataDocument.description)

IPaper.documentInformationScreen.isAssignerDisplayed()

IPaper.documentInformationScreen.checkPresentFileName(dataDocument.mainFileName)

IPaper.documentInformationScreen.checkAttachFileName(dataDocument.subFileName)

IPaper.documentInformationScreen.checkComment(userCreateDocs, CREATER_COMMNET)

IPaper.documentInformationScreen.checkComment(assigner, ASSIGNER_COMMENT)

IPaper.documentInformationScreen.backToHome()

IPaper.inComingDocument.checkItemInDocument()
