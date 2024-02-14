package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import baseTestComponenets.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.PaymentPage;
import pageObjects.ProductsCatalogPage;

public class StepDefinitionsImplementation extends Base{
	public LandingPage lp;
	public ProductsCatalogPage pcp;
	public ConfirmationPage cpp;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		lp=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void login_with_username_and_password(String username, String password)
	{
		pcp=lp.loginToApp(username,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void add_product_to_cart(String productName)
	{
		pcp.addProductToCart(productName);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName)
	{
        CartPage cp=pcp.clickOnCart();
		
		//verify if the product added is available in cart
		Boolean match=cp.verifyIfProductIsAvailableInCart(productName);
		Assert.assertTrue(match);
		
		//click on checkout button
		PaymentPage pp=cp.clickOnCheckOut();
		
		//enter country
		pp.chooseCountry("ind");
		cpp=pp.clickOnPlaceOrder();
	}
	
	@Then("{string} message is displayed in confirmation page")
	public void message_is_displayed_in_confirmation_page(String string)
	{
		String confirmationMsg=cpp.getConfMsg();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase(string));
		driver.close();
		
	}
	@Then("{string} is displayed")
	public void error_is_displayed(String string)
	{
		Assert.assertEquals(string, lp.captureWrongPwMsg());
		driver.close();
	}

}
