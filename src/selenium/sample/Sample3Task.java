package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class Sample3Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
//    @Test ////FAILED!
    public void assertEqualsTask() throws Exception {
        System.out.println("---------1---------");
//         TODO:
//         check how many element with class "test" there are on page (5)
           int expectedSize = 5;
        System.out.println(driver.findElements(By.className("test")).size());

//         check that value of second button is "This is also a button"
        String expectedValue = "This is also a button";
        assertEquals(expectedSize, driver.findElements(By.className("test")).size());
        assertEquals(expectedValue, driver.findElement(By.name("randomButton2")).getAttribute("value"));

    }

    @Test
   //ADD a custom message
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:
        try {
            String button2Name = driver.findElement(By.id("buttonId")).getAttribute("value");
//            System.out.println(button2Name);
            assertTrue(button2Name.equalsIgnoreCase("this is Also a Button2"));
        } catch (AssertionError e) {
            System.err.println("FAIL!!!!!!!!! ");
            e.printStackTrace();
        }

//        @Test
//        public void assertFalseTask () throws Exception {
//            //      TODO:
//            //     check that it is False that value of second button is "This is a button"
//            String button2Name = driver.findElement(By.id("buttonId")).getAttribute("value");
//            System.out.println(button2Name);
//            assertFalse(button2Name.equalsIgnoreCase("This is also a button"));

        }


        @Test
        public void failTask () throws Exception {
//        TODO:
//        check that none of items with class "test"
//        contain number 190
            List<WebElement> allElementsWithClass = driver.findElements(By.className("test"));
            for (WebElement elementWithClass : allElementsWithClass) {
                assertFalse(elementWithClass.getText().contains("190"));
            }
        }
    }

    /*
          xpath
          //tagName[@attribute='value']

          CSS
          tagName[attribute='value']
          class - .
          id #
          tagname#value
          tagname.value

          <input type="button" id="buttonId" value="This is also a button" name="randomButton2">
          tagName: input
          atributes: type, id, value and name

          //input[@type="button"]
          //input[@id="buttonId"]

          input[type='button']
          input#buttonId
          input.class

    *
   */
