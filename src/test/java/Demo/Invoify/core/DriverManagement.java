package Demo.Invoify.core;

import java.io.FileInputStream;



import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Demo.Invoify.Utils.logs;
import Demo.Invoify.Utils.report;

public class DriverManagement {

	protected WebDriver driver;
	private String path;
	private FileInputStream fis;
	private Properties properties;
	private String browser;
	private String url;
	private Method method;
	
	@BeforeSuite
	public void startingTest() {
		logs.logInfoMessage("[ Starting Test Execution ]", false, null);
		report.init();
	}
	
	@AfterSuite
	public void endTest() {
		logs.logInfoMessage("[ Ending Test Execution ]", false, null);
		report.flush();
	}

	@BeforeMethod
	public void setMethod(Method method) {
		this.method = method;
		report.createTest(method.getName(), method.getAnnotation(Test.class).description());
	}
	
	@BeforeClass
	public void initiateDriver() throws IOException {
		setUp();
	}

	@AfterClass
	public void quitDriver() {
		tearDown();
	}

	public void waitForPresenceOfElement(WebElement element) {
		waitForElementToBeVisible(element);
	}

	private void loadProperties() throws IOException {
		path = System.getProperty("user.dir");
		fis = new FileInputStream(path + "/src/main/java/Demo/Invoify/Resources/variable.properties");
		properties = new Properties();
		properties.load(fis);
		browser = properties.getProperty("browser");
		url = properties.getProperty("url");
	}

	public void setUp() throws IOException {
		logs.logInfoMessage("Starting session...", false, null);
		loadProperties();
		driver = initDriver(browser);
		logs.logInfoMessage("Session started: ", false, null);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	public void tearDown() {
		driver.quit();
	}

	private WebDriver initDriver(String browser) {
		WebDriver driver = null;
		switch (browser) {
		case "chrome":
			try {
				ChromeOptions chromeOptions = new ChromeOptions();
				driver = new ChromeDriver(chromeOptions);
			} catch (Exception e) {
			}
			break;
		case "firefox":
			try {
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				driver = new FirefoxDriver(firefoxOptions);
			} catch (Exception e) {
			}
			break;
		case "edge":
			try {
				EdgeOptions edgeOptions = new EdgeOptions();
				driver = new EdgeDriver(edgeOptions);
			} catch (Exception e) {
			}
			break;
		default:
		}
		return driver;
	}

	public void waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
