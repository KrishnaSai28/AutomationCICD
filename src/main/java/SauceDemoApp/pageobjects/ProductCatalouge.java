package SauceDemoApp.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SauceDemoApp.AbstractComponents.AbstractComponents;

public class ProductCatalouge extends AbstractComponents {
	
	
	WebDriver driver;
	
	public ProductCatalouge(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@class='inventory_item_description']")
	List<WebElement> productList;
	
	By waiting=By.xpath("//div[@class='inventory_item_description']");
	By inventoryName=By.cssSelector(".inventory_item_name");
	By addingItem=By.cssSelector(".btn.btn_primary.btn_small.btn_inventory");
	
	public List<WebElement> getProductList()
	{
		waitUntilAppear(waiting);
		return productList;
	}
	
	public WebElement getProductName(String productName)
	{
		WebElement item=getProductList().stream().filter(s->s.findElement(inventoryName).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		System.out.println(item.getText());
		
		return item;
	}
	
	public void addProductToCartPage(String productName) 
	{
		WebElement desired=getProductName(productName);
		desired.findElement(addingItem).click();

	}
	
	
	
	
	
	

}
