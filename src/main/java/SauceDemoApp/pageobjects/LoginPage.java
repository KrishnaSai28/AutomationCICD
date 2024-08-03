package SauceDemoApp.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SauceDemoApp.AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents {
	
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="user-name")
	WebElement name;
	
	@FindBy(css="input[placeholder='Password']")
	WebElement pass;
	
	@FindBy(id="login-button")
	WebElement submit;
	
	@FindBy(css="h3")
	WebElement errorMessage;
	
	public ProductCatalouge loginApplication(String userName,String password)
	{
		name.sendKeys(userName);
		pass.sendKeys(password);
		submit.click();
		ProductCatalouge products=new ProductCatalouge(driver);
		return products;
	}
	
	public void goTo(String url)
	{
		driver.get(url);
	}
	public String errorValidation()
	{
		return errorMessage.getText();
		
		
	}
	
	
	

}
