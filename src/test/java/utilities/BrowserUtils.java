package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BrowserUtils {

    /**
     * This method generate random emails
     * Ex.: .getRandomEmail(); ->returns userdjkanfc332@gmail.com
     */
    public static String getRandomEmail(){
        UUID uuid = UUID.randomUUID();
        return "user" + uuid + "@gmail.com";
    }

    /**
     * This method takes screenshots.
     * Ex.:
     *     .takeScreenshot("Name of screenshot");
     */

    public static void takeScreenshot(String testName) throws IOException {
        WebDriver driver = Driver.getDriver();
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path = "src/test/resources/screenshots/"+testName+".png";
        File file = new File(path);
        FileUtils.copyFile(screenshot,file);
    }


    // it's needed to be moved to another class
    public static Set<String> getTextFromListWebElements(List<WebElement> list, int numberOfLines, int infoLineNum) {

        Set<String> setInfoFromWebElements = new HashSet<>();

        for (WebElement web : list) {
            String s = web.getText();
            String[] a = s.split("\n", numberOfLines);     //String[] result = yourString.split("\\R", 2);
            setInfoFromWebElements.add(a[infoLineNum-1]);
        }
        return setInfoFromWebElements;
    }

    /**
     * This method accept a WebElement and a String value to select
     * an option from a dropdown by value.
     * Example:
     *          .selectDropDownByValue(WebElement dropDown, String value);
     */


    public static void selectDropDownByValue(WebElement dropDown, String value){
        Select select = new Select(dropDown);
        select.selectByValue(value);
    }

    /**
     * This method will create a WebDriverWait object and for certain text
     * to be present in a WebElement
     * Ex:
     *      .waitForTextToBePresentInElement(WebElement element, String expectedText);
     */
    public static void waitForTextTobePresentInElement(WebElement element, String expectedText){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    /**
     * This method will create a WebDriverWait object and for until certain
     *  WebElement becomes to be clickable
     * @param element
     */
    public static void waitForElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /** This method will create a JavascriptExecutor and
     * scroll page until the WebElement will become visible
     */
    public static void scrollDown(WebElement webElement){
        JavascriptExecutor jse =  ((JavascriptExecutor) Driver.getDriver());
        jse.executeScript("arguments[0].scrollIntoView(true);", webElement);

    }

    public static void waitSeconds(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
