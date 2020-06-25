package testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class TestCase1 {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReport;
    public ExtentTest test;

    @BeforeTest
    public void setReport(){
        htmlReporter = new ExtentHtmlReporter("./reports/extentReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("W2A Automation Reports");
        htmlReporter.config().setReportName("Automation Test Results");
        //htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTheme(Theme.STANDARD);

        extentReport = new ExtentReports();
        extentReport.attachReporter(htmlReporter);

        extentReport.setSystemInfo("Automation Tester", "Maria Ivanova");
        extentReport.setSystemInfo("Organization", "Gamma Pro");
        extentReport.setSystemInfo("Build Number", "173");
    }

    @AfterTest
    public void endReport(){
        extentReport.flush();
    }

    /***Pass, Fail, Skip*/


    @Test
    public void doLogin(){

        test = extentReport.createTest("Login Test");
        System.out.println("Executing Login Test");

    }

    @Test
    public void doUserReg(){

        test = extentReport.createTest("User Registration Test");
        System.out.println("Executing User Registration Test");
        Assert.fail("User Registration Test Failed");
    }

    @Test
    public void doLogout(){

        test = extentReport.createTest("Logout Test");
        System.out.println("Executing Logout Test");
        throw  new SkipException("Skipped Logout Test");
    }

    @AfterMethod
    public void TearDown(ITestResult result){

        if(result.getStatus() == ITestResult.FAILURE){

            String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());
            test.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
                    + "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
                    + " \n");

//
//			try {
//
//				ExtentManager.captureScreenshot();
//				testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
//						MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
//								.build());
//			} catch (IOException e) {
//
//			}

            String methodName = result.getMethod().getMethodName();
            String logText = "<b>" + "TEST CASE: - " + methodName.toUpperCase() + " --- FAILED" + "</b>";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
            test.fail(m);

        } else if (result.getStatus() == ITestResult.SKIP){

            String methodName = result.getMethod().getMethodName();
            String logText = "<b>" + "TEST CASE: - " + methodName.toUpperCase() + " --- SKIPPED" + "</b>";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
            test.skip(m);

        } else if (result.getStatus() == ITestResult.SUCCESS){

            String methodName = result.getMethod().getMethodName();
            String logText = "<b>" + "TEST CASE: - " + methodName.toUpperCase() + " --- PASSED" + "</b>";
            Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            test.pass(m);
        }
    }

}
