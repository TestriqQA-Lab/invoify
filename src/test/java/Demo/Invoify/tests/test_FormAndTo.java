package Demo.Invoify.tests;

import org.testng.annotations.Test;


import java.io.IOException;

import org.testng.Assert;

import Demo.Invoify.Core.DriverManagement;
import Demo.Invoify.Pages.homePage;
import Demo.Invoify.Pages.invoiceDetails;

public class test_FormAndTo extends DriverManagement {

	@Test(priority = 1)
	public void testLogoIsDisplayed() throws IOException {
		homePage home = new homePage(driver);
		Assert.assertTrue(home.verifyingLogoIsDisplayed());
	}

//	@Test(priority = 2)
	public void testAddingNewCustomInput() throws IOException {
		homePage home = new homePage(driver);
		int numberOfInputInBillFrom = home.numberOfInputFieldInBillFrom();
		home.clickOnAddCustomInput(0);
		int updatedNumberOfInputInBillForm = home.numberOfInputFieldInBillFrom();
		Assert.assertEquals(updatedNumberOfInputInBillForm, numberOfInputInBillFrom + 1);
		int numberOfInputInBillTo = home.numberOfInputFieldInBillTo();
		home.clickOnAddCustomInput(1);
		int updatedNumberOfInputInBillTo = home.numberOfInputFieldInBillTo();
		Assert.assertEquals(updatedNumberOfInputInBillTo, numberOfInputInBillTo + 1);
		home.removeCustomInput("billForm");
		home.removeCustomInput("billTo");
	}

//	@Test(priority = 3)
	public void testNextButtonFunctionality() throws IOException {
		homePage home = new homePage(driver);
		invoiceDetails invoiceDetail = new invoiceDetails(driver);
		home.clickOnNextButton();
		Assert.assertTrue(invoiceDetail.getTitleName().contains("Invoice Details"));
	}

}
