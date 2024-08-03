package SauceDemoApp.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SauceDemoApp.pageobjects.LoginPage;

public class BaseClass {
	public WebDriver driver;
	public  LoginPage page;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/SauceDempApp/resources/GlobalData.properties");
		prop.load(fis);
		
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			 driver=new ChromeDriver();
		}
		else if(browserName.contains("edge"))
		{
			 driver=new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//Maximize screen
		driver.manage().window().maximize();
		return driver;
		
	}
	
	@BeforeMethod
	public LoginPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		page=new LoginPage(driver);
		page.goTo("https://www.saucedemo.com/");
		return page;
		
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}
	
	public String getScreenShot(String testCaseName,WebDriver diver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	@AfterMethod
	public void close()
	{
		driver.close();
	}

}
