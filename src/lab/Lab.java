package lab;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class Lab {
	
	WebDriver driver = new ChromeDriver();		

	String URL = "https://www.saucedemo.com/";
	String userName  = "standard_user";
	String password  = "secret_sauce";
	
	@BeforeTest
	public void setup() {
		driver.manage().window().maximize();
		driver.get(URL);
	}
	
	@Test(priority = 1)
	public void Login() {
		WebElement user_name_input = driver.findElement(By.id("user-name"));
		WebElement password_input = driver.findElement(By.id("password"));
		WebElement login_button  = driver.findElement(By.id("login-button"));		
		user_name_input.sendKeys(userName);
		password_input.sendKeys(password);
		login_button.click();
	}

	
	@Test(priority = 2)
	public void print() {
		List<WebElement> addBtns = driver.findElements(By.className("btn"));
		List<WebElement> itemsPrices = driver.findElements(By.className("inventory_item_price"));
		
		//THE ASSIGNMENT	
		for(int i =0 ; i < addBtns.size() ; i++) {
			String price_After_tax = itemsPrices.get(i).getText().replace("$", "");
				double price_After_tax_double = Double.parseDouble(price_After_tax); 
				double finalPrice = price_After_tax_double * .10 + price_After_tax_double;
				addBtns.get(i).click();
				
				  // Convert to string to extract the last digit
	            String final_price_string = Double.toString(finalPrice);
	            char last_digit_char = final_price_string.charAt(final_price_string.length() - 1);
	            int lastDigit = Integer.parseInt(String.valueOf(last_digit_char));

				if(lastDigit % 2 == 0) {
					System.out.println("The final price is even number and the value of this number is  --> " + finalPrice);
				} else {
					System.out.println("The final price is odd number and the value of this number is  --> " + finalPrice);
				}
		}
	}
	@AfterTest
	public void postTest() {
	}
}
