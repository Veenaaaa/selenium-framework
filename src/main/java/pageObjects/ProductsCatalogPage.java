package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import webdriverUtility.WebDriverUtilityMethods;

public class ProductsCatalogPage extends WebDriverUtilityMethods{
	WebDriver driver;
	public ProductsCatalogPage(WebDriver driver)
	{   super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orders;
	
	By products1=By.cssSelector(".mb-3");
	By cartBtn=By.cssSelector("button.w-10");
	By toastEle=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(products1);
		return products;
	}
	
	public WebElement getProduct(String productName)
	{
		WebElement pro=getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return pro;

	}
	public void addProductToCart(String productName)
	{
		getProduct(productName).findElement(cartBtn).click();
		waitForElementToAppear(toastEle);
		waitForElementToDisappear(spinner);
	}
	
	public CartPage clickOnCart()
	{
		cart.click();
		CartPage cp=new CartPage(driver);
		return cp;
	}

	public OrdersPage clickOnOrders()
	{
		orders.click();
		return new OrdersPage(driver);
	}
	

}
