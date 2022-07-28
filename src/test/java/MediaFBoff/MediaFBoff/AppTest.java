package MediaFBoff.MediaFBoff;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.Point;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */

public class AppTest {
	WebDriver driver;
	WebDriverWait wait;
	String randomString;
	HSSFWorkbook wb;
	HSSFRow row2;

	File file = new File("Userinput\\input.xls");
	JavascriptExecutor js = (JavascriptExecutor) driver;

	@Test
	public void pfirefox() {// firefox driver
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	@Test
	public void pIE() {// IE driver
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
	}

	@Test
	public void pEdge() {// Edge driver
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}

	@BeforeClass
	public void pchrome() {// chrome driver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void login() throws IOException {
		FileInputStream inputStream = new FileInputStream(file);
		wb = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = wb.getSheet("pitch");
		row2 = sheet.getRow(1);
		HSSFCell cell0 = row2.getCell(0);
		HSSFCell cell1 = row2.getCell(1);
		HSSFCell cell2 = row2.getCell(2);
		HSSFCell cell3 = row2.getCell(3);
		String link = cell0.getStringCellValue();
		String email = cell1.getStringCellValue();
		String pwd = cell2.getStringCellValue();
		String redirectUrl = cell3.getStringCellValue();
		System.out.println("" + redirectUrl);
		// wait = new WebDriverWait(driver, 120);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("" + link);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("" + email);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("" + pwd);
		driver.findElement(By.xpath("//input[@id='sign-in-btn']")).click();
		driver.get("" + redirectUrl);

	}

	@Test
	public void mediafiles() throws IOException, InterruptedException, AWTException {
		Thread.sleep(3000);
		int userCount = driver.findElements(By.xpath("//*[starts-with(@class,'section score')]/parent::div/parent::a")).size();
		int next = driver.findElements(By.xpath("//*[contains(@class,'page-item') and contains(text(),'⟩')]"))
				.size();
		
		
		if (((next == 0) ||  (next<0))) {
			System.out.println("In first if Statement");
			for (int j = 0; j < userCount; j++) {
				List<WebElement> usersLink = driver.findElements(By.xpath("//*[starts-with(@class,'section score')]/parent::div/parent::a"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", usersLink.get(j));
				String urlToOpenInNewTab = usersLink.get(j).getAttribute("href");
				js.executeScript("window.open('" + urlToOpenInNewTab + "');");
				Thread.sleep(2000);
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				int sectionCount = driver.findElements(By.xpath("//*[@class='toggle-section ']")).size();
				for (int k = 0; k < sectionCount; k++) {
					List<WebElement> sectionExpand = driver.findElements(By.xpath("//*[@class='toggle-section ']"));
					sectionExpand.get(k).click();
					driver.findElement(By.xpath("//*[@class='download']")).click();
					Robot robot = new Robot();
					Thread.sleep(2000);

					if (k == sectionExpand.size() - 1) {
						driver.close();
						driver.switchTo().window(tabs.get(0));
						break;
					}
				}

			}
		}

		if(next>0){

			for (int j = 0; j < userCount; j++) {
		                             
						List<WebElement> usersLink = driver.findElements(By.xpath("//*[starts-with(@class,'section score')]/parent::div/parent::a"));
						JavascriptExecutor js = (JavascriptExecutor) driver;
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", usersLink.get(j));
						String urlToOpenInNewTab = usersLink.get(j).getAttribute("href");
						js.executeScript("window.open('" + urlToOpenInNewTab + "');");
						Thread.sleep(2000);
						ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
						driver.switchTo().window(tabs.get(1));
						int sectionCount = driver.findElements(By.xpath("//*[@class='toggle-section ']")).size();
						for (int k = 0; k < sectionCount; k++) {
							List<WebElement> sectionExpand = driver.findElements(By.xpath("//*[@class='toggle-section ']"));
							sectionExpand.get(k).click();
							driver.findElement(By.xpath("//*[@class='download']")).click();

		                                      if(j==userCount-1){
		                                      if (k == sectionExpand.size() - 1){
		                                                driver.close();
								driver.switchTo().window(tabs.get(0));
		                                                driver.findElement(By.xpath("//*[contains(text(),'⟩')]")).click();
		                                                Thread.sleep(2000);
		                                                j=-1;
		                                                break;
		                         }
		                                      }
							if (k == sectionExpand.size() - 1) {
								driver.close();
								driver.switchTo().window(tabs.get(0));
								break;
							}

				}

					}
				}




	}

}
