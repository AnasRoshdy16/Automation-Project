package Listeners;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokedMethodListenerClass implements IInvokedMethodListener {
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {

    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if (testResult.getStatus()==ITestResult.FAILURE){
                LogsUtils.info("Test Case " + testResult.getName() + " failed");

                try
                {
                    Utility.takeScreenShot(getDriver(),testResult.getName()); //validLoginTC-2024-03-03-8-17pm

                }
                catch (Exception e) {
                    LogsUtils.error(e.getMessage());
                }
        }
    }
}
