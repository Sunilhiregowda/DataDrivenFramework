import java.sql.Driver;

import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenExample {

	

	@Test(dataProvider = "testdata")
	public void dataDriver(String firstname, String lastname, String PhoneNumber, String email, String address,
			String City, String State, String postalcode, String Country, String userName, String Password,
			String confirmPassword) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Bhargav\\Downloads\\drivers\\chromedriver_win32 (2)\\chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();

		driver.get("https://demo.guru99.com/test/newtours/register.php");
		driver.findElement(By.name("firstName")).sendKeys(firstname);
		driver.findElement(By.name("lastName")).sendKeys(lastname);
		driver.findElement(By.name("phone")).sendKeys(PhoneNumber);
		driver.findElement(By.name("userName")).sendKeys(email);
		driver.findElement(By.name("address1")).sendKeys(address);
		driver.findElement(By.name("city")).sendKeys(City);
		driver.findElement(By.name("state")).sendKeys(State);
		driver.findElement(By.name("postalCode")).sendKeys(postalcode);
		WebElement w = driver.findElement(By.name("country"));
		org.openqa.selenium.support.ui.Select s = new org.openqa.selenium.support.ui.Select(w);
		s.deselectByValue(Country);
		driver.findElement(By.name("email")).sendKeys(userName);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.name("confirmPassword")).sendKeys(confirmPassword);
		driver.findElement(By.name("submit")).click();
		WebElement registrationText = driver.findElement(By.xpath(
				"/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[2]/font/text()[1]"));
		String res = registrationText.getText();
		Assert.assertEquals(res, "Thank you for registering. You may now");

		System.out.println("Registration Successfull");

	}

	@DataProvider(name="testdata")
	public Object[][] testDataExample(){
	ReadExcelFile configuration = new ReadExcelFile("C:\\Users\\Bhargav\\Documents\\workspace-spring-tool-suite-4-4.14.1.RELEASE\\DataDrivenTesting\\FileUtils\\DataDrivenTestData.xlsx");
	int rows = configuration.getRowCount(0);
	Object[][] registration_credentials = new Object[rows][12];

	for(int i=0;i<rows;i++)
	{
	registration_credentials[i][0] = configuration.getData(0, i, 0);
	registration_credentials[i][1] = configuration.getData(0, i, 1);
	registration_credentials[i][2] = configuration.getData(0, i, 2);
	registration_credentials[i][3] = configuration.getData(0, i, 3);
	registration_credentials[i][4] = configuration.getData(0, i, 4);
	registration_credentials[i][5] = configuration.getData(0, i, 5);
	registration_credentials[i][6] = configuration.getData(0, i, 6);
	registration_credentials[i][7] = configuration.getData(0, i, 7);
	registration_credentials[i][8] = configuration.getData(0, i, 8);
	registration_credentials[i][9] = configuration.getData(0, i, 9);
	registration_credentials[i][10] = configuration.getData(0, i, 10);
	registration_credentials[i][11] = configuration.getData(0, i, 11);
	
	}
	return registration_credentials;
	}

}
