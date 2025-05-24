import java.text.SimpleDateFormat as SimpleDateFormat
import entities.DocumentStatus as DocumentStatus
import entities.User as User
import ipaper.IPaper as IPaper
import screens.PDFSignScreen.PerformAction as PerformAction
import utilities.DataTest as DataTest

String REQUEST_NAME = 'Trình ký PDF có sẵn'

String PROCESS_NAME = 'Automation Test'

String TO_DAY = new SimpleDateFormat('dd/MM/yyyy').format(new Date())

User auto5 = DataTest.getUserTest("auto5")

User auto6 = DataTest.getUserTest("auto6")

User auto7 = DataTest.getUserTest("auto7")

User auto8 = DataTest.getUserTest("auto8")

User auto1 = DataTest.getUserTest("auto1")

def document = DataTest.createDocumentTest(auto5, auto6, null, 'dummy.pdf', 'dummy.docx')

//create document
IPaper.loginScreen.login(auto5)

IPaper.homeScreen.openRequestList()

IPaper.homeScreen.createRequest(REQUEST_NAME)

IPaper.pdfSignScreen.fillInTitle(document.title)

IPaper.pdfSignScreen.selectPriority(document.priority)

IPaper.pdfSignScreen.selectTime(document.time)

IPaper.pdfSignScreen.fillInDescription(document.description)

IPaper.pdfSignScreen.openMainFileBrowser()

IPaper.fileBrowserScreen.attachFile(document.mainFileName)

IPaper.pdfSignScreen.openSubFileBrowser()

IPaper.fileBrowserScreen.attachFile(document.subFileName)

//IPaper.pdfSignScreen.processDefinition(auto6)
//
//IPaper.pdfSignScreen.processDefinition(auto7, auto8)
IPaper.pdfSignScreen.selectProcessDefined(PROCESS_NAME)

IPaper.pdfSignScreen.addUserIntoProcess(1, auto1)

IPaper.pdfSignScreen.addUserIntoProcess(1, auto6) //work around bug

IPaper.pdfSignScreen.deleteUserInProcess(auto7)

IPaper.pdfSignScreen.performAction(PerformAction.SEND_APPROVE)

IPaper.pdfSignScreen.fillInOpinion(document.getComment())

IPaper.pdfSignScreen.submitRequest()

document.setStatus(DocumentStatus.WAIT_APPROVE)

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.checkDocumentTitle(document)

IPaper.documentInformationScreen.checkSender(document)

IPaper.documentInformationScreen.checkStatus(document)

IPaper.documentInformationScreen.checkCreateDate()

IPaper.documentInformationScreen.checkFinishDate(document)

IPaper.documentInformationScreen.checkPriority(document)

IPaper.documentInformationScreen.checkDescription(document)

IPaper.documentInformationScreen.checkPresentFileName(document)

IPaper.documentInformationScreen.checkAttachFileName(document)

//IPaper.documentInformationScreen.checkAssigners(auto6, auto8, auto1)

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

// Check document in assinger account
IPaper.loginScreen.login(auto6)

document.setAssigner(auto5)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.checkDocumentTitle(document)

IPaper.documentInformationScreen.checkSender(document)

IPaper.documentInformationScreen.checkStatus(document)

IPaper.documentInformationScreen.checkCreateDate()

IPaper.documentInformationScreen.checkFinishDate(document)

IPaper.documentInformationScreen.checkPriority(document)

IPaper.documentInformationScreen.checkDescription(document)

IPaper.documentInformationScreen.checkPresentFileName(document)

IPaper.documentInformationScreen.checkAttachFileName(document)

//IPaper.documentInformationScreen.checkAssigners(auto6, auto8, auto1)