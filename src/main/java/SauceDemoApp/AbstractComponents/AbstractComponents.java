package SauceDemoApp.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SauceDemoApp.pageobjects.CartPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver)
	{
		
		this.driver=driver;
	}
	
	@FindBy(css=".shopping_cart_badge")
	WebElement cartIcon;
	
	public void waitUntilAppear(By findBy)
	{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public CartPage goToCartPage() 
	{
		
		cartIcon.click();
		CartPage cartSelection=new CartPage(driver);
		return cartSelection;
	}

}
