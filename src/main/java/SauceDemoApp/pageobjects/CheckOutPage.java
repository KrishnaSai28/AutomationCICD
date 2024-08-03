package SauceDemoApp.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SauceDemoApp.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="first-name")
	WebElement firstname;
	
	@FindBy(name="lastName")
	WebElement lastname;
	
	@FindBy(xpath="//input[contains(@placeholder,'Zip')]")
	WebElement zipcode;
	
	@FindBy(css="input[type='submit']")
	WebElement submit;
	
	@FindBy(id="finish")
	WebElement confirmPage;
	
	public void paymentProcess(String first,String last,String code)
	{
		firstname.sendKeys(first);
		lastname.sendKeys(last);
		zipcode.sendKeys(code);
		submit.click();
		
	}
	
	public ConfirmationPage submitOrder()
	{
		confirmPage.click();
		ConfirmationPage finalPage=new ConfirmationPage(driver);
		return finalPage;
	}
	
	
	

}
