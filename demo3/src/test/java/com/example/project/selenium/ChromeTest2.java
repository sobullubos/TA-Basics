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

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class ChromeTest2 {

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
    @DisplayName("Search for ticket with Extras: 1st class and add to basket")
    public void test() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30l);
        driver.get("https://tickets.oebb.at/en/ticket");

        // From station Feld
        By inputFrom = By.xpath("//div[@id='relationInputFrom']/input");
        wait.until(elementToBeClickable(inputFrom));
        WebElement fromStationElement = driver.findElement(inputFrom);
        String fromStation = "Linz/Donau Hbf";
        fromStationElement.sendKeys(fromStation);
        wait.until(presenceOfElementLocated(By.xpath("//span[text()='" + fromStation + "']")));
        fromStationElement.sendKeys(Keys.ENTER);

        // To station Feld
        By inputTo = By.xpath("//div[@id='relationInputTo']/input");
        wait.until(elementToBeClickable(inputTo));
        WebElement toStationElement = driver.findElement(inputTo);
        String toStation = "Innsbruck Hbf";
        toStationElement.sendKeys(toStation);
        wait.until(presenceOfElementLocated(By.xpath("//span[text()='" + toStation + "']")));
        toStationElement.sendKeys(Keys.ENTER);

        // Wochenkarte
        // By byButton = By.xpath("//button[@aria-label='Wien Salzburg Hbf One-way tickets and day tickets']");
        By byButton = By.xpath("//*[contains(@aria-label,'Weekly and monthly tickets')]");
        wait.until(presenceOfElementLocated(byButton));
        WebElement button = driver.findElement(byButton);
        button.click();

        By journey = By.xpath("//app-routes-list/div[1]");
        wait.until(presenceOfElementLocated(journey));
        WebElement journeyElement = driver.findElement(journey);
        journeyElement.click();

        // chose week card
        By weekCard = By.xpath("//span[@data-unique-id='offerTitle'and text()='ÖBB Wochenkarte']");
        wait.until(presenceOfElementLocated(weekCard));
        WebElement weekCardElement = driver.findElement(weekCard);
        weekCardElement.click();

        // Extras: 1st class
        By extraFirstClassCheckbox = By.xpath("//div[@data-unique-id='firstClass']/div[@class='content-section']");

        wait.until(presenceOfElementLocated(extraFirstClassCheckbox));
        WebElement extraFirstClassCheckboxElement = driver.findElement(extraFirstClassCheckbox);
        extraFirstClassCheckboxElement.click();

        // ins Warenkorb legen
        By addToBasket = By.xpath("//conversion-button/div");
        wait.until(presenceOfElementLocated(addToBasket));
        WebElement addToBasketButton = driver.findElement(addToBasket);
        addToBasketButton.click();

        Thread.sleep(5000);
    }
}
