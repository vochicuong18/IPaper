import entities.Document
import entities.DocumentStatus
import ipaper.IPaper
import screens.OutLook_MailScreen.ActionType
import screens.PDFSignScreen.PerformAction
import utilities.DataTest
import utilities.Utilities as Utilities

String REQUEST_NAME = 'Trình ký PDF có sẵn'

def auto5 = DataTest.getUserTest('auto5')

def auto6 = DataTest.getUserTest('auto6')

Document document = DataTest.createDocumentTest(auto5, auto6, null, 'dummy.pdf', 'dummy.pdf')

IPaper.loginScreen.login(auto5)

IPaper.homeScreen.goToSetting()

IPaper.settingScreen.enableApproveByEmail(true)

IPaper.settingScreen.backToHome()

// create document
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

IPaper.fileBrowserScreen.attachFile(document.getMainFileName())

IPaper.pdfSignScreen.performAction(PerformAction.SEND_APPROVE)

IPaper.pdfSignScreen.fillInOpinion(document.getComment())

IPaper.pdfSignScreen.submitRequest()

Utilities.openOutlookApp()

IPaper.outlook_homeScreen.waitEmailSent(PerformAction.SEND_APPROVE, document)

IPaper.outlook_homeScreen.goToEmail(PerformAction.SEND_APPROVE, document)

IPaper.outlook_mailScreen.action(ActionType.REJECT)

document.setStatus(DocumentStatus.REJECT)

Utilities.closeCurentApp()

Utilities.openIPaperApp()

IPaper.loginScreen.login(auto5)

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.waitStatusChangeTo(DocumentStatus.REJECT)

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