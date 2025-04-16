import ipaper.IPaper as IPaper
import utilities.DataTest as DataTest

def userA3NV = DataTest.getUserA3NVTest()

IPaper.loginScreen.login(userA3NV)

IPaper.homeScreen.goToIncomingDocument()

IPaper.inComingDocument.checkItemInDocument()
