package tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import Utils.ExcelUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.ElementOption;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BaseClass extends ExtentReportsDemo {
	
	AppiumDriver<MobileElement> driver;
	AndroidDriver<WebElement> driver2;
	
	DesiredCapabilities caps = new DesiredCapabilities();
	
	Logger logger = Logger.getLogger(BaseClass.class);
	
	Properties properties = new Properties();
	
	String ACCOUNT_SID = "AC71a00eda024403c44364a7c08dd485d6";
	String AUTH_TOKEN = "b6ae97305e5133b1fb45ee2cfabc5191";
	String LOG_FILE = "log4j.properties";
	
	
	public String getMessage(){
		
		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
				.filter(m -> m.getTo().equals("+12058832654")).map(Message::getBody).findFirst()
				.orElseThrow(IllegalStateException::new);
		
	}

	public Stream<Message> getMessages(){

		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
		return StreamSupport.stream(messages.spliterator(), false);

	}
	
	@BeforeTest
	public void setup() {
	
		
		try {
		
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy On Max");
		caps.setCapability(MobileCapabilityType.UDID, "42002e4e97e374d1");
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		//caps.setCapability(MobileCapabilityType.APP, "E://RFT apk//app-debug.apk");
		caps.setCapability("unicodeKeyboard", true);
		caps.setCapability("resetKeyboard", true);
		caps.setCapability("appPackage", "com.geotechinfo.realfantasy");
		caps.setCapability("appActivity", "com.geotechinfo.realfantasy.SplashActivity");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		
		driver = new AppiumDriver<MobileElement>(url, caps);
		driver2 = new AndroidDriver<WebElement>(url, caps);
		
		}catch(Exception exp){
			
			System.out.println("Cause is : "+exp.getCause());
			System.out.println("Message is : "+exp.getMessage());
			exp.printStackTrace();
			
		}
		
	}
	
	@DataProvider(name = "RegData")
	public Object[][] getData(){
			
			String excelPath = "E:\\JavaProjects\\AppiumDemoProject\\Excel\\Data.xlsx";
			
			Object data[][]= testData(excelPath, "Sheet1");
			
			return data;
			
		}
	
	@DataProvider(name = "RegData2")
	public Object[][] getData2(){
			
			String excelPath = "E:\\JavaProjects\\AppiumDemoProject\\Excel\\Data.xlsx";
			
			Object data[][]= testData(excelPath, "Sheet2");
			
			return data;
			
		}
	
	@DataProvider(name = "RegData3")
	public Object[][] getData3(){
			
			String excelPath = "E:\\JavaProjects\\AppiumDemoProject\\Excel\\Data.xlsx";
			
			Object data[][]= testData(excelPath, "Sheet3");
			
			return data;
			
		}
		
	public Object[][] testData(String excelPath, String sheetName){
			
			ExcelUtils excel = new ExcelUtils(excelPath,sheetName);
			
			int rowCount = excel.getRowCount();
			int colCount = excel.getColCount();
			
			Object data[][] = new Object[rowCount-1][colCount];
			
			for(int i=1; i<rowCount; i++){
				
				for(int j=0; j<colCount; j++){
					
					String cellData = excel.getCellDataString(i, j);
					
					//Double cellData = excel.getCellDataNumber(i, j);
					
					data[i-1][j]=cellData;
				}
					
			}
			
			return data;	
		}
	
	
	@AfterSuite
	public void teardown(){
		
		//driver.close();
		//driver.quit();
		
	}
}
