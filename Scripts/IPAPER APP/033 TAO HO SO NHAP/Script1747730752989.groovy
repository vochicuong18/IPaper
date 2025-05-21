import entities.DocumentStatus
import entities.User
import ipaper.IPaper
import screens.DocumentInformationScreen.ActionType
import screens.PDFSignScreen.PerformAction
import utilities.DataTest as DataTest

String REQUEST_NAME = 'Trình ký PDF có sẵn'

String COMMENT = 'Commented by automation team'

User auto5 = DataTest.getUserTest5()

User auto6 = DataTest.getUserTest6()


def document = DataTest.createDocumentTest(auto5, auto6, null, 'dummy.pdf', 'dummy.pdf')

//create document
IPaper.loginScreen.login(auto5)

IPaper.homeScreen.openRequestList()

IPaper.homeScreen.createRequest(REQUEST_NAME)

IPaper.pdfSignScreen.fillInTitle(document.getTitle())

IPaper.pdfSignScreen.selectPriority(document.getPriority())

IPaper.pdfSignScreen.selectTime(document.getTime())

IPaper.pdfSignScreen.fillInDescription(document.getDescription())

IPaper.pdfSignScreen.selectAssigner(document.getAssigner().getEmail())

//IPaper.pdfSignScreen.openMainFileBrowser()
//
//IPaper.fileBrowserScreen.attachFile(document.getMainFileName())
//
//IPaper.pdfSignScreen.openSubFileBrowser()
//
//IPaper.fileBrowserScreen.attachFile(document.getSubFileName())

IPaper.pdfSignScreen.performAction(PerformAction.SAVE_DRAFT)

document.setStatus(DocumentStatus.DRAFTED)

IPaper.documentInformationScreen.checkDocumentTitle(document)

IPaper.documentInformationScreen.checkSender(document)

IPaper.documentInformationScreen.checkStatus(document)

IPaper.documentInformationScreen.checkCreateDate()

IPaper.documentInformationScreen.checkFinishDate(document)

IPaper.documentInformationScreen.checkPriority(document)

IPaper.documentInformationScreen.checkDescription(document)

IPaper.documentInformationScreen.checkAssigner(document)

//IPaper.documentInformationScreen.checkPresentFileName(document)

//IPaper.documentInformationScreen.checkAttachFileName(document)

IPaper.documentInformationScreen.backToHome()

IPaper.homeScreen.goToDraftDocument()

IPaper.draftDocumentScreen.loadDataResult()

IPaper.draftDocumentScreen.viewInformationDocument(document)

IPaper.draftDocumentScreen.backToHome()

IPaper.draftDocumentScreen.checkItemInDocument()