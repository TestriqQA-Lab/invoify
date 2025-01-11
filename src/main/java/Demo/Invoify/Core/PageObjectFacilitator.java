package Demo.Invoify.Core;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectFacilitator {
	
	WebDriver driver;
	Properties prop;
	
	public PageObjectFacilitator(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Properties loadData() throws IOException {
		String path = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(path + "/src/main/java/Demo/Invoify/Resources/data.properties");
		prop = new Properties();
		prop.load(fis);
		return prop;
	}
	
	public void waitForElementToBeAppear(WebElement element) {
		FluentWait<WebDriver> fluentWait;
		fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToBeInvisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public boolean isElementDisplayed(WebElement element) {
		boolean flag = false;
	    try {
	        if (!element.isDisplayed()) {
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
