package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample6Task {
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
        driver.close();
    }

    @Test
    public void findElementByXPath() throws Exception {
//         TODO:
//        2 ways to find text: "Heading 2 text":
        System.out.println("1.1) text of element: " +
                driver.findElement(By.xpath("//*[@id='heading_2']")).getText() + ".");

        String heading2 = driver.findElement(By.xpath("//*[text()='Heading 2 text']")).getText();
        System.out.println("1.2) text of element: " +heading2 + ".");
//        1-2 ways to find text: "Test Text 1"
//        1-2 ways to find text: "Test Text 2"
        String TestText2 = driver.findElement(By.xpath("//*[@class='twoTest']")).getText();
        System.out.println("2) text of element: " + TestText2 + ".");
//        1-2 ways to find text: "Test Text 3"
        String TestText3 = driver.findElement(By.xpath("//div[5]/p[1]")).getText();
        System.out.println("3) text of element: " + TestText3 + ".");
//        1-2 ways to find text: "Test Text 4"
        String TestText4 = driver.findElement(By.xpath("//div[5]/p[2]")).getText();
        System.out.println("4) text of element: " + TestText4 + ".");
//        1-2 ways to find text: "Test Text 5"
        String TestText5 = driver.findElement(By.xpath("//*[@class='Test']")).getText();
        System.out.println("5) text of element: " + TestText5 + ".");
//        1-2 ways to find text: "This is also a button"
        String TestText6 = driver.findElement(By.xpath("//div[6]/p[2]")).getText();
        System.out.println("6) text of element: " + TestText6 + ".");
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        System.out.println("7) text of element: " + driver.findElement(By.cssSelector("#heading_2")).getText() + ".");
//        1-2 ways to find text: "Test Text 1"
        System.out.println("8) text of element: " + driver.findElement(By.cssSelector(".test")).getText() + ".");
//        1-2 ways to find text: "Test Text 2"
        System.out.println("9) text of element: " + driver.findElement(By.cssSelector(".twoTest")).getText() + ".");
//        1-2 ways to find text: "Test Text 3"
        System.out.println("10) text of element: " +
                driver.findElement(By.cssSelector("div#test3 p:first-child")).getText() + ".");
//        1-2 ways to find text: "This is also a button"
        System.out.println("11) value of element is: " +
                driver.findElement(By.cssSelector("[name='randomButton2']")).getAttribute("value") + ".");
    }
}
