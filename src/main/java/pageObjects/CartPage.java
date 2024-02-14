package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage {
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productsInCart;
	
	@FindBy(css=".totalRow .btn")
	private WebElement checkoutBtn;
	
	public Boolean verifyIfProductIsAvailableInCart(String productName)
	{
		Boolean match=productsInCart.stream().anyMatch(productInCart->productInCart.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public PaymentPage clickOnCheckOut()
	{
		checkoutBtn.click();
		PaymentPage pp=new PaymentPage(driver);
		return pp;
	}
	

}
