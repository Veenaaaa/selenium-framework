package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {
	WebDriver driver;
	public OrdersPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".table.table-bordered tr td:nth-child(3)")
	List<WebElement> orderNames;
	
	public Boolean verifyIfProductIsPresent(String productName)
	{
		Boolean match=orderNames.stream().anyMatch(orderName->orderName.getText().equalsIgnoreCase(productName));
		return match;
	}

}
