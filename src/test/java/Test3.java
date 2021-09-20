package test;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test3 {
    public WebDriver driver;
    public WebDriverWait wait;



    @BeforeTest
    public void testSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait= new WebDriverWait(driver, 10);
    }
@Test
    public void TestNP() {
    driver.get("https://novaposhta.ua/");
    driver.findElement(By.cssSelector(".cost")).click();
    wait.until(ExpectedConditions.urlContains("/delivery"));
    String Text = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[3]/p")).getText();
    Assertions.assertThat(Text).contains("Даний сервіс допоможе Вам дізнатися орієнтовну вартість доставки Вашого відправлення");

    driver.findElement(By.id("DeliveryForm_senderCity")).click();
    wait.until(ExpectedConditions.textToBe(By.xpath("//h1"), "Вартість доставки"));

    driver.findElement(By.xpath("//span[text() = 'Київ']")).click();
    driver.findElement(By.id("DeliveryForm_recipientCity")).click();
    WebElement RecipientCity = driver.findElement(By.id("DeliveryForm_recipientCity"));
    RecipientCity.sendKeys("Дніпро");

    driver.findElement(By.xpath("//span[text() = 'м. Дніпро, Дніпропетровська обл.']")).click();
    driver.findElement(By.xpath("//span[text() = 'Посилки']")).click();
    driver.findElement(By.xpath("//ul/li[text()='Документи']")).click();
    driver.findElement(By.id("add-pack")).click();
    driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[4]/div[1]/div/form/div[1]/div[9]/div[2]/div[1]/div/ins")).click();
    driver.findElement(By.xpath("//span[text() = 'Конверт з ПБ плівкою E/15 (220х265) мм']")).click();
    driver.findElement(By.cssSelector(".DeliveryForm_packType_count")).click();

    WebElement BackPrice = driver.findElement(By.id("DeliveryForm_publicPrice"));
    BackPrice.clear();
    BackPrice.sendKeys("500");

    driver.findElement(By.id("DeliveryForm_backDelivery")).click();
    driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[4]/div[1]/div/form/div[1]/div[15]/div[1]/div[1]/div[1]")).click();
    driver.findElement(By.xpath("//ul/li[text() ='Грошовий переказ']")).click();


    WebElement Amount = driver.findElement(By.id("DeliveryForm_backDelivery_amount"));
    Amount.sendKeys("1000");
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Розрахувати вартість']")));
    driver.findElement(By.xpath("//input[@value='Розрахувати вартість']")).click();

    wait.until(ExpectedConditions.urlMatches("https://novaposhta.ua/delivery"));

    String Header = driver.findElement(By.xpath("//h3")).getText();
    Assertions.assertThat(Header).contains("Розрахункова вартість доставки");
}

    @AfterTest
    public  void TearDown() {
        driver.quit();
        driver = null;


    }
    }


