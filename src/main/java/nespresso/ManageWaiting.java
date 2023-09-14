package nespresso;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ManageWaiting {
    static WebDriver driver;

    public void findElementWithImplicitWait(Duration timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.MILLISECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#accueil > a"))).click();

    }

    public void waitUntilElementIsClickable(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (StaleElementReferenceException e) {
            System.out.println("Element Not Attached to the DOM catched "+e);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    public void WaitingForSeconds(int dureeWaiting) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(dureeWaiting, TimeUnit.MILLISECONDS);
    }
    /*public ManageWaiting(ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
      }*/
    }
