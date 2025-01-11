package Demo.Invoify.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Demo.Invoify.Core.DriverManagement;
import Demo.Invoify.Pages.actionAndPreviewPage;
import Demo.Invoify.Pages.homePage;
import Demo.Invoify.Pages.invoiceDetails;
import Demo.Invoify.Pages.summaryPage;
import Demo.Invoify.TestComponant.RerunScript;

public class verifyAttachmentUpload extends DriverManagement {
	
	
	@Test(retryAnalyzer = RerunScript.class)
	public void verifyBillAttachement() throws IOException {
		homePage home = new homePage(driver);
		home.switchTab("invoiceDetails");
		invoiceDetails invoiceDetail = new invoiceDetails(driver);
		invoiceDetail.addImage();
		Assert.assertTrue(invoiceDetail.verifyLogoIsDisplayedInPreview());
		invoiceDetail.removeLogo();
		Assert.assertFalse(invoiceDetail.verifyLogoIsDisplayedInPreview());
	}
	
	@Test
	public void testSignatureUpload() throws IOException {
		homePage home = new homePage(driver);
		home.switchTab("summary");
		summaryPage summaryPage = new summaryPage(driver);
		summaryPage.uploadSignature();
		Assert.assertTrue(summaryPage.verifySignatureUpload());
		actionAndPreviewPage preview = new actionAndPreviewPage(driver);
		Assert.assertTrue(preview.verifySignature());
	}

}
