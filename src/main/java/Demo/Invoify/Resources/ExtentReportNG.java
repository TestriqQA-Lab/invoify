package Demo.Invoify.Resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportNG  {

	private static ExtentReports extentReports;

	public ExtentReports init() {
		String reportPath = System.getProperty("user.dir") + "/report/index.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(new File(reportPath));
		extentReports = new ExtentReports();
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setDocumentTitle("Invoify Automation Test Report.");
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("OS", "Mac");
		extentReports.setSystemInfo("Author", "Testriq");
		return extentReports;
	}

}
