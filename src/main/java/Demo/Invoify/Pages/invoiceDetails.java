package Demo.Invoify.Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class invoiceDetails {

	WebDriver driver;
	Properties prop;

	public invoiceDetails(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		String path = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(path + "/src/main/java/Demo/Invoify/Resources/data.properties");
		prop = new Properties();
		prop.load(fis);
	}

	@FindBy(css = "input[id='details.invoiceLogo']")
	private WebElement imageInput;

	@FindBy(css = "input[name='details.invoiceNumber']")
	private WebElement invoiceNoInput;

	@FindBy(css = "button[id=':ri:-form-item']")
	private WebElement issueDate;

	@FindBy(css = "button[id=':rk:-form-item']")
	private WebElement dueDate;

	@FindBy(css = "div[data-state='open'] button[aria-controls*='radix']")
	private List<WebElement> monthAndYear;

	@FindBy(css = "select[name='details.currency']")
	private WebElement selectCurrency;
	
	@FindBy(xpath = "//p/parent::div//button")
	private List<WebElement> templates;
	
	@FindBy(xpath = "//button[text()='Remove logo']")
	private WebElement removeImage;
	
	@FindBy(xpath = "//section/h4")
	private WebElement tabTitle;
	
	@FindBy(xpath = "//h4[contains(text(),'Live Preview')]//parent::div//img")
	private WebElement logoOnPreview;
	
	@FindBy(xpath = "//div[@class='space-y-2']//button[@role='combobox']/span")
	private WebElement selectedCurrency;

	public void selectCurrency() {
		Select select = new Select(selectCurrency);
		select.selectByValue(prop.getProperty("currency"));
	}

	public void inputInvoiceNumber() {
		invoiceNoInput.sendKeys(prop.getProperty("invoice_number"));
	}

	public void addImage() {
		imageInput.sendKeys(System.getProperty("user.dir") + prop.getProperty("imgSource"));
	}
	
	public void enterIssueDate() {
		issueDate.click();
		selectMonth(prop.getProperty("issueMonth"));
		selectYear(prop.getProperty("issueYear"));
		selectDate(prop.getProperty("issueDate"));
	}
	
	public void enterDueDate() {
		dueDate.click();
		selectMonth(prop.getProperty("dueMonth"));
		selectYear(prop.getProperty("dueYear"));
		selectDate(prop.getProperty("dueDate"));
	}
	
	private void selectDate(String date) {
		WebElement dayOption = driver.findElement(By.xpath("//button[@name='day' and text()='"+date+"']"));
		dayOption.click();
	}
	
	private void selectYear(String year) {
		monthAndYear.get(1).click();
		WebElement yearOption = driver.findElement(By.xpath("//div[@role='option' and span[text()='"+year+"']]"));
		yearOption.click();
	}

	private void selectMonth(String month) {
		monthAndYear.get(0).click();
		WebElement monthOption = driver.findElement(By.xpath("//div[@role='option' and span[text()='"+month+"']]"));
		monthOption.click();
	}
	
	public void selectTemplate(String template) {
		switch(template) {
		case "template 1":
			templates.get(0).click();
		case "template 2":
			templates.get(1).click();
		}
	}
	
	public void removeLogo() {
		removeImage.click();
	}
	
	public String getTitleName() {
		return tabTitle.getText();
	}
	
	public String getCurrency() {
		return selectedCurrency.getText();
	}
	
	public Boolean verifyLogoIsDisplayedInPreview() {
		boolean flag = false;
	    try {
	        if (!logoOnPreview.isDisplayed()) {
	            flag = false;
	        } else {
	            flag = true; 
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return flag;
		
	}
	
}
