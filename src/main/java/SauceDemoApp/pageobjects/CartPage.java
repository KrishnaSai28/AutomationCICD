package SauceDemoApp.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import SauceDemoApp.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@class='cart_item_label']")
	List<WebElement> valid_product;
	
	@FindBy(css="#checkout")
	WebElement checkOut;
	
	
	By itemText=By.cssSelector("a");
	
	public boolean verifyProductDisplay(String productName)
	{
		
		boolean validItem=valid_product.stream().anyMatch(s->s.findElement(itemText).getText().equalsIgnoreCase(productName));
		return validItem;
		
		
	}
	
	public CheckOutPage goToCheckOutPage()
	{
		checkOut.click();
		CheckOutPage checkoutpage=new CheckOutPage(driver);
		return checkoutpage;
	}
	
	
	

}
