package org.apache.maven.plugins.REALM;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Example {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", Constants.ffDriverPath);
		WebDriver driver = new FirefoxDriver();
		
		//test================
		driver.get("http://www.duckduckgo.com");
		
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"t");// not working
		
		WebElement searchPane = driver.findElement(By.xpath("//form[@id='search_form_homepage']//input[@type='text']"));
		searchPane.sendKeys("Fb.com" + Keys.RETURN);
		Thread.sleep(5000);
		driver.findElement(By.partialLinkText("Facebook")).sendKeys(Keys.CONTROL, Keys.RETURN);
		Thread.sleep(5000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
		System.out.println("No of tabs: "+tabs.size());
		driver.navigate().to("http://www.apple.com");
		if (driver.getTitle().equals("Apple"))
			System.out.println("Pass");
		else
			System.out.println("FAIL");
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"1");
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(7000);
		
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.navigate().to("http://www.Flipkart.com");
		try {
			//TestLogin.Flipkart(driver);
			Thread.sleep(6000);
			WebElement flipSearch = driver.findElement(By.xpath("//div[@class='_3OO5Xc']//input[@type='text']"));
			flipSearch.sendKeys("Earpods",Keys.RETURN);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='_5THWM1']/div[6]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='E2-pcE _1q8tSL']/div[4]/div/div[2]")).click();
			Thread.sleep(4000);
			
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"2");
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs2.get(1));
			System.out.println(driver.getTitle());

		if(driver.getTitle().contains("Earpods")) {
			System.err.println("Error in switching Tabs");
		}else {
			WebElement price = driver.findElement(By.xpath("//div[@class='CEmiEU']//div[@class='_25b18c']//div[@class='_30jeq3 _16Jk6d']"));
			System.out.println("Price: "+ price.getText());}
			//tabs = (ArrayList<String>) driver.getWindowHandles();
			System.out.println("Now, No of tabs: "+tabs.size()+"\nTabs2:"+tabs2.size());
		} catch (Throwable e) {
			System.err.print("Error");
			e.printStackTrace();
		} finally {
			//driver.close();
			//driver.quit();
		}
		
		
	}
}
