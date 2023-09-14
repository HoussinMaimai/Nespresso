package nespresso;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import com.aventstack.extentreports.reporter.configuration.Theme;


import java.util.Collections;


public class LunchWebSite {

     WebDriver driver;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public LunchWebSite(ChromeDriver driver) {


        this.driver = driver;//new ChromeDriver(options);
    }
    public static ChromeDriver initDriver() {
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\ehmaimai\\IdeaProjects\\ParuVendu\\src\\main\\resources\\chromedriver.exe");
        //return new ChromeDriver();
        /*String projectPath = System.getProperty("user.dir");
        String driverPath = projectPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", driverPath);
        return new ChromeDriver();*/

        Optional<Path> driverPath = findChromeDriverPath();

        if (driverPath.isPresent()) {
            System.setProperty("webdriver.chrome.driver", driverPath.get().toString());
            //return new ChromeDriver();
            ChromeOptions options = new ChromeOptions();// pour iviter le probleme de accers denied

            options.addArguments("--disable-blink-features=AutomationControlled");

            return new ChromeDriver(options);
        } else {
            throw new RuntimeException("Impossible de trouver le fichier chromedriver.exe.");
        }
    }

    private static Optional<Path> findChromeDriverPath() {
        String projectPath = System.getProperty("user.dir");
        Path rootPath = Paths.get(projectPath);//acceder au chemin du projet courant

        try {
            return Files.walk(rootPath)
                    .filter(path -> path.toFile().getName().equals("chromedriver.exe"))
                    .findFirst();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public void lunch(String url) throws InterruptedException {
        //driver.get("https://ma.buynespresso.com/ma_fr/\"");//accéder au site
        driver.get(url);
    }

}
