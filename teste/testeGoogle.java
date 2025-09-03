package autoSelenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class testeGoogle {
	
	public static void main(String[] args) {

// Esse comando serve para quando o webdriver do navegador esteja local
//		System.setproperty("webdriver.chrome.driver", "/Users\MICRO\Downloads\selenium_drivers");		
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
				
	}
	
}
