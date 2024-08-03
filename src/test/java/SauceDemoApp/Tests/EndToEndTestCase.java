package SauceDemoApp.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTestCase {
	
	@Test
	public void saucedemo()
	{
		//Elements
		String item="Test.allTheThings() T-Shirt (Red)";
		
		WebDriver driver=new ChromeDriver();
		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//Maximize screen
		driver.manage().window().maximize();
		//Opening the URL
		driver.get("https://www.saucedemo.com/");
		//Login
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		//Adding elements to cart
		//Getting all items
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='inventory_item_description']"))));
		List<WebElement> allItems= driver.findElements(By.xpath("//div[@class='inventory_item_description']"));
		WebElement items=allItems.stream().filter(s->s.findElement(By.cssSelector(".inventory_item_name")).getText().equalsIgnoreCase(item)).findFirst().orElse(null);
		items.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).click();
		
		//Cart Page
		driver.findElement(By.cssSelector(".shopping_cart_badge")).click();
		List<WebElement> valid_product=driver.findElements(By.xpath("//div[@class='cart_item_label']"));
		boolean validItem=valid_product.stream().anyMatch(s->s.findElement(By.cssSelector("a")).getText().equalsIgnoreCase(item));
		Assert.assertTrue(validItem);
		
		//Checkout
		
		driver.findElement(By.cssSelector("#checkout")).click();
		
		//payment
		driver.findElement(By.id("first-name")).sendKeys("Sai");
		driver.findElement(By.name("lastName")).sendKeys("Vang");
		driver.findElement(By.xpath("//input[contains(@placeholder,'Zip')]")).sendKeys("50068");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		
		driver.findElement(By.id("finish")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div/h2")).getText(),"Thank you for your order!");
		
		driver.close();
	}

}
