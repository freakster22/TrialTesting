package org.apache.maven.plugins.REALM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class LoanCalculator {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.gecko.driver", Constants.ffDriverPath);
		
		WebDriver driver = new FirefoxDriver();
		driver.navigate().to("https://www.ia.ca");
		driver.manage().window().maximize();
		WebElement loanDropdown = driver.findElement(By.xpath("//div[@class='navbar-sub-main']/ul/li[4]//a[@class='dropdown-toggle']"));
		loanDropdown.click();
		if(loanDropdown.getAttribute("aria-expanded").equalsIgnoreCase("true")){
			driver.findElement(By.xpath("/html/body/div[2]/div/div/nav/div[1]/ul/li[4]/ul/li[2]/section[1]/ul/li[2]/a")).click();
			
			
			Thread.sleep(10000);
			if (driver.getTitle().contains("RRSP")) {
				/*WebElement slider = driver.findElement(By.xpath("/html/body/main/div[2]/div[8]/div/div[1]/div[1]/form/div[1]/div/div[2]/div/div[1]/div[2]"));
				Actions move = new Actions(driver);
				move.moveToElement(slider).click().dragAndDropBy(slider, 49, 51);
				*/
				driver.findElement(By.xpath("//*[@id='MontantPret']")).sendKeys("22000",Keys.TAB);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//div[@class='row separateur-haut margin-top-20']/div[1]//div[@class='selectric-wrapper selectric-below']")).click();
				
				
				Thread.sleep(3000);
				
				Select freq = new Select(driver.findElement(By.xpath("//div[@class='row separateur-haut margin-top-20']/div[3]/div/div[2]/b")));
				freq.selectByVisibleText("Biweekly");
				Thread.sleep(3000);
				
				driver.findElement(By.xpath("/html/body/main/div[2]/div[8]/div/div[1]/div[1]/form/div[3]/div/fieldset/div/div[1]/div[3]")).click();
				
				driver.findElement(By.xpath("//*[@id=\"btn_calculer\"]")).click();
				Thread.sleep(10000);
				
				String value = driver.findElement(By.xpath("//*[@id=\"paiement-resultats\"]")).getText();
				System.out.println("Loan Amt: "+value);
				
			}else System.out.println("Not the Calculator page!");
			
		}else { System.err.println("Error! Unable to access items");
		System.exit(0);
	}
		
	
		
	}

}
