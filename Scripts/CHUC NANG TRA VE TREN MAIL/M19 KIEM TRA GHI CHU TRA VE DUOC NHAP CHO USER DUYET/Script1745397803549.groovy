import entities.Document as Document

import entities.DocumentStatus as DocumentStatus
import ipaper.IPaper as IPaper
import screens.OutLook_HomeScreen.EmailNoti as EmailNoti
import screens.OutLook_MailScreen.ActionType as ActionType
import screens.PDFSignScreen.PerformAction as PerformAction
import utilities.DataTest as DataTest
import utilities.Utilities as Utilities

String REQUEST_NAME = 'Trình ký PDF có sẵn'

def auto5 = DataTest.getUserTest('auto5')

def auto6 = DataTest.getUserTest('auto6')

def APPROVER_COMMENT = "Automation commented by email"

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

Utilities.closeCurentApp()

Utilities.openOutlookApp()

// User Tạo login vào mail và kiểm tra

IPaper.outlook_homeScreen.switchToAccount(auto5)

IPaper.outlook_homeScreen.waitNotiEmailSent(auto5, EmailNoti.SEND_APPROVED, document)

IPaper.outlook_homeScreen.backToHome()

// User Duyệt login vào mail và chọn yêu cầu cần duyệt

IPaper.outlook_homeScreen.switchToAccount(auto6)

IPaper.outlook_homeScreen.waitActionEmailSent(PerformAction.SEND_APPROVE, document)

IPaper.outlook_homeScreen.goToEmail(PerformAction.SEND_APPROVE, document)

IPaper.outlook_mailScreen.action(ActionType.RETURN, APPROVER_COMMENT)

IPaper.outlook_homeScreen.backToHome()

IPaper.outlook_homeScreen.switchToAccount(auto5)

IPaper.outlook_homeScreen.waitNotiEmailSent(auto6, EmailNoti.RETURNED, document)

document.setStatus(DocumentStatus.RETURNED)

document.setSender(auto6)

document.setAssigner(auto5)

Utilities.closeCurentApp()

Utilities.openIPaperApp()

//Check document

IPaper.loginScreen.login(auto6)

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

IPaper.documentInformationScreen.checkComment(auto6, APPROVER_COMMENT)

IPaper.documentInformationScreen.checkComment(auto5, document.getComment())