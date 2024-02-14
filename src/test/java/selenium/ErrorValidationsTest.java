package selenium;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import baseTestComponenets.Base;
import pageObjects.CartPage;
import pageObjects.ProductsCatalogPage;

public class ErrorValidationsTest extends Base {
	
	@Test
	public void LoginErrorValidation()
	{
		lp.loginToApp("kamathveena37@gmail.com", "Sudheer@1");
		String text="Incorrect email or password";
		Assert.assertEquals("Incorrect email or password.", lp.captureWrongPwMsg());
	}
	
	@Test(groups= {"ErrorHandling"}/*,retryAnalyzer=Retry.class*/)
	public void productErrorValidation()
	{
		String productName="WROGN";
		//login
		ProductsCatalogPage pcp=lp.loginToApp("kamathveena37@gmail.com", "Sudheer@18");
		
		//add product into cart
		pcp.addProductToCart(productName);
		CartPage cp=pcp.clickOnCart();
		
		//verify if the product added is available in cart
		Boolean match=cp.verifyIfProductIsAvailableInCart("WROGN3");
		Assert.assertFalse(match);
	}

}
