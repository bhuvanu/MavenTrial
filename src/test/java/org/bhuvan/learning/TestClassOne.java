package org.bhuvan.learning;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class TestClassOne extends BaseTest {
    public TestClassOne() throws FileNotFoundException {
        /* BaseTest base = new BaseTest(); */
    }

    @BeforeTest
    public void setup() throws InterruptedException {
        //test=extent.startTest("Testing Rightmove");
       /* extent.attachReporter(spark);
        test=extent.createTest("Start Testing No : ");*/
        //reportInfo("Launching the Browser");
        launchBrowser();
        Thread.sleep(3000);

    }

    @Test(priority = 1)
    public void actualTest() throws InterruptedException {
        /* start test */
        test=extent.startTest("Navigating to the Browser");
        System.out.println("Inside Actual test");
        reportInfo("Navigating to the Browser");
        navigateTo();
        Thread.sleep(3000);
        reportInfo("Entering the Searching Property Value");
        //searchProperty();

        enterValue("search_property_id");
        reportInfo("Click the sale property button");
        clickElement("sale_property_id");
        reportInfo("Checking Title property value");
        Assert.assertTrue(isElementPresent("title_property_xpath"));
        System.out.println("Test1 - Passed");
        extent.endTest(test);
       // return null;
    }

    @Test(priority = 2)
    public void findMyProperty(){
        test=extent.startTest("FindMyProperty");
        reportInfo("Inside FindMyProperty");
        //Select Radius
        selectDropDown("search_radius_id","Within 1 mile");
        reportInfo("Radius set successfully");
        //Select MIn price
        selectDropDown("price_min_id", "50,000");
        reportInfo("Price Min set successfully");
        //Select Max price
        selectDropDown("price_max_id","60,000");
        reportInfo("Price max set successfully");
        //Select Min Bedrooms
        selectDropDown("beds_min_id","1");
        reportInfo("Beds min set successfully");
        //Select Max Bedrooms
        selectDropDown("beds_max_id","3");
        reportInfo("Beds max set successfully");
        // Perform Click action on Find property button
        clickElement("find_property_button_id");
        reportInfo("Find Property Button clicked");
        Assert.assertTrue(isElementPresent("second_page_title_xpath"));
        System.out.println("Test2 - Passed");
        extent.endTest(test);
    }


    @AfterTest
    public void tearDown() {
        //reportInfo("Ending test Run");
        //extent.endTest(test);
        extent.flush();
        //reportInfo("Closing the browser");
        closeBrowser();
    }

}
