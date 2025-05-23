import entities.Document
import entities.User
import ipaper.IPaper
import screens.PDFSignScreen.PerformAction
import utilities.DataTest
import utilities.Utilities as Utilities

String FOLDER_NAME = 'IPaperDownloads'

String REQUEST_NAME = 'Trình ký PDF có sẵn'

User auto5 = DataTest.getUserTest5()

User auto6 = DataTest.getUserTest6()

Document document = DataTest.createDocumentTest(auto5, auto6, null, 'dummy.pdf', 'dummy.docx')

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

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.logout()

IPaper.loginScreen.login(auto6)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.viewInformationDocument(document)

IPaper.documentInformationScreen.downloadFile()

Utilities.closeCurentApp()

Utilities.openFileManagerApp()

IPaper.fileManager_HomeScreen.goToIPaperFolder(FOLDER_NAME)

IPaper.fileManager_FolderScreen.checkPDFFiles()

IPaper.fileManager_FolderScreen.deleteAllFiles()

Utilities.closeCurentApp()