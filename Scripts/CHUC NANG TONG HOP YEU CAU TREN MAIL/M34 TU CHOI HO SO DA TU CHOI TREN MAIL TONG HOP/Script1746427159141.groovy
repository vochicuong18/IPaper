import entities.Document as Document
import ipaper.IPaper as IPaper
import screens.DocumentInformationScreen.ActionType as DocumentActionType
import screens.OutLook_HomeScreen.EmailNoti as EmailNoti
import screens.OutLook_MailScreen.ActionType as ActionType
import screens.PDFSignScreen.PerformAction as PerformAction
import utilities.DataTest as DataTest
import utilities.Utilities as Utilities

String REQUEST_NAME = 'Trình ký PDF có sẵn'

String TO_DAY = new Date().format('dd/MM/yyyy')

def auto6 = DataTest.getUserTest('auto6')

def auto5 = DataTest.getUserTest('auto5')

Document document = DataTest.createDocumentTest(auto5, auto6, null, 'dummy.pdf', 'dummy.pdf')

//IPaper.loginScreen.login(auto6)
//
//IPaper.homeScreen.goToSetting()
//
//IPaper.settingScreen.enableApproveByEmail(true)
//
//IPaper.settingScreen.backToHome()
//
//IPaper.homeScreen.logout()
IPaper.loginScreen.login(auto5)

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

Utilities.closeCurentApp()

Utilities.openOutlookApp()

IPaper.outlook_homeScreen.switchToAccount(auto6)

IPaper.outlook_homeScreen.waitNotiEmailSent("$EmailNoti.SUMMARY_DOCUMENT $TO_DAY")

IPaper.outlook_homeScreen.backToHome()

Utilities.closeCurentApp()

Utilities.openIPaperApp()

IPaper.loginScreen.login(auto6)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.performAction(document, DocumentActionType.REJECT)

document.setSender(auto6)

document.setAssigner(auto5)

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.checkDocumentTitle(document)

IPaper.documentInformationScreen.checkSender(document)

IPaper.documentInformationScreen.checkStatus(document)

IPaper.documentInformationScreen.checkCreateDate()

IPaper.documentInformationScreen.checkFinishDate(document)

IPaper.documentInformationScreen.checkPriority(document)

IPaper.documentInformationScreen.checkDescription(document)

IPaper.documentInformationScreen.isAssignerDisplayed()

IPaper.documentInformationScreen.checkPresentFileName(document)

IPaper.documentInformationScreen.checkAttachFileName(document)

Utilities.closeCurentApp()

Utilities.openOutlookApp()

IPaper.outlook_homeScreen.switchToAccount(auto6)

IPaper.outlook_homeScreen.searchEmail("$EmailNoti.SUMMARY_DOCUMENT $TO_DAY")

IPaper.outlook_homeScreen.goToFirstEmail()

IPaper.outlook_mailScreen.actionSummarizeEmail(document, ActionType.REJECT, 'Cuong check mail nha')

IPaper.outlook_homeScreen.backToHome()

IPaper.outlook_homeScreen.waitNotiEmailSent(EmailNoti.NOT_ACCEPTED, document)

Utilities.closeCurentApp()

Utilities.openIPaperApp()

IPaper.loginScreen.login(auto5)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.checkDocumentTitle(document)

IPaper.documentInformationScreen.checkSender(document)

IPaper.documentInformationScreen.checkStatus(document)

IPaper.documentInformationScreen.checkCreateDate()

IPaper.documentInformationScreen.checkFinishDate(document)

IPaper.documentInformationScreen.checkPriority(document)

IPaper.documentInformationScreen.checkDescription(document)

IPaper.documentInformationScreen.isAssignerDisplayed()

IPaper.documentInformationScreen.checkPresentFileName(document)

IPaper.documentInformationScreen.checkAttachFileName(document)