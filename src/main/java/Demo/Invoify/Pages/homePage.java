package Demo.Invoify.Pages;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Demo.Invoify.Core.PageObjectFacilitator;

public class homePage extends PageObjectFacilitator {

	WebDriver driver;
	Properties prop;

	public homePage(WebDriver driver) throws IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		prop = loadData();
	}

	@FindBy(xpath = "//img[@alt='Invoify Logo']")
	private WebElement appLogo;

	@FindBy(xpath = "//div//input[@placeholder='Your name'][@name='sender.name']")
	private WebElement senderName;

	@FindBy(xpath = "//input[@name='sender.address']")
	private WebElement senderAddress;

	@FindBy(xpath = "//input[@name='sender.zipCode']")
	private WebElement senderZipCode;

	@FindBy(xpath = "//input[@name='sender.city']")
	private WebElement senderCity;

	@FindBy(xpath = "//input[@name='sender.country']")
	private WebElement senderCountry;

	@FindBy(xpath = "//input[@name='sender.email']")
	private WebElement senderEmail;

	@FindBy(xpath = "//input[@name='sender.phone']")
	private WebElement senderPhoneNo;

	@FindBy(xpath = "//input[@name='receiver.name']")
	private WebElement receiverName;

	@FindBy(xpath = "//input[@name='receiver.address']")
	private WebElement receiverAddress;

	@FindBy(xpath = "//input[@name='receiver.zipCode']")
	private WebElement receiverZipCode;

	@FindBy(xpath = "//input[@name='receiver.city']")
	private WebElement receiverCity;

	@FindBy(xpath = "//input[@name='receiver.country']")
	private WebElement receiverCountry;

	@FindBy(xpath = "//input[@name='receiver.email']")
	private WebElement receiverEmail;

	@FindBy(xpath = "//input[@name='receiver.phone']")
	private WebElement receiverPhoneNo;

	@FindBy(xpath = "//button[contains(text(),'1')]")
	private WebElement formAndToTab;

	@FindBy(xpath = "//button[contains(text(),'2')]")
	private WebElement invoiceDetailTab;

	@FindBy(xpath = "//button[contains(text(),'3')]")
	private WebElement lineItemsTab;

	@FindBy(xpath = "//button[contains(text(),'4')]")
	private WebElement paymentInfotab;

	@FindBy(xpath = "//button[contains(text(),'5')]")
	private WebElement summaryTab;
	
	@FindBy(xpath = "//button[text()='Next']")
	private WebElement nextButton;
	
	@FindBy(xpath = "//button[text()='Back']")
	private WebElement backButton;
	
	@FindBy(xpath = "//button[text()='Add Custom Input']")
	private List<WebElement> addCustomInput;
	
	@FindBy(xpath = "//h4[text()='Bill From']//parent::section/div")
	private List<WebElement> billFormInputList;
	
	@FindBy(xpath = "//h4[text()='Bill To']//parent::section/div")
	private List<WebElement> billToInputList;
	
	@FindBy(xpath = "//h4[text()='Bill From']//parent::section/div//button")
	private WebElement removeCustomInputForBillFrom;
	
	@FindBy(xpath = "//h4[text()='Bill To']//parent::section/div//button")
	private WebElement removeCustomInputForBillTo;
	
	public Boolean verifyingLogoIsDisplayed() {
		return appLogo.isDisplayed();
	}

	public void switchTab(String tabName) {
		switch (tabName) {
		case "form&to":
			try {
				formAndToTab.click();
				break;
			} catch (Exception e) {
			}
		case "invoiceDetails":
			try {
				invoiceDetailTab.click();
				break;
			} catch (Exception e) {
			}
		case "lineItems":
			try {
				lineItemsTab.click();
				break;
			} catch (Exception e) {
			}
		case "paymentInfo":
			try {
				paymentInfotab.click();
				break;
			} catch (Exception e) {
			}
		case "summary":
			try {
				summaryTab.click();
				break;
			} catch (Exception e) {
			}
		}
	}
	
	public void clickOnAddCustomInput(int index) {
		addCustomInput.get(index).click();
	}
	
	public void removeCustomInput(String name) {
		switch (name) {
		case "billForm":
			removeCustomInputForBillFrom.click();
			break;
		case "billTo":
			removeCustomInputForBillTo.click();
		}
	}
	
	public int numberOfInputFieldInBillFrom() {
		return billFormInputList.size();
	}
	
	public int numberOfInputFieldInBillTo() {
		return billToInputList.size();
	}
	
	public void clickOnNextButton() {
		nextButton.click();
	}
	
}
