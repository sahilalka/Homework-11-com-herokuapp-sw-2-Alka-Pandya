package testsuite;


import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "http://the-internet.herokuapp.com/login";
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }
    /**
    1. userSholdLoginSuccessfullyWithValidCredentials
* Enter “tomsmith” username
* Enter “SuperSecretPassword!” password
* Click on ‘LOGIN’ button
* Verify the text “Secure Area”
     */
    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
//Enter valid Username
        driver.findElement(By.id("username")).sendKeys("tomsmith");
//Enter valid password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
// Click on ‘LOGIN’ button
        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();
        //Verify the text “Secure Area”
        String expectedMessage = "Secure Area";
        WebElement actualTextElement = driver.findElement(By.xpath("//h2[contains(text(),' Secure Area')]"));
        String actualMessage = actualTextElement.getText();
        Assert.assertEquals("Secure area text  is not displayed", expectedMessage, actualMessage);
    }
    @Test
    public void verifyTheUsernameErrorMessage() {
        //Enter Invalid Username
        driver.findElement(By.id("username")).sendKeys("tomsmith1");

        //Enter valid password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        // Click on ‘LOGIN’ button
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();

        //Verify the error message “Your username is invalid!”
        String expectedMessage = "Your username is invalid!";
        WebElement actualTextElement = driver.findElement(By.id("flash-messages"));
        String actualMessage = actualTextElement.getText();
        boolean trueMessage = actualMessage.contains(expectedMessage);
        Assert.assertTrue(trueMessage);

    }
    /**3. verifyThePasswordErrorMessage
* Enter “tomsmith” username
* Enter “SuperSecretPassword” password
* Click on ‘LOGIN’ button
* Verify the error message “Your password
    is invalid!”*/
    @Test
    public void verifyThePasswordErrorMessage() {
        //Enter valid Username
        driver.findElement(By.id("username")).sendKeys("tomsmith");

        //Enter Invalid password
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");

        // Click on ‘LOGIN’ button
        WebElement loginButton = driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        loginButton.click();

        // Verify the error message “Your password
        //    is invalid!”*/
        String expectedMessage = "Your password is invalid!";
        WebElement actualTextElement = driver.findElement(By.xpath("//div[contains(text(),'Your password is invalid!')]"));
        String actualMessage = actualTextElement.getText();
        boolean trueMessage = actualMessage.contains(expectedMessage);
        Assert.assertTrue(trueMessage);
    }

    @After
    public void tearDown() {
        // closeBrowser();
    }
}