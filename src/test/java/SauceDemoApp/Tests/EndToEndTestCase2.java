package SauceDemoApp.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import SauceDemoApp.TestComponents.BaseClass;
import SauceDemoApp.pageobjects.CartPage;
import SauceDemoApp.pageobjects.CheckOutPage;
import SauceDemoApp.pageobjects.ConfirmationPage;
import SauceDemoApp.pageobjects.ProductCatalouge;

public class EndToEndTestCase2 extends BaseClass{
	ExtentReports exrep;
	
	@Test(dataProvider="getData")
	public void saucedemo(HashMap<String,String> datas) throws IOException
	{
		//Elements
//comments
		
		ProductCatalouge products=page.loginApplication(datas.get("name"),datas.get("pass"));
		List<WebElement> productsList=products.getProductList();
		products.addProductToCartPage(datas.get("productName"));
		CartPage cartSelection=products.goToCartPage();
		Boolean valid_item=cartSelection.verifyProductDisplay(datas.get("productName"));
		Assert.assertTrue(valid_item);
		CheckOutPage checkoutprocess=cartSelection.goToCheckOutPage();
		checkoutprocess.paymentProcess("Sai", "Vang", "50062");
		ConfirmationPage ultimateMessage=checkoutprocess.submitOrder();
		String checkMessage=ultimateMessage.checkMessage();
		Assert.assertEquals(checkMessage,"Thank you for your order!");

		
	}
	
	/*@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"standard_user","secret_sauce","Sauce Labs Fleece Jacket"},{"standard_user","secret_sauce","Sauce Labs Bolt T-Shirt"}};
	}
	
	@DataProvider
	public Object[][] getData()
	{
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("name","standard_user");
		map.put("pass", "secret_sauce");
		map.put("productName", "Sauce Labs Fleece Jacket");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("name","standard_user");
		map1.put("pass", "secret_sauce");
		map1.put("productName", "Sauce Labs Bolt T-Shirt");
		
		return new Object[][] {{map},{map1}};
	}
	*/
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"/src/test/java/SauceDemoApp/data/purchaseorder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	}
