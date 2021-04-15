package org.bhuvan.learning;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

public class BaseTest {
    /**
     * step 1: launch the browser
     * step 2: navigate to particular site and maximise the window
     * step 3: search sale property for specific postcode
     * step 4: close the browser
     */

   WebDriver driver ;
   Properties prop,prop1;
   FileInputStream fis, fis1;

   // For creating tests reports in a file
   public ExtentReports extent = ExtentReportsManager.getReport();
   /* public ExtentReports extent = new ExtentReports();
    public ExtentSparkReporter spark = new ExtentSparkReporter("C:\\bhu\\MavenTrial\\target\\extentReports\\Spark.html");
    */

   // For creating test logs results
   public ExtentTest test ;


   public BaseTest() throws FileNotFoundException {
       String path = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";
       String path1 = System.getProperty("user.dir") + "\\src\\test\\resources\\or.properties";
       System.out.println(path);
       prop = new Properties();
       prop1 = new Properties();
       fis = new FileInputStream(path);
       fis1 = new FileInputStream(path1);
       try {
           prop.load(fis);
           prop1.load(fis1);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }


 public void launchBrowser(){
       String BrowserType = prop.getProperty("browser");
       System.out.println(BrowserType);
       if (BrowserType.equalsIgnoreCase("Chrome")) {
           WebDriverManager.chromedriver().setup();
           driver = new ChromeDriver();
       }
       else if (BrowserType.equalsIgnoreCase("Firefox")){
           WebDriverManager.firefoxdriver().setup();
           driver = new FirefoxDriver();
       }
       else if (BrowserType.equalsIgnoreCase("null"))
           System.out.println("Detected Null Browser Type");
       else {
           System.out.println("Invalid Browser type");
       }
       driver.manage().window().maximize();
     System.out.println("I am out of launch Browser");
 }

 public void enterValue(final String key){

       identifyElement(key).sendKeys(prop.getProperty("postcode"));
 }

 public void clickElement(final String key){

       identifyElement(key).click();
 }

 // Parameterise Element locators
    public WebElement identifyElement(final String key){
       String elementValue = prop1.getProperty(key);
       WebElement element = null;

       if(key.endsWith("xpath")) {
        element = driver.findElement(By.xpath(elementValue));
       }
       else if(key.endsWith("id")) {
           element = driver.findElement(By.id(elementValue));
       }
       else if(key.endsWith("name")) {
           element = driver.findElement(By.name(elementValue));
       }
       else if(key.endsWith("css")) {
           element = driver.findElement(By.cssSelector(elementValue));
       }
       else if(key.endsWith("linkText")) {
           element = driver.findElement(By.linkText(elementValue));
       }
       else if (key.endsWith("partialLinkText")) {
           element = driver.findElement(By.partialLinkText(elementValue));
       }
       else if (key.endsWith("tagName")) {
           element = driver.findElement(By.tagName(elementValue));
       }
       else if (key.endsWith("className")) {
           element = driver.findElement(By.className(elementValue));
       }
       else {
           System.out.println("invalid Element locator");
       }

       return element;

       }


 public void navigateTo() throws InterruptedException
     {
     //driver.navigate().to("https://www.rightmove.co.uk");
         System.out.println("inside navigate to method");
     driver.navigate().to(prop.getProperty("appUrl"));
     //driver.get("https://www.rightmove.co.uk/");
     Thread.sleep(3000);
    // return null;
 }

    public void searchProperty() throws InterruptedException {
       //Enter the postcode to search
        System.out.println("inside search property");
        driver.findElement(By.id("search_property_id")).sendKeys(prop.getProperty("postcode"));
        System.out.println("typed postcode in search property");
        // Click the "Sale" button
        driver.findElement(By.id("sale_property_id")).click();
        Thread.sleep(5000);
    }

    public void selectDropDown(String key,String text) {
       Select select = new Select(identifyElement(key));
       select.selectByVisibleText(text);
    }
/*
    public void SearchMinRange(String minkey, String min) {
        Select select = new Select(identifyElement(minkey));
        select.selectByVisibleText(min);
    }

    public void SearchMaxRange(String maxkey, String max){
        Select select = new Select(identifyElement(maxkey));
        select.selectByVisibleText(max);
    }
    public void searchMinBedrooms(String minkey,String min){
        Select select = new Select(identifyElement(minkey));
        select.selectByVisibleText(min);
    }

    public void searchMaxBedrooms(String maxkey,String max){
        Select select = new Select(identifyElement(maxkey));
        select.selectByVisibleText(max);
    }
*/

 public void closeBrowser(){
    driver.quit();
 }

 // Assert Methods to compare actual with expected values
 public boolean isElementPresent(final String key)
 {
     try{
         identifyElement(key);
         return true;
     } catch (NoSuchElementException e){
         return false;
     }
 }

 // Report information
    public void reportInfo(String infoMessage){
            //test.log(Status.INFO, infoMessage);
             test.log(LogStatus.INFO, infoMessage);
    }

}
