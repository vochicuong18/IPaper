import ipaper.IPaper as IPaper
import utilities.DataTest as DataTest

def user = DataTest.getUserA5NVTest()

IPaper.loginScreen.login(user)

IPaper.homeScreen.goToOutComingDocument()

IPaper.outComingDocument.checkItemInDocument()
