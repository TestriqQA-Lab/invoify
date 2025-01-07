package Demo.Invoify.Utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class report {

	private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public static void init() {
		if (extentReports == null) {
			String reportPath = System.getProperty("user.dir") + "/report/index.html";
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(new File(reportPath));
			extentReports = new ExtentReports();
			sparkReporter.config().setTheme(Theme.STANDARD);
			sparkReporter.config().setDocumentTitle("Invoify Automation Test Report.");
			extentReports.attachReporter(sparkReporter);
			extentReports.setSystemInfo("OS", "Windows");
			extentReports.setSystemInfo("Author", "Testriq");
			extentReports.setSystemInfo("Browser", "Chrome");
			extentReports.setSystemInfo("Site tested", "http://localhost:3000/");
		}
	}

	public static void createTest(String testName, String testDescription) {
		ExtentTest test = extentReports.createTest(testName, testDescription).log(Status.INFO,
				"Test execution started!");
		extentTest.set(test);
	}

	public static ExtentTest getTest() {
		return extentTest.get();
	}

	public static void flush() {
		if (extentReports != null) {
			extentReports.flush();
		}
	}

}
