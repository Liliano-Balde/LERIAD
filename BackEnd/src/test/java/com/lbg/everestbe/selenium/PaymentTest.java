package com.lbg.everestbe.selenium;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)

@Sql(scripts = { "classpath:everest-schema.sql",
		"classpath:everest-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class PaymentTest {

	private RemoteWebDriver driver;
	private WebDriverWait wait;

	@BeforeEach
	void init() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	@Test
	@Order(4)

	void testPayment() throws InterruptedException {
		this.driver.get("http://localhost:3000/checkout");

		WebElement nameInput = this.driver
				.findElement(By.cssSelector("#root > div > main > form > div > input:nth-child(3)"));
		nameInput.sendKeys("Georgie Porgie");

		WebElement cardInput = this.driver
				.findElement(By.cssSelector("#root > div > main > form > div > input:nth-child(6)"));
		cardInput.sendKeys("4056 4583 4573 2374");

		WebElement dateInput = this.driver
				.findElement(By.cssSelector("#root > div > main > form > div > input:nth-child(9)"));
		dateInput.sendKeys("07/27");

		WebElement cvcInput = this.driver
				.findElement(By.cssSelector("#root > div > main > form > div > input:nth-child(12)"));
		cvcInput.sendKeys("578");

		WebElement postcodeInput = this.driver
				.findElement(By.cssSelector("#root > div > main > form > div > input:nth-child(15)"));
		postcodeInput.sendKeys("578");

		WebElement paymentSubmit = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("#root > div > main > form > div > button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paymentSubmit);
		Thread.sleep(500);
		paymentSubmit.click();

		WebElement confirmSubmit = this.wait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("#root > div > main > div > div > div > div.content")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confirmSubmit);
		Assertions.assertEquals("Your order is now complete", confirmSubmit.getText());

		WebElement modalSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("#root > div > main > div > div > div > div.btnContainer > button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", modalSubmit);
		Thread.sleep(500);
		modalSubmit.click();

	}

}
