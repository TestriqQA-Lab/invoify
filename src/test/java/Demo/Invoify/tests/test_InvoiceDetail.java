package Demo.Invoify.tests;

import org.testng.annotations.Test;


import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;

import Demo.Invoify.Core.DriverManagement;
import Demo.Invoify.Pages.actionAndPreviewPage;
import Demo.Invoify.Pages.homePage;
import Demo.Invoify.Pages.invoiceDetails;

public class test_InvoiceDetail extends DriverManagement {

	homePage home;
	invoiceDetails invoiceDetail;
	actionAndPreviewPage preview;

	public void initPages() throws IOException {
		home = new homePage(driver);
		invoiceDetail = new invoiceDetails(driver);
		preview = new actionAndPreviewPage(driver);
		home.switchTab("invoiceDetails");
	}
	
	@Test(priority = 1)
	public void testLogoIsDisplayedInPreview() throws IOException {
		initPages();
		invoiceDetail.addImage();
		Assert.assertTrue(invoiceDetail.verifyLogoIsDisplayedInPreview());
		invoiceDetail.removeLogo();
		Assert.assertFalse(invoiceDetail.verifyLogoIsDisplayedInPreview());
	}
	
	@Test(priority = 2)
	public void testAmountCurrencyChangesCorrectly() throws IOException {
		initPages();
		invoiceDetail.selectCurrency();
		String currencySelected = invoiceDetail.getCurrency().replaceAll(".*\\((.*?)\\).*", "$1");
		String currencyInPreview = preview.getAmount().replaceAll("[^A-Z]", "");
		AssertJUnit.assertEquals(currencySelected, currencyInPreview);
	}
	
}
