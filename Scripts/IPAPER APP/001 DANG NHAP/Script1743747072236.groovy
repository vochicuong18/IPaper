import ipaper.IPaper
import utilities.DataTest
def user = DataTest.getUserTest('auto1')

IPaper.loginScreen.login(user)

IPaper.homeScreen.expandMenu()
        
IPaper.homeScreen.goToProfileInfo()

IPaper.profileScreen.checkUserEmail(user.email)

IPaper.profileScreen.checkUserName(user.userName)

IPaper.profileScreen.checkName(user.name)