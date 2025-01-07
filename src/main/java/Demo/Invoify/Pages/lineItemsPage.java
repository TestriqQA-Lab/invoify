package Demo.Invoify.Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class lineItemsPage {
	
	WebDriver driver;
	Properties prop;

	public lineItemsPage(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		String path = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(path + "/src/main/java/Demo/Invoify/Resources/data.properties");
		prop = new Properties();
		prop.load(fis);
	}
	
	@FindBy(xpath = "//button[text()='Add a new item']")
	private WebElement addItemBtn;
	
	@FindBy(xpath = "//input[@placeholder='Item name']")
	private List<WebElement> inputItemName;
	
	@FindBy(xpath = "//input[@placeholder='Quantity']")
	private List<WebElement> inputQuantity;
	
	@FindBy(xpath = "//input[@placeholder='Rate']")
	private List<WebElement> inputRate;
	
	@FindBy(xpath = "//textarea[@placeholder='Item description']")
	private List<WebElement> inputDescription;
	
	@FindBy(xpath = "//button[text()='Remove item']")
	private List<WebElement> removeItemBtn;
	
	@FindBy(xpath = "//div[@role='button']//div[@class='flex gap-3']/div")
	private List<WebElement> scrollItemListButton;
	
	@FindBy(xpath = "//div[@role='button']//div[@class='flex gap-3']//button[not(@disabled)]")
	private List<WebElement> itemSorting;
	
	@FindBy(xpath = "//input[@placeholder='Item total']")
	private WebElement totalAmount;
	
	@FindBy(xpath = "//div[@aria-roledescription='sortable']")
	private List<WebElement> listOfItems;
	
	public void enterQuantity() throws InterruptedException {
		inputQuantity.get(0).sendKeys(Keys.CONTROL + "a");
		inputQuantity.get(0).sendKeys(Keys.BACK_SPACE);
		inputQuantity.get(0).sendKeys(prop.getProperty("quantity"));
	}
	
	public void enterRate() {
		inputRate.get(0).sendKeys(Keys.CONTROL + "a");
		inputRate.get(0).sendKeys(Keys.BACK_SPACE);
		inputRate.get(0).sendKeys(prop.getProperty("rate"));
	}
	
	public Double getTotalAmountFromItem() {
		String amount = totalAmount.getAttribute("value");
		String onlyNumericValue = amount.split(" ")[0];
		return Double.parseDouble(onlyNumericValue);
	}
	
	public Double expectedTotalAmount() {
		double quantity = Double.parseDouble(prop.getProperty("quantity"));
		double rate = Double.parseDouble(prop.getProperty("rate"));
		return quantity*rate;
	}
	
	public void addnewItem() {
		addItemBtn.click();
	}
	
	public int getTotalItemInList() {
		return listOfItems.size();
	}
	
	public void removeNewAddedItem() {
		removeItemBtn.get(listOfItems.size()-1).click();
	}
	
}
