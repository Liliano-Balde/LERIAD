package com.lbg.everestbe.selenium;

import static org.junit.jupiter.api.Assertions.fail;

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

public class ItemTest {

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
	@Order(2)
	void testItem() throws InterruptedException {
		this.driver.get("http://localhost:3000/");

		WebElement adminUsername = this.driver.findElement(By.cssSelector("#username"));
		adminUsername.sendKeys("admin");

		WebElement adminPassword = this.driver.findElement(By.cssSelector("#password"));
		adminPassword.sendKeys("admin");

		WebElement loginClick = this.driver
				.findElement(By.cssSelector("#root > div > main > form > div > div > button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginClick);
		Thread.sleep(500);
		loginClick.click();

		WebElement adminContinueClick = this.driver.findElement(
				By.cssSelector("#root > div > main > form > div.overlay > div > div > div.btnContainer > button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", adminContinueClick);
		Thread.sleep(500);
		adminContinueClick.click();

		WebElement itemName = this.driver.findElement(By.cssSelector("#iname"));
		itemName.sendKeys("Football");

		WebElement itemDescription = this.driver.findElement(By.cssSelector("#idesc"));
		itemDescription.sendKeys("Demonskin 2.0 tech");

		WebElement itemPrice = this.driver.findElement(By.cssSelector("#iprice"));
		itemPrice.sendKeys("39.99");

		WebElement itemQuantity = this.driver.findElement(By.cssSelector("#iquant"));
		itemQuantity.sendKeys("3");

		WebElement submitClick = this.driver.findElement(By.cssSelector("#root > div > main > form > div > button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitClick);
		Thread.sleep(500);
		submitClick.click();

		WebElement checkItem = this.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#root > div > main > div:nth-child(2) > div > div > div > div:nth-child(4) > div > h4 > p:nth-child(3)")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkItem);
		Assertions.assertEquals("Demonskin 2.0 tech", checkItem.getText());

		WebElement editClick = this.driver.findElement(By.cssSelector("div.row > div:nth-child(4) #edititem"));
		Thread.sleep(500);
		editClick.click();

		WebElement clearName = driver.findElement(By.cssSelector("#inameup"));
		clearName.clear();

		WebElement updatedName = this.driver.findElement(By.cssSelector("#inameup"));
		updatedName.sendKeys("Jordan");

		WebElement clearDescription = driver.findElement(By.cssSelector("#idescup"));
		clearDescription.clear();

		WebElement updatedDescription = this.driver.findElement(By.cssSelector("#idescup"));
		updatedDescription.sendKeys("Wizard");

		WebElement clearPrice = driver.findElement(By.cssSelector("#ipriceup"));
		clearPrice.clear();

		WebElement updatedPrice = this.driver.findElement(By.cssSelector("#ipriceup"));
		updatedPrice.sendKeys("999999999");

		WebElement clearQuantity = driver.findElement(By.cssSelector("#ad"));
		clearQuantity.clear();

		WebElement updatedQuantity = this.driver.findElement(By.cssSelector("#ad"));
		updatedQuantity.sendKeys("1");

		WebElement updateClick = this.driver.findElement(By.cssSelector("#root > div > form > div > button"));
		updateClick.click();

		WebElement checkUpdatedName = this.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#root > div > main > div:nth-child(2) > div > div > div > div:nth-child(4) > div > h4 > p:nth-child(2)")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkUpdatedName);
		Assertions.assertEquals("Jordan", checkUpdatedName.getText());

		WebElement checkUpdatedDesc = this.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#root > div > main > div:nth-child(2) > div > div > div > div:nth-child(4) > div > h4 > p:nth-child(3)")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkUpdatedDesc);
		Assertions.assertEquals("Wizard", checkUpdatedDesc.getText());

		WebElement checkUpdatedPrice = this.wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"#root > div > main > div:nth-child(2) > div > div > div > div:nth-child(4) > div > h4 > p:nth-child(4)")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkUpdatedPrice);
		Assertions.assertEquals("Price: £999999999", checkUpdatedPrice.getText());

		WebElement deleteItem = this.driver.findElement(By.cssSelector(
				"#root > div > main > div:nth-child(2) > div > div > div > div:nth-child(4) > div > button.btn.btn-danger"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", deleteItem);
		Thread.sleep(500);
		deleteItem.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until((ExpectedConditions.invisibilityOfElementLocated(
				By.cssSelector("#root > div > main > div:nth-child(2) > div > div > div > div:nth-child(4)"))));
		try {
			this.driver.findElement(
					By.cssSelector("#root > div > main > div:nth-child(2) > div > div > div > div:nth-child(4)"));
			fail("Delete has failed");

		} catch (NoSuchElementException ex) {

		}

	}
}