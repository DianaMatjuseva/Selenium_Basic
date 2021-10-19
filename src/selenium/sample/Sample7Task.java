package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class Sample7Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
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
    public void selectCheckBox() throws Exception {
//         TODO:
        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
//        check that none of the checkboxes are ticked
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }
//        tick  "Option 2"
        WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='checkbox']"));
        option2.click();
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='checkbox']"));
        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
        assertFalse(option1.isSelected());
        assertFalse(option3.isSelected());
//        tick  "Option 3"
        option3.click();
//        click result
        driver.findElement(By.id("result_button_checkbox")).click();
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
    }
    String actual = driver.findElement(By.id("result_checkbox")).getText();
    String expected = "You selected value(s): Option 2, Option 3";
 //   assertEquals (expected, actual);
    //   }





    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
        List<WebElement> radioButtons = driver.findElements(By.cssSelector(".w3-validate[type='radio']"));
//        check that none of the radio are selected
        for (WebElement radioButton : radioButtons) {
            assertFalse(radioButton.isSelected());
//        select  "Option 3"
            WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='radio'"));
            option3.click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
            WebElement option1 = driver.findElement(By.cssSelector(".w3-check[value='Option 1'][type='radio'"));
            WebElement option2 = driver.findElement(By.cssSelector(".w3-check[value='Option 2'][type='radio'"));
            assertFalse(option1.isSelected());
            assertFalse(option2.isSelected());
            assertTrue(option3.isSelected());
//        select  "Option 1"
            option1.click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
            assertFalse(option2.isSelected());
            assertFalse(option3.isSelected());

//        click result
            driver.findElement(By.id("result_button_ratio")).click();
//        check that 'You selected option: Option 1' text is being displayed
            String actual = driver.findElement(By.id("result_radio")).getText();
            String expected = "You selected option: Option 1";
            assertEquals (expected, actual);
    }

//    @Test
  //  public void selectOption() throws Exception {
//        select "Option 3" in Select
            Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
            dropdown.selectByIndex(3);
//        check that selected option is "Option 3"
            assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());
//        select "Option 2" in Select
            dropdown.selectByVisibleText("Option 2");
//        check that selected option is "Option 2"
            assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());
//        click result
            driver.findElement(By.id("result_button_select")).click();
//        check that 'You selected option: Option 2' text is being displayed
            String actual = driver.findElement(By.id("result_select")).getText();
            String expected = "You selected option: Option 2";
            assertEquals (expected, actual);
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
        String dateToEnter = "09/02/1959";
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.sendKeys(dateToEnter);
//        check that correct date is added
        assertEquals(dateToEnter, dateBox.getAttribute("value"));
    }
}
