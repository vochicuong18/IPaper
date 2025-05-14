import entities.Document
import ipaper.IPaper
import screens.PDFSignScreen.PerformAction
import utilities.DataTest
import utilities.Utilities as Utilities

String FOLDER_NAME = 'IPaperDownloads'

String REQUEST_NAME = 'Trình ký PDF có sẵn'

def auto5 = DataTest.getUserTest('auto5')

def auto6 = DataTest.getUserTest('auto6')

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

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.viewMainFile(document)

IPaper.documentInformationScreen.downloadFileAtmainFile()

Utilities.closeCurentApp()

Utilities.openFileManagerApp()

IPaper.fileManager_HomeScreen.goToIPaperFolder(FOLDER_NAME)

IPaper.fileManager_FolderScreen.checkPDFFiles()

IPaper.fileManager_FolderScreen.deleteAllFiles()

Utilities.closeCurentApp()