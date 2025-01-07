package Demo.Invoify.tests;

import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.Assert;

import Demo.Invoify.Pages.actionAndPreviewPage;
import Demo.Invoify.Pages.homePage;
import Demo.Invoify.Pages.summaryPage;
import Demo.Invoify.core.DriverManagement;

public class test_SummaryPage extends DriverManagement {
	
	homePage home;
	summaryPage summaryPage;
	actionAndPreviewPage preview;
	
	public void initPages() throws IOException {
		home = new homePage(driver);
		summaryPage = new summaryPage(driver);
		preview = new actionAndPreviewPage(driver);
		home.switchTab("summary");
	}
	
	@Test(priority = 1)
	public void testToggleButtonEnabling() throws IOException {
		initPages();
		summaryPage.enableToggle("discount");
		Assert.assertTrue(summaryPage.checkIfDiscountEnabled());
		summaryPage.disableToggle("discount");
		summaryPage.enableToggle("tax");
		Assert.assertTrue(summaryPage.checkIfTaxEnabled());
		summaryPage.disableToggle("tax");
		summaryPage.enableToggle("shipping");
		Assert.assertTrue(summaryPage.checkIfShippingEnabled());
		summaryPage.disableToggle("shipping");
	}
	
	@Test(priority = 2)
	public void testPercentageChange() throws IOException {
		initPages();
		summaryPage.enableToggle("discount");
		summaryPage.verifyUnitConversion("discount");
		summaryPage.enableToggle("tax");
		summaryPage.verifyUnitConversion("tax");
		summaryPage.enableToggle("shipping");
		summaryPage.verifyUnitConversion("shipping");
	}
	
	@Test(priority = 3)
	public void testSignatureUpload() throws IOException {
		initPages();
		summaryPage.uploadSignature();
		Assert.assertTrue(summaryPage.verifySignatureUpload());
		Assert.assertTrue(preview.verifySignature());
	}
	
}
