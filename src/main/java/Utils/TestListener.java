package Utils;

import Core.TestBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        if (TestBase.getTest() != null) {
            TestBase.getTest().info("Test Started: " + result.getName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	try {
            // Run all soft asserts here
            TestBase.sAssert.assertAll();
            if (TestBase.getTest() != null) {
                TestBase.getTest().pass("Test Passed: " + result.getName());
            }
        } catch (AssertionError e) {
            // If soft asserts failed, mark as failure
            if (TestBase.getTest() != null) {
                TestBase.getTest().fail("Assertion Failure in " + result.getName());
                TestBase.getTest().fail(e);
            }
            result.setStatus(ITestResult.FAILURE); // force TestNG to fail it
        } finally {
            TestBase.sAssert.reset();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	if (TestBase.getTest() != null) {
            TestBase.getTest().fail("Test Failed: " + result.getName());
            if (result.getThrowable() != null) {
                TestBase.getTest().fail(result.getThrowable());
            }
        }
        TestBase.sAssert.reset();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	if (TestBase.getTest() != null) {
            TestBase.getTest().skip("Test Skipped: " + result.getName());
        }
        TestBase.sAssert.reset();
    }

    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
}
