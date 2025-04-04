import utilities.DataTest
import ipaper.IPaper
import screens.PDFSignScreen

String REQUEST_NAME = 'Trình ký PDF có sẵn'

def user = DataTest.getUserTest()

String documentTitle = 'Cuong Auto ' + System.currentTimeMillis()

def dataDocument = [
	title         : documentTitle,
	mainFileName  : "dummy.pdf",
	subFileName   : "dummy.pdf",
	priority      : PDFSignScreen.Priority.CAO,
	time          : "01/5/2027",
	description   : "Cuong description",
	assigner      : "test0004@hdbank.com.vn",
	relatedMember : "9999hd6@hdbank.com.vn",
	opinion       : "This is opinion",
]

IPaper.loginScreen.login(user.userName, user.password)

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

IPaper.pdfSignScreen.selectRelatedMember(dataDocument.relatedMember)

IPaper.pdfSignScreen.sendRequest()

IPaper.pdfSignScreen.fillInOpinion(dataDocument.opinion)

IPaper.pdfSignScreen.submitRequest()

IPaper.inComingDocument.viewInformationDocument('Cuong Auto 1743578444753')

IPaper.documentInformationScreen.checkDocumentTitle(documentTitle)

IPaper.documentInformationScreen.checkSender(user.name)



