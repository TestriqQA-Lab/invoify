package Demo.Invoify.tests;

import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.Assert;

import Demo.Invoify.Pages.actionAndPreviewPage;
import Demo.Invoify.Pages.homePage;
import Demo.Invoify.Pages.lineItemsPage;
import Demo.Invoify.core.DriverManagement;

public class test_LineItems extends DriverManagement {

	homePage home;
	lineItemsPage item;
	actionAndPreviewPage preview;

	public void initPages() throws IOException {
		home = new homePage(driver);
		item = new lineItemsPage(driver);
		preview = new actionAndPreviewPage(driver);
		home.switchTab("lineItems");
	}
	
	@Test(priority = 1)
	public void testProperTotalCalculation() throws InterruptedException, IOException {
		initPages();
		item.enterQuantity();
		item.enterRate();
		Assert.assertEquals(item.getTotalAmountFromItem(), item.expectedTotalAmount());
	}
	
	@Test(priority = 2)
	public void testAddANewitem() throws IOException {
		initPages();
		int listCountBeforeAddingItem = item.getTotalItemInList();
		item.addnewItem();
		int listCountAfterAddingItem = item.getTotalItemInList();
		Assert.assertTrue(listCountBeforeAddingItem!=listCountAfterAddingItem);
		Assert.assertEquals(listCountAfterAddingItem, listCountBeforeAddingItem+1);
		item.removeNewAddedItem();
		int listCountAfterRemovingItem = item.getTotalItemInList();
		Assert.assertTrue(listCountBeforeAddingItem==listCountAfterRemovingItem);
	}
	
	
}
