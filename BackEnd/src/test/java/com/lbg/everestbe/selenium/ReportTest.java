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

public class ReportTest {

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
	@Order(5)
	void reportTest() throws InterruptedException {
		this.driver.get("http://localhost:3000/report-issues");

		WebElement reportClick = this.driver.findElement(By
				.cssSelector("#root > nav > div > div > div > div > div.col-md-5.my-auto > ul > li:nth-child(3) > a"));
		reportClick.click();

		WebElement email = this.driver.findElement(By.cssSelector("#email"));
		email.sendKeys("justanemail@domain.com");

		WebElement issue = this.driver.findElement(By.cssSelector("#issue"));
		issue.sendKeys("Just sending this issue to make sure it tests properly.");

		WebElement submitClick = this.driver
				.findElement(By.cssSelector("#root > div > form > div:nth-child(3) > button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitClick);
		Thread.sleep(1000);
		submitClick.click();

		WebElement checklert = this.wait.until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("#root > div > div > div > div > div.content")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checklert);
		Assertions.assertEquals("Thank you for your feedback!", checklert.getText());

		WebElement continueClick = this.driver
				.findElement(By.cssSelector("#root > div > div > div > div > div.btnContainer > button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueClick);
		Thread.sleep(1000);
		continueClick.click();

	}
}
