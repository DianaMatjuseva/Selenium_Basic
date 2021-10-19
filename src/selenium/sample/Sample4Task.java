package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.*;

public class Sample4Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + "\\lib\\";
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver.exe");
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void enterNumber() throws Exception {
//         TODO:
        WebElement numberInput = driver.findElement(By.id("number"));
        WebElement resultButton = driver.findElement(By.id("result_button_number"));
        WebElement clearButton = driver.findElement(By.id("clear_result_button_number"));
        WebElement resultText = driver.findElement(By.id("result_number"));
        String newText = "123";
//        enter a number under "Number"
        numberInput.clear();
        assertEquals(numberInput.getText(), "");
        assertEquals(numberInput.getAttribute("value"), "");

        numberInput.sendKeys(newText);

        //        check that button is not clickable "Clear Result"
        //        check that text is not displayed
        assertFalse(clearButton.isEnabled());

        //        check that text is not displayed below
        assertFalse(resultText.isDisplayed());

        //        click on "Result" button
        resultButton.click();

        //        check that text is displayed
        assertTrue(resultButton.isDisplayed());

        //        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        assertTrue(resultText.getText().equals("You entered number: \"" + newText + "\""));

        //        check that the button "Clear Result" is clickable now
        assertTrue(clearButton.isEnabled());

        //        click on "Clear Result"
        //        check that the text is still (""), but it is not displayed
        clearButton.click();

        //        check that the text below is equal to what it was, but it is not displayed
        assertFalse(resultText.isDisplayed());
        assertTrue(resultText.getText().equals(""));
    }


    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
        assertEquals(driver.getCurrentUrl(), base_url);

        //        click on "This is a link to Homepage"
        driver.findElement(By.id("homepage_link")).click();
        //
        //        check that current url is not base_url
        assertFalse(driver.getCurrentUrl().equals(base_url));

        //        verify that current url is homepage
        assertTrue(driver.getCurrentUrl().equals("https://kristinek.github.io/site/"));
    }
}
