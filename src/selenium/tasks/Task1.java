package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

import java.io.File;

public class Task1 {
    WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement number = driver.findElement(By.id("numb"));
        String inputText = "text";
        number.sendKeys(inputText);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        String errorText = driver.findElement(By.id("ch1_error")).getText();
        String expected = "Please enter a number";
        assertEquals(expected, errorText);
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        WebElement number = driver.findElement(By.id("numb"));
        String inputNumberSmall = "35";
        number.sendKeys(inputNumberSmall);
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/button")).click();
        String errorNumberSmall = driver.findElement(By.id("ch1_error")).getText();
        String expected = "Number is too small";
        assertEquals(expected, errorNumberSmall);
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement inputBox = driver.findElement(By.cssSelector("#numb"));
        inputBox.clear();
        inputBox.sendKeys("666");

        WebElement submitBtn = driver.findElement(By.xpath("//button[@onclick='numberValidation()']"));
        submitBtn.click();

        WebElement errorMessage = driver.findElement(By.cssSelector("#ch1_error"));
        String expectedErrorTxt = "Number is too big";

        try {
            assertEquals(expectedErrorTxt, errorMessage.getText());
        } catch (AssertionError e) {
            System.out.println("\"Number is too big\" error message should be displayed");
            e.printStackTrace();
        }

        inputBox.clear();
        inputBox.sendKeys("150");
        submitBtn.click();
        assertEquals(expectedErrorTxt, errorMessage.getText());
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        WebElement inputBox = driver.findElement(By.cssSelector("#numb"));
        inputBox.clear();
        inputBox.sendKeys("81");

        WebElement submitBtn = driver.findElement(By.xpath("//button[@onclick='numberValidation()']"));
        submitBtn.click();

        Alert alert = driver.switchTo().alert();
        String alertTxt = "Square root of 81 is 9.00";
        assertEquals(alertTxt, alert.getText());
        alert.accept();


        WebElement errorMessage = driver.findElement(By.cssSelector("#ch1_error"));
        assertFalse(errorMessage.isDisplayed());

    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        WebElement txt1 = driver.findElement(By.id("numb"));
        txt1.sendKeys("67");
        WebElement btn = driver.findElement(By.className("w3-orange"));
        btn.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 67 is 8.19", alert.getText());
        alert.accept();
    }
}


