import java.text.SimpleDateFormat

import entities.DocumentStatus
import entities.User
import ipaper.IPaper
import screens.PDFSignScreen.PerformAction
import utilities.DataTest

String REQUEST_NAME = 'Trình ký PDF có sẵn'

String TO_DAY = new SimpleDateFormat('dd/MM/yyyy').format(new Date())

def auto5 = DataTest.getUserTest("auto5")

User auto6 = DataTest.getUserTest("auto6")

User auto7 = DataTest.getUserTest("auto7")

def document = DataTest.createDocumentTest(auto5, auto6, auto7, 'dummy.pdf', 'dummy.pdf')

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

IPaper.pdfSignScreen.processDefinition(auto6)

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

IPaper.documentInformationScreen.checkAssigner(document)

IPaper.documentInformationScreen.checkPresentFileName(document)

IPaper.documentInformationScreen.checkAttachFileName(document)

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

IPaper.documentInformationScreen.checkAssigner(document)

IPaper.documentInformationScreen.checkPresentFileName(document)

IPaper.documentInformationScreen.checkAttachFileName(document)

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()
