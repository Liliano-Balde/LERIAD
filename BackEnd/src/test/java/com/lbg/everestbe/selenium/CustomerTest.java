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

public class CustomerTest {

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
	@Order(1)

	void testCreate() throws InterruptedException {
		this.driver.get("http://localhost:3000/");

		WebElement newCustLink = this.driver.findElement(By
				.cssSelector("#root > nav > div > div > div > div > div.col-md-5.my-auto > ul > li:nth-child(1) > a"));
		newCustLink.click();

		WebElement name = this.driver.findElement(By.cssSelector("#name"));
		name.sendKeys("Jordan Harrison");

		WebElement address = this.driver.findElement(By.cssSelector("#address"));
		address.sendKeys("123 Road");

		WebElement email = this.driver.findElement(By.cssSelector("#email"));
		email.sendKeys("JH@domain.com");

		WebElement phone = this.driver.findElement(By.cssSelector("#phone"));
		phone.sendKeys("012345678");

		WebElement username = this.driver.findElement(By.cssSelector("#username"));
		username.sendKeys("JHarry2024");

		WebElement password = this.driver.findElement(By.cssSelector("#password"));
		password.sendKeys("JHarry2024");

		WebElement clickSubmit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"al\"]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickSubmit);
		Thread.sleep(500);
		clickSubmit.click();

		WebElement clickLoginAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.cssSelector("#root > div > div > main > form > div.overlay > div > div > div.btnContainer > button")));
		clickLoginAlert.click();

		WebElement adminUsername = this.driver.findElement(By.cssSelector("#username"));
		adminUsername.sendKeys("admin");

		WebElement adminPassword = this.driver.findElement(By.cssSelector("#password"));
		adminPassword.sendKeys("admin");

		WebElement loginClick = this.driver
				.findElement(By.cssSelector("#root > div > main > form > div > div > button"));
		Thread.sleep(500);
		loginClick.click();

		WebElement continueAlertClick = this.driver.findElement(
				By.cssSelector("#root > div > main > form > div.overlay > div > div > div.btnContainer > button"));
//		Thread.sleep(500);
		continueAlertClick.click();

		WebElement usernameDisplay = this.wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("#root > div > main > div:nth-child(3) > div > div > div:nth-child(2) > div > div > h4")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", usernameDisplay);
		Assertions.assertEquals("JHarry2024", usernameDisplay.getText());

		WebElement editClick = this.driver.findElement(By.cssSelector("div.row > div:nth-child(2) #edit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editClick);
		Thread.sleep(500);
		editClick.click();

		WebElement updateUser = this.driver.findElement(By.cssSelector("#username"));
		updateUser.sendKeys(".V2");

		WebElement updateClick = this.driver.findElement(By.cssSelector("#update"));
		Thread.sleep(500);
		updateClick.click();

		WebElement updatedUsernameDisplay = this.wait.until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector("#root > div > main > div:nth-child(3) > div > div > div:nth-child(2) > div > div > h4")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updatedUsernameDisplay);
		Assertions.assertEquals("JHarry2024.V2", updatedUsernameDisplay.getText());

		WebElement deleteCustomer = this.driver.findElement(By.cssSelector("div.row div:nth-child(2) #delete"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", deleteCustomer);
		Thread.sleep(500);
		deleteCustomer.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until((ExpectedConditions.invisibilityOfElementLocated(
				By.cssSelector("#root > div > main > div:nth-child(3) > div > div > div:nth-child(2) > div > div"))));
		try {
			this.driver.findElement(
					By.cssSelector("#root > div > main > div:nth-child(3) > div > div > div:nth-child(2) > div > div"));
			fail("Delete has failed");

		} catch (NoSuchElementException ex) {

		}

	}
}