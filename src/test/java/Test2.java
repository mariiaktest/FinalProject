package test;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test2 {
    private WebDriver driver;
    public WebDriverWait wait;

 @BeforeTest
public void SetUp(){
     driver = new ChromeDriver();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     wait = new WebDriverWait(driver,10);
 }
 @Test
    public void Test2(){
     driver.get("https://novaposhta.ua/");
     WebElement cargoNumber = driver.findElement(By.id("cargo_number"));
     cargoNumber.sendKeys("2045040754423");
     driver.findElement(By.xpath("//input[@id = 'cargo_number']/../input[2]")).click();
     wait.until(ExpectedConditions.titleIs("Керуйте доставкою посилок Нової пошти"));
     driver.findElement(By.xpath("//input[@value = 'Пошук'")).click();
     String text = driver.findElement(By.cssSelector(".np-message")).getText();
     Assertions.assertThat(text).contains("видана");

    }
    @Test
    public void NegativeTest() {
        driver.get("https://novaposhta.ua/");
        WebElement cargoNumber = driver.findElement(By.id("cargo_number"));
        cargoNumber.sendKeys("2045040754423");
        driver.findElement(By.xpath("//input[@id = 'cargo_number']/../input[2]")).click();
        wait.until(ExpectedConditions.titleIs("Керуйте доставкою Нової пошти"));
        driver.findElement(By.xpath("//input[@value = 'Пошук'")).click();

        String text = driver.findElement(By.cssSelector(".np-message")).getText();
        Assertions.assertThat(text).contains("видана");
    }
    @Test
    public  void MyTest(){
     driver.get("https://novaposhta.ua/");
     driver.findElement(By.xpath("//input[@id = 'cargo_number']/../input[2]")).click();
     driver.findElement(By.xpath("//input[@value='Пошук']")).click();

     WebElement Number = driver.findElement(By.id("en"));
     Number.sendKeys("12345533746");
     wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#np-number-input-desktop-btn-search-en")));
     driver.findElement(By.id("np-number-input-desktop-btn-search-en")).click();
     wait.until(ExpectedConditions.textToBe(By.id("np-number-input-desktop-message-error-message"),
             "Ми не знайшли посилку за таким номером. Якщо ви шукаєте інформацію про посилку, якій більше 3 місяців, будь ласка, зверніться у контакт-центр: 0 800 500 609"));


    }

    @AfterTest
    public  void TearDown(){
     driver.quit();
     driver=null;

        }
    }

