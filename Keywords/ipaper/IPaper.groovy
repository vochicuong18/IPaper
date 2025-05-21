package ipaper

import screens.DocumentInformationScreen
import screens.DraftDocumentScreen
import screens.FileBrowserScreen
import screens.FileManager_FolderScreen
import screens.FileManager_HomeScreen
import screens.HomeScreen
import screens.IncomingDocumentScreen
import screens.LoginScreen
import screens.OutComingDocumentScreen
import screens.OutLook_HomeScreen
import screens.OutLook_MailScreen
import screens.PDFSignScreen
import screens.ProfileScreen
import screens.RelatedDocumentScreen
import screens.SettingScreen


public class IPaper {
	public static HomeScreen homeScreen = new HomeScreen()
	public static LoginScreen loginScreen = new LoginScreen()
	public static ProfileScreen profileScreen = new ProfileScreen()
	public static PDFSignScreen pdfSignScreen = new PDFSignScreen()
	public static FileBrowserScreen fileBrowserScreen = new FileBrowserScreen()
	public static IncomingDocumentScreen inComingDocument = new IncomingDocumentScreen()
	public static OutComingDocumentScreen outComingDocument = new OutComingDocumentScreen()
	public static RelatedDocumentScreen relatedDocumentScreen = new RelatedDocumentScreen()
	public static DocumentInformationScreen documentInformationScreen = new DocumentInformationScreen()
	public static SettingScreen settingScreen = new SettingScreen()
	public static OutLook_HomeScreen outlook_homeScreen = new OutLook_HomeScreen()
	public static OutLook_MailScreen outlook_mailScreen = new OutLook_MailScreen()
	public static FileManager_HomeScreen fileManager_HomeScreen = new FileManager_HomeScreen()
	public static FileManager_FolderScreen fileManager_FolderScreen = new FileManager_FolderScreen()
	public static DraftDocumentScreen draftDocumentScreen = new DraftDocumentScreen()
}
