package pages;

import nespresso.ExcelReader;
import nespresso.HandleError;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Cart {
    private  ChromeDriver driver;
    WebDriverWait wait;
    @FindBy(css = ".item-qty.qty.cart-item-qty")
    private WebElement QteChek;
    // Delete
   // @FindBy(css = "a.action.delete[data-cart-item='1100417'][title='Remove item']")
    @FindBy(xpath = "//button[@class='action-primary action-accept']/span[text()='OK']")
    private WebElement deleteConfirmationButton;
    @FindBy(css="div.showcart-wrapper.hasitem")  //div.showcart-wrapper[data-bind*='hasitem ///@FindBy(css="div.showcart-wrapper[data-bind*='hasitem']")
    private WebElement cart;
    @FindBy(css = "span[data-bind='i18n: \"View Shopping Bag\"']")
    private WebElement cartDetails;


    public Cart(ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        //wait.until(ExpectedConditions.visibilityOf(closeCookies)).click();
    }
    //add delete and update
    public  int getQuantityfromCart() {
        HandleError wait1 = new HandleError(driver);
        wait1.waitUntilElementIsClickable(cart, Duration.ofSeconds(5));// att pour que l'element soit cliquable
        cart.click();
        String QteSaisie = QteChek.getAttribute("value");
        int MyQte = Integer.parseInt(QteSaisie);
        if (MyQte==3){
            System.out.println("OK");
        }else
            System.out.println("KO");
        return MyQte;

    }
    public void UpdateProductQuantityy(String productName,String ActionToDo) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"_evidon-accept-button\"]"))).click();
        // WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30)); // Augmentez le délai à 30 secondes si nécessaire
        //wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.showcart-wrapper[data-bind*='hasitem']"))).click();
        wait.until(ExpectedConditions.visibilityOf(cart)).click();
        //wait.until(ExpectedConditions.visibilityOf(cartDetails)).click();
        WebElement getProductElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("strong.product-item-name a[href*='"+productName+"']")));//add condition
        WebElement getProductParentElement = getProductElement.findElement(By.xpath("ancestor::li[contains(@class, 'item product product-item')]"));//ancestor or parent
        if (ActionToDo.equals("increase")) {
            getProductParentElement.findElement(By.cssSelector("span.qty-increase")).click();
        } else if (ActionToDo.equals("decrease")) {
            getProductParentElement.findElement(By.cssSelector("span.qty-decrease")).click();
        }
        else {
            throw new IllegalArgumentException("Give add or reduce on argument!");
        }
        WebElement update = getProductParentElement.findElement(By.cssSelector("span[data-bind=\"i18n: 'Update'\"]"));
        //WebElement update = getProductParentElement.findElement(By.cssSelector("button.action.update"));
        //wait.until(ExpectedConditions.visibilityOf(update)).click();

        //String productQuantity=getProductParentElement.findElement(By.cssSelector("input.item-qty.qty.cart-item-qty")).getAttribute("value");
        //System.out.println(productQuantity);*/

    }
    public int UpdateProductQuantity(String productName,String ActionToDo) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"_evidon-accept-button\"]"))).click();
       // WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30)); // Augmentez le délai à 30 secondes si nécessaire
        //wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.showcart-wrapper[data-bind*='hasitem']"))).click();
        wait.until(ExpectedConditions.visibilityOf(cart)).click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.showcart-wrapper.hasitem"))).click();

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //cart.click();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement getProductElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("strong.product-item-name a[href*='"+productName+"']")));//add condition
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Find the parent element of the product
        WebElement getProductParentElement = getProductElement.findElement(By.xpath("ancestor::li[contains(@class, 'item product product-item')]"));//ancestor or parent
        //System.out.println("parent :" + productParentElement.getTagName());
         if (ActionToDo.equals("increase")) {
            getProductParentElement.findElement(By.cssSelector("span.qty-increase")).click();
        } else if (ActionToDo.equals("decrease")) {
            getProductParentElement.findElement(By.cssSelector("span.qty-decrease")).click();
           // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //Duration timeout = Duration.ofSeconds(10); // Temps d'attente de 10 secondes
             }
         else {
            throw new IllegalArgumentException("Give add or reduce on argument!");
        }
        WebElement update = getProductParentElement.findElement(By.cssSelector("span[data-bind=\"i18n: 'Update'\"]"));
        //WebElement update = getProductParentElement.findElement(By.cssSelector("button.action.update"));
        wait.until(ExpectedConditions.visibilityOf(update)).click();

        String productQuantity=getProductParentElement.findElement(By.cssSelector("input.item-qty.qty.cart-item-qty")).getAttribute("value");
        System.out.println(productQuantity);
        return Integer.parseInt(productQuantity);
       // return 3;
    }

    public void DeleteProductFromCart(String productName){
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"_evidon-accept-button\"]"))).click();
        //WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(50)); // Augmentez le délai à 30 secondes si nécessaire
        //wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.showcart-wrapper[data-bind*='hasitem']"))).click();
        //wait.until(ExpectedConditions.visibilityOf(cart)).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.showcart-wrapper.hasitem"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.showcart-wrapper.hasitem"))).click();

        //cart.click();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement getProductElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("strong.product-item-name a[href*='"+productName+"']")));
        WebElement getProductParentElement = getProductElement.findElement(By.xpath("ancestor::li[contains(@class, 'item product product-item')]"));
        System.out.println("eee"+getProductParentElement);
        WebElement delete = wait.until(ExpectedConditions.elementToBeClickable(getProductParentElement.findElement(By.cssSelector("a.action.delete"))));
        wait.until(ExpectedConditions.visibilityOf(delete)).click();
        wait.until(ExpectedConditions.visibilityOf(deleteConfirmationButton)).click();
    }

}
