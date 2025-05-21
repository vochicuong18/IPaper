import entities.Document
import entities.DocumentStatus
import entities.User
import ipaper.IPaper
import screens.PDFSignScreen.PerformAction
import utilities.DataTest as DataTest

User auto5 = DataTest.getUserTest5()

User auto6 = DataTest.getUserTest6()

String REQUEST_NAME = 'Trình ký PDF có sẵn'

Document document = DataTest.createDocumentTest(auto5, auto6, null, 'dummy.pdf', 'dummy.pdf')

IPaper.loginScreen.login(auto5)

IPaper.homeScreen.openRequestList()

IPaper.homeScreen.createRequest(REQUEST_NAME)

IPaper.pdfSignScreen.fillInTitle(document.title)

IPaper.pdfSignScreen.selectPriority(document.priority)

IPaper.pdfSignScreen.selectTime(document.time)

IPaper.pdfSignScreen.fillInDescription(document.description)

IPaper.pdfSignScreen.selectAssigner(document.getAssigner().getEmail())

IPaper.pdfSignScreen.openMainFileBrowser()

IPaper.fileBrowserScreen.attachFile(document.mainFileName)

IPaper.pdfSignScreen.openSubFileBrowser()

IPaper.fileBrowserScreen.attachFile(document.subFileName)

IPaper.pdfSignScreen.performAction(PerformAction.SEND_APPROVE)

IPaper.pdfSignScreen.fillInOpinion(document.getComment())

IPaper.pdfSignScreen.submitRequest()

document.setStatus(DocumentStatus.WAIT_APPROVE)

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

// ======================== CHECK ENABLED ========================
IPaper.loginScreen.login(auto6)

IPaper.homeScreen.goToSetting()

IPaper.settingScreen.enableEditFileAttach(true)

IPaper.settingScreen.backToHome()

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewMainFile(document)

IPaper.documentInformationScreen.isEditFileIconDisplayed()

// ======================== CHECK DISABLED ========================

IPaper.homeScreen.goToSetting()

IPaper.settingScreen.enableEditFileAttach(false)

IPaper.settingScreen.backToHome()

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewMainFile(document)

IPaper.documentInformationScreen.isEditFileIconNotDisplayed()