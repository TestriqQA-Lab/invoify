package Demo.Invoify.Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class paymentInfoPage {

	WebDriver driver;
	Properties prop;

	public paymentInfoPage(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		String path = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(path + "/src/main/java/Demo/Invoify/Resources/data.properties");
		prop = new Properties();
		prop.load(fis);
	}

	@FindBy(xpath = "//input[@placeholder='Bank Name']")
	private WebElement inputBankName;

	@FindBy(xpath = "//input[@placeholder='Account Name']")
	private WebElement inputAccName;

	@FindBy(xpath = "//input[@placeholder='Account Number']")
	private WebElement inputAccNumber;

	public void enterBankDetails() {
		inputBankName.sendKeys(prop.getProperty("bank_name"));
		inputAccName.sendKeys(prop.getProperty("account_name"));
		inputAccNumber.sendKeys(prop.getProperty("account_number"));
	}

	public Boolean verifyAccountDetails(String bankName, String accName, String accNumber) {
		return bankName.equals(prop.getProperty("bank_name")) && accName.equals(prop.getProperty("account_name"))
				&& accNumber.equals(prop.getProperty("account_number"));
	}

}
