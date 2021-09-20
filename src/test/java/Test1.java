package test;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test1 {
    private WebDriver driver;
    public WebDriverWait wait;


    @BeforeTest
    public void testSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait= new WebDriverWait(driver, 10);
    }


    @Test
        public void TestPositive()
    {

        driver.get("https://regenxbio.itc-portal.com/");


        WebElement Email = driver.findElement(By.id("email"));
        Email.sendKeys("mariiaktest@gmail.com");

        WebElement Password = driver.findElement(By.id("password"));
        Password.sendKeys("Qwerty1Qwerty@");

        driver.findElement(By.xpath("//span[contains(@class, 'Login')]")).click();

        String forgotPass =driver.findElement(By.xpath("//h6")).getText();

        wait.until(ExpectedConditions.textToBe(By.xpath("//h6"), "Forgot password"));

        wait.until(ExpectedConditions.urlContains("/forgot"));

        Assertions.assertThat(forgotPass).contains("Forgot password");

        WebElement email = driver.findElement(By.cssSelector("#email"));
        email.sendKeys("mariiaktest@gmail.com");


        driver.findElement(By.xpath("//html/body/div[1]/div/div[1]/div[2]/div/div[3]/div/div/button/span[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notifications-wrapper")));

        String Error = driver.findElement(By.cssSelector(".notifications-wrapper")).getText();
        Assertions.assertThat(Error).contains("There was an error reset a password");

    }
    @Test
    public void TestNegative() {


        driver.get("https://regenxbio.itc-portal.com/");

        WebElement Email = driver.findElement(By.id("email"));
        Email.sendKeys("mariiaktest@gmail.com");

        WebElement Password = driver.findElement(By.id("password"));
        Password.sendKeys("Qwerty1Qwerty@");
        driver.findElement(By.xpath("//span[contains(@class, 'Login')]")).click();

        String ForgotPass =driver.findElement(By.xpath("//h6")).getText();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h6"), "Forgot password"));

        Assertions.assertThat(ForgotPass).contains("Reset password");

        driver.findElement(By.xpath("//html/body/div[1]/div/div[1]/div[2]/div/div[3]/div/div/button/span[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notifications-wrapper")));

        String Error = driver.findElement(By.cssSelector(".notifications-wrapper")).getText();
        Assertions.assertThat(Error).contains("There was an error resetting a password");
    }
    @AfterTest
    public void tearDown (){
    driver.quit();
    driver=null;
    }
}
