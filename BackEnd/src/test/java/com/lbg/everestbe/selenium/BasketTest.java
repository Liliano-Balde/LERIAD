package com.lbg.everestbe.selenium;

import static org.assertj.core.api.Assertions.fail;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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

public class BasketTest {

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
	@Order(3)
	void testBasket() throws InterruptedException {
		this.driver.get("http://localhost:3000/");

		WebElement nameAttempt = this.driver.findElement(By.cssSelector("#username"));
		nameAttempt.sendKeys("Lb4lD£");

		WebElement failedPassword = this.driver.findElement(By.cssSelector("#password"));
		failedPassword.sendKeys("Testng");

		WebElement clickSubmit = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/main/form/div/div/button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickSubmit);
		Thread.sleep(500);
		clickSubmit.click();

		WebElement continueAlertClick = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("#root > div > main > form > div.overlay > div > div > div.btnContainer > button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueAlertClick);
		Thread.sleep(500);
		continueAlertClick.click();

		WebElement name = this.driver.findElement(By.cssSelector("#name"));
		name.sendKeys("Liliano");

		WebElement address = this.driver.findElement(By.cssSelector("#address"));
		address.sendKeys("456 Road");

		WebElement email = this.driver.findElement(By.cssSelector("#email"));
		email.sendKeys("LB@domain.com");

		WebElement phone = this.driver.findElement(By.cssSelector("#phone"));
		phone.sendKeys("012345678");

		WebElement username = this.driver.findElement(By.cssSelector("#username"));
		username.sendKeys("Lb4lD£");

		WebElement password = this.driver.findElement(By.cssSelector("#password"));
		password.sendKeys("Testing");

		WebElement clickRegister = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"al\"]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickRegister);
		Thread.sleep(500);
		clickRegister.click();

		WebElement confirmSubmit = this.wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#root > div > div > main > form > div.overlay > div > div > div.content")));
		Assertions.assertEquals("Registered! Redirecting to the login page", confirmSubmit.getText());

		WebElement clickContinueModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.cssSelector("#root > div > div > main > form > div.overlay > div > div > div.btnContainer > button")));
		Thread.sleep(500);
		clickContinueModal.click();

		WebElement name2 = this.driver.findElement(By.cssSelector("#username"));
		name2.sendKeys("Lb4lD£");

		WebElement password2 = this.driver.findElement(By.cssSelector("#password"));
		password2.sendKeys("Testing");

		WebElement clickSubmit2 = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/main/form/div/div/button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickSubmit2);
		Thread.sleep(500);
		clickSubmit2.click();

		WebElement welcomeClick = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("#root > div > main > form > div.overlay > div > div > div.btnContainer > button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", welcomeClick);
		Thread.sleep(500);
		welcomeClick.click();

		WebElement clickAddItem1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("#root > div > main > div > div > div > div:nth-child(1) > div > button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickAddItem1);
		Thread.sleep(500);
		clickAddItem1.click();

		WebElement itemModalClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#root > div > main > div > div > div > div.overlay > div > div > div.btnContainer > button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", itemModalClick);
		Thread.sleep(500);
		itemModalClick.click();

		WebElement clickAddItem2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("#root > div > main > div > div > div > div:nth-child(2) > div > button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickAddItem2);
		Thread.sleep(500);
		clickAddItem2.click();

		WebElement itemModalClick2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#root > div > main > div > div > div > div.overlay > div > div > div.btnContainer > button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", itemModalClick2);
		Thread.sleep(500);
		itemModalClick2.click();

		WebElement clickAddItem3 = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("#root > div > main > div > div > div > div:nth-child(3) > div > button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickAddItem3);
		Thread.sleep(500);

		clickAddItem3.click();

		WebElement itemModalClick3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#root > div > main > div > div > div > div.overlay > div > div > div.btnContainer > button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", itemModalClick3);
		Thread.sleep(500);
		itemModalClick3.click();

		WebElement clickBasket = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#basket")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickBasket);
		Thread.sleep(500);
		clickBasket.click();

		WebElement clickClear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#root > div > main > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > button:nth-child(4)")));
		clickClear.click();

		WebElement checkDeletedItem = this.driver.findElement(
				By.cssSelector("#root > div > main > table > tbody > tr:nth-child(1) > td:nth-child(6) > button"));
		checkDeletedItem.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/main/table/tbody/tr[3]")));

		try {
			this.driver.findElement(By.xpath("//*[@id='root']/div/main/table/tbody/tr[3]"));
			fail("Delete has failed");
		} catch (NoSuchElementException ex) {

		}

		WebElement clickClear2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#root > div > main > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > button:nth-child(4)")));
		clickClear2.click();

		WebElement clickIncrease = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#root > div > main > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > button:nth-child(5)")));
		clickIncrease.click();

		WebElement clickIncrease2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#root > div > main > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > button:nth-child(5)")));
		clickIncrease2.click();

		WebElement clickDecrease = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#root > div > main > table > tbody > tr:nth-child(2) > td:nth-child(4) > div > button:nth-child(3)")));
		clickDecrease.click();

		WebElement checkItemName = this.wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#root > div > main > table > tbody > tr:nth-child(1) > td:nth-child(2)")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkItemName);
		Assertions.assertEquals("Camera", checkItemName.getText());

		WebElement checkPrice = this.wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#root > div > main > table > tbody > tr:nth-child(1) > td:nth-child(3)")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkPrice);
		Assertions.assertEquals("£ 149.99", checkPrice.getText());

		WebElement checkIteName2 = this.wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#root > div > main > table > tbody > tr:nth-child(2) > td:nth-child(2)")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkIteName2);
		Assertions.assertEquals("Drone", checkIteName2.getText());

		WebElement checkPrice2 = this.wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#root > div > main > table > tbody > tr:nth-child(2) > td:nth-child(3)")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkPrice2);
		Assertions.assertEquals("£ 1500", checkPrice2.getText());

		WebElement checkTotal = this.wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#root > div > main > div > h2")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkTotal);
		Assertions.assertEquals("Basket Total : £1799.98", checkTotal.getText());

		WebElement clickCheckout = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#root > div > main > button")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickCheckout);
		Thread.sleep(500);
		clickCheckout.click();

		WebElement checkCheckoutPage = this.wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#root > div > header > h1")));
		Assertions.assertEquals("Payment Details", checkCheckoutPage.getText());

	}
}