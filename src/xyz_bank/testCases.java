package xyz_bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class testCases {

	WebDriver driver = new ChromeDriver();
	String websiteUrl = "https://globalsqa.com/angularJs-protractor/BankingProject/#/login\r\n";
	Random rand = new Random();
	int customerID;
	int accountNumber;

	@BeforeTest
	public void setup() {
		driver.manage().window().maximize();
		driver.get(websiteUrl);
	}

	@Test(priority = 1, enabled = false)
	public void login() throws InterruptedException {

		Thread.sleep(3000);

		WebElement loginBtn = driver.findElement(By.cssSelector(".btn.btn-primary.btn-lg"));
		loginBtn.click();
	}

	@Test(priority = 2, enabled = false)
	public void selectName() throws InterruptedException {

		Thread.sleep(3000);

		WebElement nameSelect = driver.findElement(By.id("userSelect"));
		Select selector = new Select(nameSelect);

		List<WebElement> options = nameSelect.findElements(By.tagName("option"));

		int randomUser = rand.nextInt(1, options.size());

		selector.selectByIndex(randomUser);

		Thread.sleep(2000);

		WebElement loginBtn = driver.findElement(By.cssSelector("[type='submit']"));
		loginBtn.click();

	}

	@Test(priority = 3, enabled = false)
	public void deposit() throws InterruptedException {

		Thread.sleep(3000);

		WebElement depositBtn = driver.findElement(By.cssSelector("[ng-class=\"btnClass2\"]"));
		depositBtn.click();

		String oldBalance = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")).getText();

		Thread.sleep(2000);
		String depositAmount = "50";
		WebElement Deposit = driver.findElement(By.cssSelector("input.form-control.ng-invalid-required"));
		Deposit.sendKeys(depositAmount);
		;

		WebElement AmountDeposited = driver.findElement(By.cssSelector("[ng-submit='deposit()']"));
		WebElement depbtn = driver.findElement(By.cssSelector("[type=\'submit\']"));
		depbtn.click();

		Thread.sleep(1000);
		String newBalance = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")).getText();

		int ExpectedResult = Integer.parseInt(newBalance) - Integer.parseInt(oldBalance);
		int ActualResult = Integer.parseInt(depositAmount);

		Assert.assertEquals(ExpectedResult, ActualResult);

	}

	@Test(priority = 4, enabled = false)

	public void withdrawl() throws InterruptedException {

		Thread.sleep(3000);

		WebElement withdrawBtn = driver.findElement(By.cssSelector("[ng-class='btnClass3']"));
		withdrawBtn.click();
		String oldBalanceText = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]"))
				.getText();

		Thread.sleep(2000);
		String depositAmount2 = "30";
		WebElement Deposit = driver.findElement(By.cssSelector("input[ng-model='amount']"));
		Deposit.sendKeys(depositAmount2);
		;

		WebElement AmountDeposited = driver.findElement(By.cssSelector("[ng-model='amount']"));
		WebElement withdrawBtn2 = driver.findElement(By.cssSelector("[type='submit']"));
		withdrawBtn2.click();

		Thread.sleep(1000);
		String newBalanceText = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]"))
				.getText();

		Thread.sleep(2000);
		int ExpectedResult = Integer.parseInt(oldBalanceText) - Integer.parseInt(newBalanceText);
		int ActualResult = Integer.parseInt(depositAmount2);

		Assert.assertEquals(ExpectedResult, ActualResult);

	}

	@Test(priority = 5, enabled = false)

	public void transactions() throws InterruptedException {

		Thread.sleep(3000);
		String expectedBalanceText = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]"))
				.getText();

		WebElement transactionsBtn = driver.findElement(By.cssSelector("[ng-class='btnClass1']"));
		transactionsBtn.click();

		Thread.sleep(3000);

		WebElement transactionTable = driver.findElement(By.className("table-striped"))
				.findElement(By.tagName("tbody"));

		List<WebElement> tranTableRows = transactionTable.findElements(By.tagName("tr"));

		int actualBalance = 0;

		for (int i = 0; i < tranTableRows.size(); i++) {

			List<WebElement> tranTableCol = tranTableRows.get(i).findElements(By.tagName("td"));

			String transactionType = tranTableCol.get(2).getText();

			if (transactionType.equals("Credit")) {
				actualBalance += Integer.parseInt(tranTableCol.get(1).getText());
			} else {

				actualBalance -= Integer.parseInt(tranTableCol.get(1).getText());
			}

		}

		Assert.assertEquals(actualBalance, Integer.parseInt(expectedBalanceText));

	}

	@Test(priority = 6, enabled = false)

	public void backToHome() throws InterruptedException {

		WebElement homeBtn = driver.findElement(By.className("home"));
		homeBtn.click();

	}

	@Test(priority = 7, enabled = true)

	public void managerLogin() throws InterruptedException {
		Thread.sleep(3000);
		WebElement loginBtn = driver.findElement(By.cssSelector("[ng-click='manager()']"));
		loginBtn.click();

	}

	@Test(priority = 8, enabled = true)

	public void addCustomer() throws InterruptedException {

		String[] names = { "Ahmed", "Sara", "Hadeel", "Omar", "Lina", "Yousef", "Mona", "Samir", "Dina", "Tariq" };

		Thread.sleep(3000);
		WebElement addCustomerBtn = driver.findElement(By.cssSelector("[ng-class=\"btnClass1\"]"));
		addCustomerBtn.click();

		Thread.sleep(3000);
		WebElement addFname = driver.findElement(By.cssSelector("[ng-model=\"fName\"]"));
		addFname.sendKeys(names[rand.nextInt(10)]);

		WebElement addLname = driver.findElement(By.cssSelector("[ng-model=\"lName\"]"));
		addLname.sendKeys(names[rand.nextInt(10)]);

		WebElement addCode = driver.findElement(By.cssSelector("[ng-model=\"postCd\"]"));
		addCode.sendKeys(rand.nextInt(10000, 99999) + "");

		WebElement submitBtn = driver.findElement(By.cssSelector("[type=\"submit\"]"));
		submitBtn.click();

		Thread.sleep(1000);

		// Switch to alert
		Alert navigate2alert = driver.switchTo().alert();
		// get alert and split text to get the id and saved it on customerID to future
		// use
		customerID = Integer.parseInt(navigate2alert.getText().split(":")[1]);
		navigate2alert.accept();

	}

	@Test(priority = 9, enabled = true)

	public void addAccount() throws InterruptedException {

		Thread.sleep(3000);
		WebElement addAccountBtn = driver.findElement(By.cssSelector("[ng-class=\"btnClass2\"]"));
		addAccountBtn.click();

		Thread.sleep(3000);

		WebElement nameSelect = driver.findElement(By.id("userSelect"));
		Select selector = new Select(nameSelect);

		List<WebElement> options = nameSelect.findElements(By.tagName("option"));

		int randomUser = rand.nextInt(1, options.size());

		selector.selectByIndex(randomUser);

		WebElement nameSelect2 = driver.findElement(By.id("currency"));
		Select selector2 = new Select(nameSelect2);

		List<WebElement> options2 = nameSelect2.findElements(By.tagName("option"));

		int randomUser2 = rand.nextInt(1, options2.size());

		selector2.selectByIndex(randomUser2);

		Thread.sleep(2000);

		WebElement loginBtn = driver.findElement(By.cssSelector("[type='submit']"));
		loginBtn.click();

		Thread.sleep(1000);
		// Switch to alert
		Alert navigate2alert = driver.switchTo().alert();
		// get alert and split text to get the id and saved it on customerID to future
		// use
		accountNumber = Integer.parseInt(navigate2alert.getText().split(":")[1]);
		navigate2alert.accept();
		
		System.out.println(customerID + "   " + accountNumber);

	}
	
	@Test(priority = 10, enabled = true)

	public void deleteCustomer() throws InterruptedException {

		Thread.sleep(3000);
		WebElement addAccountBtn = driver.findElement(By.cssSelector("[ng-class=\"btnClass3\"]"));
		addAccountBtn.click();

		Thread.sleep(3000);
		
		WebElement searchField = driver.findElement(By.cssSelector("[ng-model=\"searchCustomer\"]"));
		searchField.sendKeys(accountNumber+"");
		
		WebElement deleteBtn = driver.findElement(By.cssSelector("[ng-click=\"deleteCust(cust)\"]"));
		deleteBtn.click();
		
		
	}

}
