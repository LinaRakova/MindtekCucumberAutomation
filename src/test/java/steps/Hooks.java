package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    WebDriver driver = Driver.getDriver();

    @Before
    public void setUp(Scenario scenario){
        System.out.println(scenario.getSourceTagNames());
        if(!scenario.getSourceTagNames().contains("@api")) {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().deleteAllCookies();
        }
    }

    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
        if(!scenario.getSourceTagNames().contains("@api")){
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.getId();
        }
        Thread.sleep(2000);
        driver.quit();
    }
}

