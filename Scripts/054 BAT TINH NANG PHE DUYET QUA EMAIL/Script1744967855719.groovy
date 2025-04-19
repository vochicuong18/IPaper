import entities.Document as Document
import ipaper.IPaper as IPaper
import utilities.DataTest as DataTest
import utilities.Utilities as Utilities

String REQUEST_NAME = 'Trình ký PDF có sẵn'

def userA3NV = DataTest.getUserA3NVTest()

def userA4NV = DataTest.getUserA4NVTest()

Document document = DataTest.createDocumentTest(userA4NV, null, 'dummy.pdf', 'dummy.pdf')

IPaper.loginScreen.login(userA3NV)

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

IPaper.pdfSignScreen.selectAssigner(document.getAssigner())

IPaper.pdfSignScreen.openMainFileBrowser()

IPaper.fileBrowserScreen.attachFile(document.getMainFileName())

IPaper.pdfSignScreen.openSubFileBrowser()

IPaper.fileBrowserScreen.attachFile(document.getMainFileName())

IPaper.pdfSignScreen.sendRequest()

IPaper.pdfSignScreen.fillInOpinion(document.getOpinion())

IPaper.pdfSignScreen.submitRequest()

Utilities.openOutlookApp()

IPaper.outlook_homeScreen.searchEmail('test')