package pages;

import nespresso.ExcelReader;
import nespresso.HandleError;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CatalogCafe {
    private ChromeDriver driver;
    WebDriverWait wait;
    //private final HandleError handleError;
    @FindBy(xpath = "//*[@id='_evidon-accept-button']")
    private WebElement AccepteCookies;

    //*********************************
    @FindBy(css = "label.label[data-role='minisearch-label'] span")
    private WebElement buttonSearch;
    @FindBy(id = "search")
    private WebElement fieldSearsh;
    @FindBy(css="a.search.action.tocart.primary")
    private WebElement detailsProduct;
    @FindBy(css = "button.action.tocart.primary.list.pdpcart-btn")
    private WebElement addPanier;
    @FindBy(css = "button.action.tocart.primary.list.pdpcart-btn")
    private WebElement addToCartAfterSearch;
    @FindBy(css="span.qty-item-btn[data-qtyitem='0']")
    private WebElement quantity;
    @FindBy(css = "input[name='qty']")
    WebElement qtechoix;
    @FindBy(id = "product-addtocart-button")
    WebElement buttonOK;
    @FindBy(css = "span.text[data-mobile-text='Cart']")
    private WebElement ClickPanier;



    //*********************************

    /*public CatalogCafe(ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }*/
    //Actions actions = new Actions(driver);
    public CatalogCafe(ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(driver, this);
        wait=new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    public  void AddCafeToCartfromExcel(String itemName) throws InterruptedException {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//
        buttonSearch.click();
        fieldSearsh.sendKeys(itemName);
        fieldSearsh.sendKeys(Keys.ENTER);
        detailsProduct.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Duration timeout = Duration.ofSeconds(10); // Temps d'attente de 10 secondes
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"_evidon-accept-button\"]"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        addPanier.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        int MyQuantity=ExcelReader.getProductQuantityFromExcel(2);
        qtechoix.sendKeys(String.valueOf(MyQuantity));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         buttonOK.click();
    }
    public void AddCafeToCart(String productName,int quantityValue){
        wait.until(ExpectedConditions.visibilityOf(buttonSearch)).click();
        fieldSearsh.sendKeys(productName);
        fieldSearsh.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(detailsProduct)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"_evidon-accept-button\"]"))).click();
        wait.until(ExpectedConditions.visibilityOf(addToCartAfterSearch)).click();
        String quantitySelector="span.qty-item-btn[data-qtyitem='"+quantityValue+"']";
        quantity=driver.findElement(By.cssSelector(quantitySelector));
        wait.until(ExpectedConditions.visibilityOf(quantity)).click();
    }
    //update product quantity



}
