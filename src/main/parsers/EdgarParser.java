package main.parsers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class EdgarParser
{
	WebDriver driver;
	
	public EdgarParser() {
		this.driver = new HtmlUnitDriver();
	}
	
	public boolean connectToEdgar() {
		return false;
	}
	
	/**
	 * connectToGoogle
	 * 
	 * just testing out if selenium is set up and working correctly.
	 * 
	 * @return
	 */
	public boolean connectToGoogle() {
		// And now use this to visit Google
        this.driver.get("http://www.google.com");

        // Find the text input element by its name
        WebElement element = this.driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + this.driver.getTitle());
		
		return true;
	}
	
}
