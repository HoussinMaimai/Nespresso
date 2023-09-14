package pages;
import nespresso.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Login {
    private ChromeDriver driver;
    WebDriverWait wait;
    @FindBy(id="header-login-link")
    private WebElement logIn;

    @FindBy(id="email")
    private WebElement email;
    @FindBy(id="pass")
    private WebElement pwd;
    @FindBy(id="send2")
    private WebElement Connect;

    ///////////////////////////////////////////////////////////////////////*************************************************
    public Login(ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    /*public void loginAcces(){// si on veux utiliser deixieme lien
        clickHere.click();
        moroccoOptionFrensh.click();

    }*/

        public void logIn () throws InterruptedException {
            driver.manage().window().maximize();

            /*Actions actions = new Actions(driver);
            actions.moveToElement(machine).perform();*/

            //Thread.sleep(3000);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            logIn.click();
            //wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).sendKeys(ExcelReader.readValueFromExcel(3));
            email.sendKeys(ExcelReader.readValueFromExcel(3));
            pwd.sendKeys(ExcelReader.readValueFromExcel(4));
            Connect.click();
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        }
    }

