import entities.Document
import entities.DocumentStatus
import ipaper.IPaper
import screens.IncomingDocumentScreen.ActionType
import screens.OutLook_MailScreen.ActionType as ActionOutlook
import screens.OutLook_HomeScreen.EmailNoti
import screens.PDFSignScreen.PerformAction
import utilities.DataTest
import utilities.Utilities as Utilities

String REQUEST_NAME = 'Trình ký PDF có sẵn'

def auto5 = DataTest.getUserTest('auto5')

def auto6 = DataTest.getUserTest('auto6')

def APPROVER_COMMENT = "Automation commented by email"

Document document = DataTest.createDocumentTest(auto5, auto6, null, 'dummy.pdf', 'dummy.pdf')

IPaper.loginScreen.login(auto6)

IPaper.homeScreen.goToSetting()

IPaper.settingScreen.enableApproveByEmail(true)

IPaper.settingScreen.backToHome()

IPaper.homeScreen.logout()

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

// User Duyệt login vào mail và chọn yêu cầu cần duyệt

IPaper.outlook_homeScreen.switchToAccount(auto6)

IPaper.outlook_homeScreen.waitNotiEmailSent(auto5, EmailNoti.SEND_APPROVED, document)

IPaper.outlook_homeScreen.waitActionEmailSent(PerformAction.SEND_APPROVE, document)

Utilities.closeCurentApp()

Utilities.openIPaperApp()

IPaper.loginScreen.login(auto6)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.performAction(document, ActionType.QUICK_APPROVE)

document.setStatus(DocumentStatus.APPROVED)

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

IPaper.documentInformationScreen.checkAssigner(document)

IPaper.documentInformationScreen.checkPresentFileName(document)

IPaper.documentInformationScreen.checkAttachFileName(document)

Utilities.closeCurentApp()

Utilities.openOutlookApp()

IPaper.outlook_homeScreen.switchToAccount(auto6)

IPaper.outlook_homeScreen.waitActionEmailSent(PerformAction.SEND_APPROVE, document)

IPaper.outlook_homeScreen.goToEmail(PerformAction.SEND_APPROVE, document)

IPaper.outlook_mailScreen.action(ActionOutlook.RETURN, APPROVER_COMMENT)

IPaper.outlook_homeScreen.backToHome()

IPaper.outlook_homeScreen.waitNotiEmailSent(EmailNoti.NOT_ACCEPTED, document)