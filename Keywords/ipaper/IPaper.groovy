package ipaper

import screens.DocumentInformationScreen
import screens.FileBrowserScreen
import screens.HomeScreen
import screens.IncomingDocumentScreen
import screens.LoginScreen
import screens.OutComingDocumentScreen
import screens.PDFSignScreen
import screens.ProfileScreen
import screens.RelatedDocumentScreen


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
}
