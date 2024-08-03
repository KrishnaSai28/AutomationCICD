package SauceDemoApp.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SauceDemoApp.TestComponents.BaseClass;
import SauceDemoApp.TestComponents.Retry;
import SauceDemoApp.pageobjects.CartPage;
import SauceDemoApp.pageobjects.ProductCatalouge;

public class ErrorValidations extends BaseClass{
	
	@Test(retryAnalyzer=Retry.class)
	public void loginError() throws IOException
	{
		//Elements
		String productName="Sauce Labs Fleece Jacket";
		page.loginApplication("standard_user", "secret_sauce");
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", page.errorValidation());
		
		
	}
	@Test
	public void productError()
	{
		String productName="Sauce Labs Fleece Jacket";
		ProductCatalouge products=page.loginApplication("standard_user", "secret_sauce");
		List<WebElement> productsList=products.getProductList();
		products.addProductToCartPage(productName);
		CartPage cartSelection=products.goToCartPage();
		Boolean valid_item=cartSelection.verifyProductDisplay("Sauce Labs Fleece Jacke");
		Assert.assertFalse(valid_item);
		
	}

}
