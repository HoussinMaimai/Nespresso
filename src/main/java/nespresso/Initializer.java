package nespresso;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class Initializer {
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

}
