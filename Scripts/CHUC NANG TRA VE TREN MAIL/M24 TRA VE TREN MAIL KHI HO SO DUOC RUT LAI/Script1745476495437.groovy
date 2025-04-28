 import entities.Document
import entities.DocumentStatus
import ipaper.IPaper
import screens.IncomingDocumentScreen.ActionType
import screens.DocumentInformationScreen.ActionType as ActionDocumentInformation
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

// User Duyệt login vào mail và kiểm tra email

IPaper.outlook_homeScreen.switchToAccount(auto6)

IPaper.outlook_homeScreen.waitNotiEmailSent(auto5, EmailNoti.SEND_APPROVED, document)

IPaper.outlook_homeScreen.waitActionEmailSent(PerformAction.SEND_APPROVE, document)

Utilities.closeCurentApp()

// User Tạo login web/app IPP và thực hiện rút lại hồ sơ vừa gửi duyệt thành công

Utilities.openIPaperApp()

IPaper.loginScreen.login(auto5)

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewInformationDocument(document)

// Đảm bảo yêu cầu đều hiển thị ở Hồ sơ đi và Hồ sơ đến

IPaper.documentInformationScreen.performAction(document, ActionDocumentInformation.WITHDRAW_DOCUMENT)

document.setStatus(DocumentStatus.WAIT_PROCESS)

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.checkStatus(document)

IPaper.documentInformationScreen.isAssignerDisplayed()

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.checkStatus(document)

IPaper.documentInformationScreen.isAssignerDisplayed()

//  User Duyệt login vào mail và thực hiện Trả về hồ sơ user Tạo vừa rút về thành công ở app/web IPP

Utilities.closeCurentApp()

Utilities.openOutlookApp()

IPaper.outlook_homeScreen.switchToAccount(auto6)

//User Duyệt kiểm tra mail phản hồi kết quả trả về

IPaper.outlook_homeScreen.waitActionEmailSent(PerformAction.SEND_APPROVE, document)

IPaper.outlook_homeScreen.goToEmail(PerformAction.SEND_APPROVE, document)

IPaper.outlook_mailScreen.action(ActionOutlook.RETURN)

IPaper.outlook_homeScreen.backToHome()

IPaper.outlook_homeScreen.waitNotiEmailSent(EmailNoti.NOT_ACCEPTED_WITHDRAW, document)