package Demo.Invoify.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Demo.Invoify.Core.DriverManagement;
import Demo.Invoify.Pages.actionAndPreviewPage;
import Demo.Invoify.Pages.homePage;
import Demo.Invoify.Pages.lineItemsPage;
import Demo.Invoify.Pages.paymentInfoPage;
import Demo.Invoify.Pages.summaryPage;

public class dataInputValidation extends DriverManagement {

	@Test(priority = 1)
	public void testBankDetailsAreEntered() throws IOException {
		homePage home = new homePage(driver);
		home.switchTab("paymentInfo");
		paymentInfoPage paymentInfo = new paymentInfoPage(driver);
		paymentInfo.enterBankDetails();
		actionAndPreviewPage preview = new actionAndPreviewPage(driver);
		String bankName = preview.getBankName();
		String accName = preview.getAccName();
		String accNumber = preview.getAccNumber();
		Assert.assertTrue(paymentInfo.verifyAccountDetails(bankName, accName, accNumber));
	}

	@Test(priority = 2)
	public void testProperTotalCalculation() throws InterruptedException, IOException {
		homePage home = new homePage(driver);
		home.switchTab("lineItems");
		lineItemsPage item = new lineItemsPage(driver);
		item.enterQuantity();
		item.enterRate();
		Assert.assertEquals(item.getTotalAmountFromItem(), item.expectedTotalAmount());
	}

	@Test(priority = 3)
	public void testToggleButtonEnabling() throws IOException {
		homePage home = new homePage(driver);
		home.switchTab("summary");
		summaryPage summaryPage = new summaryPage(driver);
		summaryPage.enableToggle("discount");
		summaryPage.verifyUnitConversion("discount");
		summaryPage.enableToggle("tax");
		summaryPage.verifyUnitConversion("tax");
		summaryPage.enableToggle("shipping");
		summaryPage.verifyUnitConversion("shipping");
	}

}
