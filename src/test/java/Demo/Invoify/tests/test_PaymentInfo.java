package Demo.Invoify.tests;

import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.Assert;

import Demo.Invoify.Pages.actionAndPreviewPage;
import Demo.Invoify.Pages.homePage;
import Demo.Invoify.Pages.paymentInfoPage;
import Demo.Invoify.core.DriverManagement;

public class test_PaymentInfo extends DriverManagement {

	homePage home;
	paymentInfoPage paymentInfo;
	actionAndPreviewPage preview;
	
	public void initPages() throws IOException {
		home = new homePage(driver);
		paymentInfo = new paymentInfoPage(driver);
		preview = new actionAndPreviewPage(driver);
		home.switchTab("paymentInfo");
	}
	
	@Test(priority = 1)
	public void testBankDetailsAreEntered() throws IOException {
		initPages();
		paymentInfo.enterBankDetails();
		String bankName = preview.getBankName();
		String accName = preview.getAccName();
		String accNumber = preview.getAccNumber();
		Assert.assertTrue(paymentInfo.verifyAccountDetails(bankName, accName, accNumber));
	}
	
}
