package generalTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class EndtoEndTest 
{

	
	
	ChromeDriver driver ;
	
	// Make sure to change email value since "The specified email already exists" error will occur
	String email = "TestEmail123@Test.com" ;
	String pass = "TestPass@123" ;
	
	@BeforeTest
	public void SetupEnv()
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ziad\\Documents\\Automation\\Chrome Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));	
		
	}
	
	@Test (priority = 1)
	public void InvokeBrowser()
	{	
		
		driver.get("https://demo.nopcommerce.com/");

		
	}
	
	
	  @Test (priority = 2)
	  public void Register() throws InterruptedException 
	  {
	  
	  driver.findElement(By.linkText("Register")).click();
	  driver.findElement(By.id("gender-male")).click();
	  driver.findElement(By.id("FirstName")).sendKeys("Test First Name");
	  driver.findElement(By.id("LastName")).sendKeys("Test Last Name");
	  driver.findElement(By.name("DateOfBirthDay")).click();
	  
	  WebElement element = driver.findElement(By.name("DateOfBirthDay")); Select
	  select = new Select(element); select.selectByValue("28");
	  
	  WebElement element2 = driver.findElement(By.name("DateOfBirthMonth")); Select
	  select2 = new Select(element2); select2.selectByVisibleText("March");
	  
	  WebElement element3 = driver.findElement(By.name("DateOfBirthYear")); Select
	  select3 = new Select(element3); select3.selectByValue("1997");
	  
	  driver.findElement(By.id("Email")).sendKeys(email);
	  driver.findElement(By.id("Company")).sendKeys("Software Company");
	  driver.findElement(By.id("Newsletter")).click();
	  driver.findElement(By.id("Password")).sendKeys(pass);
	  driver.findElement(By.id("ConfirmPassword")).sendKeys(pass);
	  
	
	  driver.findElement(By.id("register-button")).click();
	  driver.findElement(By.linkText("CONTINUE")).click();
	  
	  driver.findElement(By.linkText("My account")).click(); Thread.sleep(2000);
	  driver.navigate().back();
	  
	  
	  }
	
	
	@Test (priority = 3)
	public void Shopping () throws InterruptedException
	{
		
		Actions act = new Actions(driver);
		
		WebElement electronics = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/a[1]"));
		act.moveToElement(electronics).perform();
		Thread.sleep(1000);
		WebElement phones = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));
		phones.click();
		
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[3]/div[2]/button[3]")).click();
		
		driver.findElement(By.linkText("Computers")).click();
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/h2[1]/a[1]")).click();
		driver.findElement(By.partialLinkText("Build your own")).click();
		
		driver.findElement(By.id("product_attribute_1")).click();
		
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'2.2 GHz Intel Pentium Dual-Core E2200')]")));
		driver.findElement(By.xpath("//option[contains(text(),'2.2 GHz Intel Pentium Dual-Core E2200')]")).click();
		
		driver.findElement(By.id("product_attribute_2")).click();
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'4GB [+$20.00]')]")));
		driver.findElement(By.xpath("//option[contains(text(),'4GB [+$20.00]')]")).click();
		
		driver.findElement(By.cssSelector("#product_attribute_3_7")).click();
		driver.findElement(By.id("product_attribute_4_9")).click();
		driver.findElement(By.id("product_attribute_5_11")).click();
		driver.findElement(By.id("product_attribute_5_12")).click();
		
		driver.findElement(By.id("add-to-cart-button-1")).click();
		
		driver.findElement(By.partialLinkText("Wishlist")).click();
		
		driver.findElement(By.xpath("//tbody/tr[1]/td[6]/input[1]")).click();
		driver.findElement(By.xpath("//tbody/tr[1]/td[6]/input[1]")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "2");
		driver.findElement(By.id("updatecart")).click();
		driver.findElement(By.xpath("//tbody/tr[1]/td[1]/input[1]")).click();
		driver.findElement(By.name("addtocartbutton")).click();
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");

		
		
	}
	
	@Test (priority = 4)
	public void Checkout() throws InterruptedException
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)");
		
		driver.findElement(By.id("checkout_attribute_1")).click();
		driver.findElement(By.id("checkout_attribute_1")).click();
			
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'Yes [+$10.00]')]")));
		driver.findElement(By.xpath("//option[contains(text(),'Yes [+$10.00]')]")).click();
		
		driver.findElement(By.id("termsofservice")).click();
		driver.findElement(By.id("checkout")).click();
		
		driver.findElement(By.id("BillingNewAddress_CountryId")).click();
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'United States')]")));
		driver.findElement(By.xpath("//option[contains(text(),'United States')]")).click();
		
		driver.findElement(By.id("BillingNewAddress_StateProvinceId")).click();
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'Nevada')]")));
		driver.findElement(By.xpath("//option[contains(text(),'Nevada')]")).click();
		
		
		driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Las Vegas");
		driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Random Address");
		driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("12345");
		driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("0123456789");
		
		driver.findElement(By.name("save")).click();
		
		driver.findElement(By.id("shippingoption_1")).click();
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[3]/div[2]/form[1]/div[2]/button[1]")));
		driver.findElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[3]/div[2]/form[1]/div[2]/button[1]")).click();
		
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]")));
		driver.findElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]")).click();
	
		
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[5]/div[2]/div[1]/button[1]")));
		driver.findElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[5]/div[2]/div[1]/button[1]")).click();
		
		
		jse.executeScript("window.scrollBy(0,250)");
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		
		driver.findElement(By.xpath("//a[contains(text(),'Click here for order details.')]")).click();
		
		Thread.sleep(2000);

	}
	
	@Test (priority = 5)
	public void MyAccount() throws InterruptedException
	{
		String newpass = "123@passtest";
		driver.findElement(By.linkText("My account")).click();
		driver.findElement(By.linkText("Addresses")).click();
		driver.findElement(By.linkText("Orders")).click();
		driver.findElement(By.linkText("Downloadable products")).click();
		driver.findElement(By.linkText("Back in stock subscriptions")).click();
		driver.findElement(By.linkText("Reward points")).click();
		
		//Change Password Test
		driver.findElement(By.linkText("Change password")).click();
		driver.findElement(By.id("OldPassword")).sendKeys(pass);
		driver.findElement(By.id("NewPassword")).sendKeys(newpass);
		driver.findElement(By.id("ConfirmNewPassword")).sendKeys(newpass);
		driver.findElement(By.xpath("//button[contains(text(),'Change password')]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.linkText("My product reviews")).click();
		
		
	}
	
	@AfterTest
	public void CloseBrowser()
	{
		
		
		
		
		driver.close();
		driver.quit();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
