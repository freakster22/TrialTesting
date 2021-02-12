package org.apache.maven.plugins.REALM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GmailLogin {

	static void getCount(WebDriver Driver) {
		WebElement count = Driver
				.findElement(By.xpath("//div[@class='J-J5-Ji amH J-JN-I']//span[@class='Dj']/span[2]"));
		String num = count.getText();
		System.out.println("No of mails: " + num);
	}
}
