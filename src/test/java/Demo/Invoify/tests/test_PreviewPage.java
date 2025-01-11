package Demo.Invoify.tests;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;

import Demo.Invoify.Core.DriverManagement;
import Demo.Invoify.Pages.actionAndPreviewPage;
import Demo.Invoify.Pages.homePage;

public class test_PreviewPage extends DriverManagement {

	homePage home;
	actionAndPreviewPage preview;
	
	public void initPages() throws IOException {
		home = new homePage(driver);
		preview = new actionAndPreviewPage(driver);
	}
	
	@Test(priority = 1)
	public void testInvoiceSaving() throws IOException {
		initPages();
		preview.fillTheForm();
		preview.generatePDF();
		Assert.assertEquals(preview.getMessage(), "Your invoice has been generated!");
		preview.saveInvoice();
		Assert.assertEquals(preview.getMessage(), "Saved Invoice");
		preview.loadInvoice();
		Assert.assertTrue(preview.verifySavedInvoiceInLoadInvoice());
		preview.deleteInvoiceFromLoadInvoice();
	}
	
	@Test(priority = 2)
	public void testGeneratingNewInvoice() throws IOException {
		initPages();
		preview.addNewInvoice();
		Assert.assertEquals(preview.getMessage(), "Generated new invoice");
	}
	
	@Test(priority = 3)
	public void testLoadingInvoiceFromDevice() throws IOException {
		initPages();
		preview.loadInvoiceFromDevice();
		String sender = preview.getSendersNameFromPreview();
		Assert.assertTrue(sender.length()!=0);
	}
	
	@Test(priority = 4)
	public void testSendingGeneratedPdfViaMail() throws IOException {
		initPages();
		preview.generatePDF();
		Assert.assertEquals(preview.getMessage(), "Your invoice has been generated!");
		preview.enterInvalidEmailAndSendMail();
		Assert.assertEquals(preview.verifyErrorMessageOfInvalidMail(),"Please enter a valid email address");
		preview.enterEmailAndSendMail();
		Assert.assertEquals(preview.getMessage(), "Your invoice has been generated!");
	}
	
}
