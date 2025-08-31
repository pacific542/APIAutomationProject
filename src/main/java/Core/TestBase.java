package Core;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utils.ConfigReader;
import Utils.SoftAssertUtil;
import Utils.TimeStamp;
import io.restassured.RestAssured;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

public  class TestBase  {
	

    protected static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    public static  SoftAssertUtil sAssert;
    public static HashMap<String, String> header_map;
    public static HashMap<String, String> form_map;
    public static long response_time;

    public static ExtentTest getTest() {
        return testThread.get();
    }

    @BeforeSuite(alwaysRun = true)
    public void setupExtentReport() {
    	String suiteName = "Regression Suite";   // Gets <suite name="Suite"> from testng.xml
        String timestamp = TimeStamp.getTimeStamp();
        String reportPath = System.getProperty("user.dir") + "/reports/" + suiteName + "_" + timestamp + ".html";
        
    	
    	RestAssured.baseURI=ConfigReader.getPropertyValue("petswagger_url");
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        try {
			spark.loadXMLConfig("Resource/Config/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Project", "API Automation");
        extent.setSystemInfo("Tester", "Prashant");
    }
   

    @BeforeMethod(alwaysRun = true)
    public void setupExtentTest(Method method) {
        ExtentTest test = extent.createTest(method.getName());
        testThread.set(test);
         sAssert=SoftAssertUtil.getInstance();
         header_map = new HashMap<>();
         form_map=new HashMap< >();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownExtentTest() {
        testThread.remove();
        try {
        	sAssert.assertAll();
        } catch (AssertionError e) {
            e.printStackTrace(); // print detailed stack trace
            throw e; // rethrow so TestNG marks it as failed
        }
        sAssert.reset();
        header_map.clear();
        form_map.clear();
        response_time=0;
    }

    @AfterSuite(alwaysRun = true)
    public void flushExtentReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
