package nespresso;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class VerfierPage {
    private ChromeDriver driver;
    public VerfierPage(ChromeDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    public void pageVerification(String verificator){
        String codesource = driver.getPageSource();
        //String valeurAttendue = "Vendez plus vite !";
        if(codesource.contains(verificator)) {
            System.out.println("C'est OK ! \n");
        }else {
            System.out.println("c'est KO ? \n");
        }
    }

}
