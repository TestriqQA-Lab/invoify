package Demo.Invoify.tests;

import java.io.IOException;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Demo.Invoify.Core.DriverManagement;
import Demo.Invoify.Pages.actionAndPreviewPage;
import Demo.Invoify.Pages.homePage;
import Demo.Invoify.Pages.invoiceDetails;
import Demo.Invoify.TestComponant.RerunScript;

public class validatingInvoiceActions extends DriverManagement {
	
	@Test
	public void testNavigation() throws IOException {
		homePage home = new homePage(driver);
		Assert.assertTrue(home.verifyingLogoIsDisplayed());
		home.clickOnNextButton();
		invoiceDetails invoiceDetail = new invoiceDetails(driver);
		Assert.assertTrue(invoiceDetail.getTitleName().contains("Invoice Details"));
	}
	
	@Test(retryAnalyzer = RerunScript.class)
	public void testInvoiceSaving() throws IOException {
		actionAndPreviewPage preview = new actionAndPreviewPage(driver);
		preview.loadInvoiceFromDevice();
		preview.generatePDF();
		Assert.assertEquals(preview.getMessage(), "Your invoice has been generated!");
		preview.enterInvalidEmailAndSendMail();
		Assert.assertEquals(preview.verifyErrorMessageOfInvalidMail(),"Please enter a valid email address");
		preview.enterEmailAndSendMail();
		Assert.assertEquals(preview.getMessage(), "Your invoice has been generated!");
	}
	
}
