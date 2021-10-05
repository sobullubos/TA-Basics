package com.example.project.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class ChromeTest1 {

    private WebDriver driver;

    @BeforeEach
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Search for ticket and add to basket")
    public void test() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30l);
        driver.get("https://tickets.oebb.at/en/ticket");

        By inputFrom = By.xpath("//div[@id='relationInputFrom']/input");
        wait.until(elementToBeClickable(inputFrom));
        WebElement element = driver.findElement(inputFrom);
        String fromStation = "Wien Hbf (U)";
        element.sendKeys(fromStation);
        wait.until(presenceOfElementLocated(By.xpath("//span[text()='" + fromStation + "']")));
        element.sendKeys(Keys.ENTER);

        By inputTo = By.xpath("//div[@id='relationInputTo']/input");
        wait.until(elementToBeClickable(inputTo));
        WebElement element2 = driver.findElement(inputTo);
        String toStation = "Salzburg Hbf";
        element2.sendKeys(toStation);
        wait.until(presenceOfElementLocated(By.xpath("//span[text()='" + toStation + "']")));
        element2.sendKeys(Keys.ENTER);

        // By byButton = By.xpath("//button[@aria-label='Wien Salzburg Hbf One-way tickets and day tickets']");
        By byButton = By.xpath("//*[contains(@aria-label,'tickets and day tickets')]");
        wait.until(presenceOfElementLocated(byButton));
        WebElement button = driver.findElement(byButton);
        button.click();

        By journey = By.xpath("//div[contains(@id,'connection_')]");
        wait.until(presenceOfElementLocated(journey));
        WebElement journeyElement = driver.findElement(journey);
        journeyElement.click();

        By addToBasket = By.xpath("//conversion-button/div");
        wait.until(presenceOfElementLocated(addToBasket));
        WebElement addToBasketButton = driver.findElement(addToBasket);
        addToBasketButton.click();

        Thread.sleep(5000);
    }
}
