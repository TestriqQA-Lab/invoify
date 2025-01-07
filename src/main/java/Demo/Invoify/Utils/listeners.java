package Demo.Invoify.Utils;

import org.testng.ITestContext;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class listeners implements ITestListener {
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		logs.logInfoMessage("Currently executing test case : " + getTestCaseName(result) + "\n" + "Test case description : " + getTestCaseDescription(result), false, null);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		logs.logInfoMessage("Test passed! Test case : " + getTestCaseName(result), true, Status.PASS);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		logs.logInfoMessage("Test failed! Test case : " + getTestCaseName(result), true, Status.FAIL);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		logs.logInfoMessage("Test skipped! Test case : " + getTestCaseName(result), true, Status.SKIP);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		logs.logInfoMessage("Test passed with certain exceptions! Test case : " + getTestCaseName(result), true, Status.FAIL);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		logs.logInfoMessage("Test suite instantiated : " + context.getName(), false, null);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		logs.logInfoMessage("Test suite completed : " + context.getName(), false, null);
	}
	
	private static String getTestCaseName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    private static String getTestCaseDescription(ITestResult result) {
        return result.getMethod().getDescription();
    }

}
