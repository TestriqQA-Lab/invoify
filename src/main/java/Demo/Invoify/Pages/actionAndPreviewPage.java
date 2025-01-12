package Demo.Invoify.Pages;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Demo.Invoify.Core.PageObjectFacilitator;

public class actionAndPreviewPage extends PageObjectFacilitator {

	WebDriver driver;
	Properties prop;
	
	public actionAndPreviewPage(WebDriver driver) throws IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		prop = loadData();
	}
	
	@FindBy(xpath = "//button[text()='Load Invoice']")
	private WebElement loadInvoiceBtn;
	
	@FindBy(xpath = "//button[text()='Delete']")
	private WebElement deleteInvoice;
	
	@FindBy(xpath = "//button[text()='Export Invoice']")
	private WebElement exportInvoiceBtn;
	
	@FindBy(xpath = "//button[text()='Export as JSON']")
	private WebElement exportAsJsonBtn;
	
	@FindBy(xpath = "//button[text()='Export as CSV']")
	private WebElement exportAsCSVBtn;
	
	@FindBy(xpath = "//button[text()='Export as XML']")
	private WebElement exportAsXMLBtn;
	
	@FindBy(xpath = "//button[text()='Export as XLSX']")
	private WebElement exportAsXLSXBtn;
	
	@FindBy(xpath = "//input[@type='file']")
	private WebElement addJsonInvoiceFile;
	
	@FindBy(xpath = "//button[text()='New Invoice']")
	private WebElement addNewInvoice;
	
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement cancelAddingInvoice;
	
	@FindBy(xpath = "//button[text()='Create new invoice']")
	private WebElement createInvoice;
	
	@FindBy(xpath = "//button[text()='Generate PDF']")
	private WebElement generatePDFBtn;
	
	@FindBy(xpath = "//button[text()='Back to Live Preview']")
	private WebElement backToLivePreview;
	
	@FindBy(xpath = "//button[text()='Preview']")
	private WebElement openPreview;
	
	@FindBy(xpath = "//button[text()='Download']")
	private WebElement downloadInvoice;
	
	@FindBy(xpath = "//button[text()='Print']")
	private WebElement printInvoice;
	
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveInvoice;
	
	@FindBy(xpath = "//button[text()='Send to mail']")
	private WebElement sendInvoice;
	
	@FindBy(xpath = "//input[@type='email']")
	private WebElement inputEmail;
	
	@FindBy(xpath = "//button[text()='Send PDF']")
	private WebElement sendPDF;
	
	@FindBy(xpath = "//span[text()='Close']//parent::button")
	private WebElement closeSignPopUp;
	
	@FindBy(css = "p[class*='text-right']")
	private WebElement amount;
	
	@FindBy(xpath = "//span[text()='Please send the payment to this address']//parent::div//p")
	private List<WebElement> paymentTerms;
	
	@FindBy(xpath = "//p[contains(text(),'Signature')]//parent::div//img")
	private WebElement signature;
	
	@FindBy(xpath = "//button[text()='Fill in the form']")
	private WebElement fillFormBtn;
	
	@FindBy(xpath = "//h4[contains(text(),'Final PDF')]")
	private WebElement pdfHeading;
	
	@FindBy(xpath = "//div[@class='grid gap-1']/div[@class='text-sm font-semibold']")
	private WebElement message;
	
	@FindBy(xpath = "//h2[text()='Saved Invoices']//parent::div//following-sibling::div/div/div")
	private WebElement savedInvoice;
	
	@FindBy(xpath = "//section//div/h1")
	private WebElement senderNameFromPreview;
	
	@FindBy(xpath = "//small")
	private WebElement errorMessage;
	
	public String getAmount() {
		return amount.getText();
	}
	
	public String getBankName() {
		String bankName = paymentTerms.get(0).getText();
		return bankName.split(" ")[1];
	}
	
	public String getAccName() {
		String accName = paymentTerms.get(1).getText();
		return accName.split(": ")[1];
	}
	
	public String getAccNumber() {
		String accNumber = paymentTerms.get(2).getText();
		return accNumber.split(": ")[1];
	}
	
	public boolean verifySignature() {
		return isElementDisplayed(signature);
	}
	
	public void fillTheForm() {
		clickElement(fillFormBtn);
	}
	
	public void generatePDF() {
		clickElement(generatePDFBtn);
		waitForElementToBeVisible(pdfHeading);
	}
	
	public void saveInvoice() {
		clickElement(saveInvoice);
	}
	
	public void addNewInvoice() {
		clickElement(addNewInvoice);
		clickElement(createInvoice);
	}
	
	public void loadInvoice() {
		clickElement(loadInvoiceBtn);
		waitForElementToBeVisible(addJsonInvoiceFile);
	}
	
	public void deleteInvoiceFromLoadInvoice() {
		clickElement(deleteInvoice);
		clickElement(closeSignPopUp);
		driver.navigate().refresh();
	}
	
	public String getMessage() {
        waitForElementToBeAppear(message);
		return message.getText().trim();
	}
	
	public boolean verifySavedInvoiceInLoadInvoice() {
		return isElementDisplayed(savedInvoice);
	}
	
	public String getSendersNameFromPreview() {
		return senderNameFromPreview.getText();
	}
	
	public void loadInvoiceFromDevice() {
		loadInvoice();
		addJsonInvoiceFile.sendKeys(System.getProperty("user.dir")+prop.getProperty("invoiceSource"));
	}
	
	public void enterEmailAndSendMail() {
		clickElement(sendInvoice);
		setTextInInput(inputEmail, prop.getProperty("mail"));
		clickElement(sendPDF);
	}
	
	public void enterInvalidEmailAndSendMail() {
		clickElement(sendInvoice);
		setTextInInput(inputEmail, "test.com");
		clickElement(sendPDF);
	}
	
	public String verifyErrorMessageOfInvalidMail() {
		String meesage = errorMessage.getText();
		errorMessage.isDisplayed();
		clickElement(closeSignPopUp);
		return meesage;
	}
	
}
