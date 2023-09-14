import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import nespresso.ExcelReader;
import nespresso.Initializer;
import nespresso.LunchWebSite;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import pages.Cart;
import pages.CatalogCafe;
import pages.Login;
import org.testng.Assert;

@Listeners(CustomTestListener.class)
public class TestNespresso {
    private ChromeDriver driver;
    ExtentHtmlReporter htmlReporter;

    static ExtentReports extent;
    //helps to generate the logs in the test report.
    ExtentTest test;

    @BeforeTest
    void initExtentreport(){
        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/testReport.html");
        //initialize ExtentReports and attach the HtmlReporter
        extent=new ExtentReports();
        //configuration items to change the look and feel
        //add content, manage tests etc
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setDocumentTitle("Simple Automation Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }
    @BeforeMethod
    void preparerDriver() throws InterruptedException {
        Initializer initializer = new Initializer();
        driver = initializer.initDriver();
        LunchWebSite lunchWebSite = new LunchWebSite(driver);
        lunchWebSite.lunch(ExcelReader.readValueFromExcel(0));
    }


    @Test
    void TestLogIn() throws InterruptedException {
        Login log = new Login(driver);
        log.logIn();
    }

   @Test
    void testAddCafeToCart() throws InterruptedException {
       Login myLog=new Login(driver);
       myLog.logIn();
       CatalogCafe CatCafe=new CatalogCafe(driver);
       String ItemName=ExcelReader.readValueFromExcel(1);// Caramello nocciola
       CatCafe.AddCafeToCartfromExcel(ItemName);
    }

    @Test
    void testUpdateQuantity() throws InterruptedException {
        Login myLog=new Login(driver);
        myLog.logIn();
        Cart miniCart = new Cart(driver);
        //miniCart.UpdateProductQuantityy(ExcelReader.readValueFromExcel(1),"increase");
        Assert.assertEquals(miniCart.UpdateProductQuantity(ExcelReader.readValueFromExcel(1),"increase"),4);
    }
    @Test
    void testDeleteProduct() throws InterruptedException {
        Login myLog=new Login(driver);
        myLog.logIn();
        Cart miniCart= new Cart(driver);
        miniCart.DeleteProductFromCart(ExcelReader.readValueFromExcel(1));
    }

   @AfterMethod
    void quitDriver(){
        driver.quit();//quitter le driver
    }

    @AfterTest
    void endReport(){
        extent.flush(); //erase any previous data on the report and create a new report
    }













    @Test
    void testAdd2() throws InterruptedException {
        Login myLog=new Login(driver);
        myLog.logIn();
        CatalogCafe CatCafe=new CatalogCafe(driver);
        CatCafe.AddCafeToCart("nocciola",2);// nocciola voltesso caramello
    }
    @Test
    void testSiteWebLink(){
        ExcelReader appConstants = new ExcelReader();
        Assertions.assertNotNull(appConstants.readValueFromExcel(0));
        Assertions.assertEquals("https://ma.buynespresso.com/ma_fr/",ExcelReader.readValueFromExcel(0));
    }
    @Test
    public void TestProductName() throws InterruptedException {
        ExcelReader appConstants = new ExcelReader();
        Assertions.assertNotNull(appConstants.readValueFromExcel(1));
        Assertions.assertEquals("Freddo Intenso",appConstants.readValueFromExcel(1));
    }
    @Test
    public void TestProductQuantity() throws InterruptedException {
        int MyQuantity=ExcelReader.getProductQuantityFromExcel(2);
        //qtechoix.sendKeys("3");
        Assertions.assertEquals(3,MyQuantity);
    }
    @Test
    public void TestLog() throws InterruptedException {
        String mylog=ExcelReader.readValueFromExcel(3);
        Assertions.assertEquals("houssinemaimai@gmail.com",mylog);
        System.out.println(mylog);
    }
    @Test
    public void Testpwd() throws InterruptedException {
        String Pwd=ExcelReader.readValueFromExcel(4);
        Assertions.assertEquals("Nespresso3579/%/",Pwd);
    }










}
