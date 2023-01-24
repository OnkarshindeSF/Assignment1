package IntAssignment;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ex1 {
	static WebDriver driver ;

	public static void main(String[] args) throws InterruptedException {
		Ex1 ex1 = new Ex1();
		ex1.startUp();
		ex1.gettingOtpFromMailId( );
		ex1.selectProduct();

	}
	public void startUp () {
		String path = System.getProperty("user.dir");
		System.out.println(path);
		System.setProperty("webdriver.chrome.driver", path+ "\\Drivers\\chromedriver.exe");
		driver= new ChromeDriver();		
		driver.manage().window().maximize();
		driver.get("https://www.snapdeal.com");
		WebElement SignInbtn =driver.findElement(By.xpath("//span[text()='Sign In']"));
		SignInbtn.click();
		WebElement Loginbtn = driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']"));
		Loginbtn.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"loginIframe\"]")));
		WebElement username= driver.findElement(By.xpath("//*[@id=\"userName\"]"));
		username.click();
		username.sendKeys("onkarshinde123@yopmail.com");
		driver.findElement(By.xpath("//button[@id='checkUser']")).click();


	}

	public void gettingOtpFromMailId()  {
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://yopmail.com/wm");
		driver.navigate().refresh();
		WebElement eUsername = driver.findElement(By.cssSelector("[id='login']"));
		eUsername.click();
		eUsername.sendKeys("onkarshinde123");
		driver.findElement(By.xpath("//button[@class='md']")).click();
		driver.navigate().refresh();

		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"ifmail\"]")));
		String Otp = driver.findElement(By.xpath("(//main[@class='yscrollbar']//td[@align='center'])[5]/table/tbody/tr[3]/td/span")).getText();
		System.out.println(Otp);

		driver.switchTo().window(tabs.get(0));

		driver.switchTo().frame("loginIframe");
		WebDriverWait wt = new WebDriverWait(driver,30);
		WebElement otpfield =driver.findElement(By.xpath("//*[@id=\"loginOtpUC\"]/div[1]/input"));
		otpfield.click();
		otpfield.sendKeys(Otp);
		driver.findElement(By.xpath("//button[text()='continue'][@id='checkUser']"));


	}

	public void selectProduct() {
		Actions action = new Actions(driver);
		WebElement mensFasion = driver.findElement(By.xpath("(//span[@class='labelIcon'])[1]"));
		action.moveToElement(mensFasion).perform();
		WebElement sportShoes=driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]"));
		sportShoes.click();

		WebDriverWait wt = new WebDriverWait(driver,30);
		driver.findElement(By.xpath("(//div[@id=\"5764608196877142101\"]/div[2]/a/picture/img)[1]")).click();

		WebElement addToCart = driver.findElement(By.xpath("//span[text()='add to cart']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
				
		driver.findElement(By.xpath("//div[text()='View Cart']")).click();
		
		driver.findElement(By.xpath("//span[@class='item-price']")).getText();

	}


}
