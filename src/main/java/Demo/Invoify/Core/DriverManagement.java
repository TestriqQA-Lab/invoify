package Demo.Invoify.Core;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class DriverManagement {

	protected WebDriver driver;
	private String path;
	private FileInputStream fis;
	private Properties properties;
	private String browser;
	private String url;

	@BeforeMethod
	public void launchApplication() throws IOException {
		setUp();
	}

	@AfterMethod
	public void quitDriver() {
		tearDown();
	}

	private void loadProperties() throws IOException {
		path = System.getProperty("user.dir");
		fis = new FileInputStream(path + "/src/main/java/Demo/Invoify/Resources/globalData.properties");
		properties = new Properties();
		properties.load(fis);
		browser = properties.getProperty("browser");
		url = properties.getProperty("url");
	}

	public void setUp() throws IOException {
		loadProperties();
		driver = initDriver(browser);
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
				chromeOptions.addArguments("--incognito");
				chromeOptions.addArguments("--headless");
				driver = new ChromeDriver(chromeOptions);
			} catch (Exception e) {
			}
			break;
		case "firefox":
			try {
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--incognito");
				firefoxOptions.addArguments("--headless");
				driver = new FirefoxDriver(firefoxOptions);
			} catch (Exception e) {
			}
			break;
		case "edge":
			try {
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--incognito");
				edgeOptions.addArguments("--headless");
				driver = new EdgeDriver(edgeOptions);
			} catch (Exception e) {
			}
			break;
		default:
		}
		return driver;
	}

	public String getScreenShot(String fileName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "/report/" + fileName + ".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir") + "/report/" + fileName + ".png";
	}

}
