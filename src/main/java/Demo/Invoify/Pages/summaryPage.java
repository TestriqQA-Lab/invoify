package Demo.Invoify.Pages;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Demo.Invoify.Core.PageObjectFacilitator;

public class summaryPage extends PageObjectFacilitator {

	WebDriver driver;
	Properties prop;
	
	public summaryPage(WebDriver driver) throws IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		prop = loadData();
	}
	
	@FindBy(xpath = "//label[text()='Discount']/parent::div//button")
	private WebElement discountToggle;
	
	@FindBy(xpath = "//label[text()='Tax']/parent::div//button")
	private WebElement taxToggle;
	
	@FindBy(xpath = "//label[text()='Shipping']/parent::div//button")
	private WebElement shippingToggle;
	
	@FindBy(xpath = "//p[text()='Include Total in Words?']/parent::div//button")
	private WebElement amountInWordToggle;
	
	@FindBy(xpath = "textarea[name='details.additionalNotes']")
	private WebElement inputAddNotes;
	
	@FindBy(xpath = "textarea[name='details.paymentTerms']")
	private WebElement inputPaymentTerms;
	
	@FindBy(xpath = "//label[text()='Click to add signature']/parent::div")
	private WebElement addSignButton;
	
	@FindBy(xpath = "//div[@role='tablist']/button[text()='Type']")
	private WebElement typeSignatureBtn;
	
	@FindBy(xpath = "//div[@role='tablist']/button[text()='Upload']")
	private WebElement uploadSignature;
	
	@FindBy(xpath = "//input[@type='file']")
	private WebElement addSignatureFromFolder;
	
	@FindBy(id = "typed-signature")
	private WebElement inputSignature;
	
	@FindBy(xpath = "//button[text()='Done']")
	private WebElement doneBtn;
	
	@FindBy(xpath = "//span[text()='Close']//parent::button")
	private WebElement closeSignPopUp;
	
	@FindBy(xpath = "//div[text()='Discount']//parent::div/div[2]/div[2]")
	private WebElement discount_currencyUnit;
	
	@FindBy(xpath = "//div[text()='Tax']//parent::div/div[2]/div[2]")
	private WebElement tax_currencyUnit;
	
	@FindBy(xpath = "//div[text()='Shipping']//parent::div/div[2]/div[2]")
	private WebElement shipping_currencyUnit;
	
	@FindBy(xpath = "//div[text()='Discount']//parent::div//button")
	private WebElement discount_USD_Percentage;
	
	@FindBy(xpath = "//div[text()='Tax']//parent::div//button")
	private WebElement tax_USD_Percentage;
	
	@FindBy(xpath = "//div[text()='Shipping']//parent::div//button")
	private WebElement shipping_USD_Percentage;
	
	@FindBy(xpath = "//div[text()='Discount']//parent::div//input")
	private WebElement discountInput;
	
	@FindBy(xpath = "//div[text()='Tax']//parent::div//input")
	private WebElement taxInput;
	
	@FindBy(xpath = "//div[text()='Shipping']//parent::div//input")
	private WebElement shippingInput;
	
	@FindBy(xpath = "//label[text()='Signature']//parent::div//img")
	private WebElement signature;
	
	public void enableToggle(String name) {
		switch(name) {
		case "discount":
			discountToggle.click();
			break;
		case "tax":
			taxToggle.click();
			break;
		case "shipping":
			shippingToggle.click();
			break;
		}
	}
	
	public void disableToggle(String name) {
		switch(name) {
		case "discount":
			discountToggle.click();
			break;
		case "tax":
			taxToggle.click();
			break;
		case "shipping":
			shippingToggle.click();
			break;
		}
	}
	
	public void convertToPercentage(String name) {
		switch(name) {
		case "discount":
			discount_USD_Percentage.click();
			break;
		case "tax":
			tax_USD_Percentage.click();
			break;
		case "shipping":
			shipping_USD_Percentage.click();
			break;
		}
	}
	
	public boolean checkIfDiscountEnabled() {
		return discountInput.isDisplayed();
	}
	
	public boolean checkIfTaxEnabled() {
		return taxInput.isDisplayed();
	}
	
	public boolean checkIfShippingEnabled() {
		return shippingInput.isDisplayed();
	}
	
	public void verifyUnitConversion(String name) {
		switch(name) {
		case "discount":
			isElementDisplayed(discount_currencyUnit);
			clickElement(discount_USD_Percentage);
			isElementDisplayed(discount_currencyUnit);
			break;
			
		case "tax":
			isElementDisplayed(tax_currencyUnit);
			clickElement(tax_USD_Percentage);
			isElementDisplayed(tax_currencyUnit);
			break;
			
		case "shipping":
			isElementDisplayed(shipping_currencyUnit);
			clickElement(shipping_USD_Percentage);
			isElementDisplayed(shipping_currencyUnit);
			break;
		}
	}
	
	public boolean verifySignatureUpload() {
		return isElementDisplayed(signature);
	}
	
	public void uploadSignature() {
		clickElement(addSignButton);
		clickElement(uploadSignature);
		addSignatureFromFolder.sendKeys(System.getProperty("user.dir") + prop.getProperty("imgSource"));
		clickElement(doneBtn);
	}
	
	
}
