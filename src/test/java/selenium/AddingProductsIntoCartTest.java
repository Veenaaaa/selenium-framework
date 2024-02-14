package selenium;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTestComponenets.Base;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import pageObjects.CartPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.OrdersPage;
import pageObjects.PaymentPage;
import pageObjects.ProductsCatalogPage;

public class AddingProductsIntoCartTest extends Base {
	String productName="WROGN";
	@Test(dataProvider="getData",groups={"purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException
	{
		//login
		ProductsCatalogPage pcp=lp.loginToApp(input.get("username"), input.get("password"));
		
		//add product into cart
		pcp.addProductToCart(input.get("productName"));
		CartPage cp=pcp.clickOnCart();
		
		//verify if the product added is available in cart
		Boolean match=cp.verifyIfProductIsAvailableInCart(input.get("productName"));
		Assert.assertTrue(match);
		
		//click on checkout button
		PaymentPage pp=cp.clickOnCheckOut();
		
		//enter country
		pp.chooseCountry("ind");
		ConfirmationPage cpp=pp.clickOnPlaceOrder();
		
		//verify confirmation message
		String confirmationMsg=cpp.getConfMsg();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void verifyOrdersPage()
	{
		ProductsCatalogPage pcp=lp.loginToApp("kamathveena28@gmail.com", "Sudheer@18");
		OrdersPage op=pcp.clickOnOrders();
		Boolean match=op.verifyIfProductIsPresent(productName);
		Assert.assertTrue(match);
	}
	
	/*@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"kamathveena28@gmail.com","Sudheer@18","WROGN"},{"kamathveena37@gmail.com","Sudheer@18","QWERTY"}};
	}*/
	
	/*@DataProvider
	public Object[][] getData()
	{
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "kamathveena28@gmail.com");
		map.put("password", "Sudheer@18");
		map.put("productName", "WROGN");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "kamathveena37@gmail.com");
		map1.put("password", "Sudheer@18");
		map1.put("productName", "QWERTY");
		
		return new Object[][] {{map},{map1}};
		
		
	}*/
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data=getJsonDataToMap(".\\src\\test\\resources\\data.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}

}
