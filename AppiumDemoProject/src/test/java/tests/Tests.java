package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
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

public class Tests extends BaseClass {
	
	
	

	@Test(dataProvider = "RegData")
	public void loginlogout(String username, String password){
		
		try{
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
		existing_user.click();
		
		MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
		email_login.click();
		
		driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
		driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
		
		MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
		login_button.click();
		
		driver.navigate().back();
		
		Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();	
		
		MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
		
			if(close_help){
				
				close_help_icon.click();
				
				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
				
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
				
				MobileElement listitem = (MobileElement)driver.findElement(
						MobileBy.AndroidUIAutomator(
								"new UiScrollable(new UiSelector()).scrollIntoView("
										+ "new UiSelector().text(\"LOGOUT\"));"));
				
				listitem.click();
				
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
		
			}
						
		}catch(Exception e){
			
			MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
			settings_nav.click();
			
			MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
			
			MobileElement listitem = (MobileElement)driver.findElement(
					MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector()).scrollIntoView("
									+ "new UiSelector().text(\"LOGOUT\"));"));
			
			listitem.click();
			
			MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
			logout_confirm.click();
		}
		
	}
	
	/*@Test(dataProvider = "RegData")
	public void leaderboardcheck(String username, String password){
		
		try{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
			
		
			MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			existing_user.click();
		
			MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
			email_login.click();
		
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
		
			MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			login_button.click();
		
			driver.navigate().back();
		
			Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();
			MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
		
				if(close_help == true){
			
					close_help_icon.click();
			
					MobileElement leaderboard_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[4]/android.widget.ImageView");
					leaderboard_nav.click();
			
					MobileElement leaderboard_rank = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tvTPRank"));
					Assert.assertEquals(leaderboard_rank.getText(), "N/A");
			
					MobileElement leaderboard_history = driver.findElement(By.id("com.geotechinfo.realfantasy:id/credit_btn"));
					leaderboard_history.click();
			
					MobileElement history_rank = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.TextView[2]");
					Assert.assertEquals(history_rank.getText(), "Rank");
			
					MobileElement history_teamrank = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.ImageView");
					history_teamrank.click();
			
					MobileElement leaderboard_history_rank_team = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[2]");
					Assert.assertEquals(leaderboard_history_rank_team.getText(), "TEAM");
			
					MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
					page_back.click();
			
					MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
					settings_nav.click();
			
					MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
			
					MobileElement listitem = (MobileElement)driver.findElement(
							MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector()).scrollIntoView("
									+ "new UiSelector().text(\"LOGOUT\"));"));
			
					listitem.click();
			
					MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
					logout_confirm.click();
			
			}
			
		}
		catch( Exception e){
		
			MobileElement leaderboard_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[4]/android.widget.ImageView");
			leaderboard_nav.click();
		
			MobileElement leaderboard_rank = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tvTPRank"));
			Assert.assertEquals(leaderboard_rank.getText(), "N/A");
		
			MobileElement leaderboard_history = driver.findElement(By.id("com.geotechinfo.realfantasy:id/credit_btn"));
			leaderboard_history.click();
		
			MobileElement history_rank = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.TextView[2]");
			Assert.assertEquals(history_rank.getText(), "Rank");
		
			MobileElement history_teamrank = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.RelativeLayout/android.widget.ImageView");
			history_teamrank.click();
		
			MobileElement leaderboard_history_rank_team = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[2]");
			Assert.assertEquals(leaderboard_history_rank_team.getText(), "TEAM");
		
			MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
			page_back.click();
		
			MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
			settings_nav.click();
		
			MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
		
			MobileElement listitem = (MobileElement)driver.findElement(
				MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector()).scrollIntoView("
								+ "new UiSelector().text(\"LOGOUT\"));"));
		
			listitem.click();
		
			MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
			logout_confirm.click();
		}
		
	}*/
	
	/*@Test(dataProvider = "RegData")
	public void fixturecheck(String username, String password){
	
		try{
		
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
			MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			existing_user.click();
		
			MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
			email_login.click();
		
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
		
			MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			login_button.click();
		
			//driver.navigate().back();
		 
			Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();
			MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
		
				if(close_help == true){
			
					close_help_icon.click();
		
					MobileElement fixture_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/fixtures_tv"));
					fixture_tab.click();
		
					MobileElement notstarted_match = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView");
					notstarted_match.click();
		
					MobileElement teamstanding_link = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tvViewPrize"));
					Assert.assertEquals(teamstanding_link.getText(), "TEAM STANDINGS");
		
					MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
					page_back.click();
		
					MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
					settings_nav.click();
		
					MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
		
					MobileElement listitem = (MobileElement)driver.findElement(
							MobileBy.AndroidUIAutomator(
									"new UiScrollable(new UiSelector()).scrollIntoView("
											+ "new UiSelector().text(\"LOGOUT\"));"));
		
					listitem.click();
		
					MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
					logout_confirm.click();
					
				}
		
		}
		
		catch(Exception e){
		
					MobileElement fixture_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/fixtures_tv"));
					fixture_tab.click();
		
					MobileElement notstarted_match = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView");
					notstarted_match.click();
		
					MobileElement teamstanding_link = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tvViewPrize"));
					Assert.assertEquals(teamstanding_link.getText(), "TEAM STANDINGS");
		
					MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
					page_back.click();
		
					MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
					settings_nav.click();
		
					MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
		
					MobileElement listitem = (MobileElement)driver.findElement(
							MobileBy.AndroidUIAutomator(
									"new UiScrollable(new UiSelector()).scrollIntoView("
											+ "new UiSelector().text(\"LOGOUT\"));"));
		
					listitem.click();
		
					MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
					logout_confirm.click();
		
		}
	}*/
	
	
	
	/*@Test(dataProvider = "RegData")
	public void teamstandingcheck(String username, String password){
	
		try{
		
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
		existing_user.click();
		
		MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
		email_login.click();
		
		driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
		driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
		
		MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
		login_button.click();
		
		//driver.navigate().back();
		 
		Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();
		MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
		
			if(close_help == true){
			
				close_help_icon.click(); 
		
				MobileElement fixture_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/fixtures_tv"));
				fixture_tab.click();
		
				MobileElement teamstanding_link = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tvViewPrize"));
				teamstanding_link.click();
		
				MobileElement teamstanding_pts = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.ExpandableListView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView[9]");
				Assert.assertEquals(teamstanding_pts.getText(), "PTS");
		
				MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				page_back.click();
		
				MobileElement left_gs = driver.findElement(By.id("com.geotechinfo.realfantasy:id/left_gs"));
				left_gs.click();
		
				MobileElement page_back2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				page_back2.click();
		
				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
		
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
		
				MobileElement listitem = (MobileElement)driver.findElement(
				MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector()).scrollIntoView("
								+ "new UiSelector().text(\"LOGOUT\"));"));
		
				listitem.click();
		
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
				
			}
		
		}
		
		catch(Exception e){
		
				MobileElement fixture_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/fixtures_tv"));
				fixture_tab.click();
		
				MobileElement teamstanding_link = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tvViewPrize"));
				teamstanding_link.click();
		
				MobileElement teamstanding_pts = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.ExpandableListView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView[9]");
				Assert.assertEquals(teamstanding_pts.getText(), "PTS");
		
				MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				page_back.click();
		
				MobileElement left_gs = driver.findElement(By.id("com.geotechinfo.realfantasy:id/left_gs"));
				left_gs.click();
		
				MobileElement page_back2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				page_back2.click();
		
				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
		
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
		
				MobileElement listitem = (MobileElement)driver.findElement(
				MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector()).scrollIntoView("
								+ "new UiSelector().text(\"LOGOUT\"));"));
		
				listitem.click();
		
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
		
		}
		
	}*/
	
	/*@Test(dataProvider = "RegData")
	public void teaminfocheck(String username, String password){
		
		try{
		
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
		existing_user.click();
		
		MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
		email_login.click();
		
		driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
		driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
		
		MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
		login_button.click();
		
		//driver.navigate().back();
		
		Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();
		MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
	
			if(close_help == true){
		
				close_help_icon.click();
		
				MobileElement teaminfo_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/teamInfo_tv"));
				teaminfo_button.click();
		
				MobileElement teaminfo_title = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_player_details"));
				Assert.assertEquals(teaminfo_title.getText(), "TEAM DETAILS");
		
				MobileElement teaminfo_playerlist = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.ImageView");
				teaminfo_playerlist.click();
		
				//MobileElement teaminfo_playerdetails = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_player_details"));
				MobileElement selected_by = driver.findElement(By.id("com.geotechinfo.realfantasy:id/selected_by_heading"));
				Assert.assertEquals(selected_by.getText(), "SELECTED BY");
		
				MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				page_back.click();
		
				MobileElement left_gs = driver.findElement(By.id("com.geotechinfo.realfantasy:id/left_gs"));
				left_gs.click();
		
				MobileElement page_back2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				page_back2.click();
		
				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
		
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
				
				MobileElement listitem = (MobileElement)driver.findElement(
						MobileBy.AndroidUIAutomator(
								"new UiScrollable(new UiSelector()).scrollIntoView("
										+ "new UiSelector().text(\"LOGOUT\"));"));
		
				listitem.click();
		
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
				
			}
		
		}
		
		catch(Exception e){
			
			MobileElement teaminfo_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/teamInfo_tv"));
			teaminfo_button.click();
	
			MobileElement teaminfo_title = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_player_details"));
			Assert.assertEquals(teaminfo_title.getText(), "TEAM DETAILS");
	
			MobileElement teaminfo_playerlist = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.ImageView");
			teaminfo_playerlist.click();
	
			//MobileElement teaminfo_playerdetails = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_player_details"));
			MobileElement selected_by = driver.findElement(By.id("com.geotechinfo.realfantasy:id/selected_by_heading"));
			Assert.assertEquals(selected_by.getText(), "SELECTED BY");
	
			MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
			page_back.click();
	
			MobileElement left_gs = driver.findElement(By.id("com.geotechinfo.realfantasy:id/left_gs"));
			left_gs.click();
	
			MobileElement page_back2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
			page_back2.click();
	
			MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
			settings_nav.click();
	
			MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
			
			MobileElement listitem = (MobileElement)driver.findElement(
					MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector()).scrollIntoView("
									+ "new UiSelector().text(\"LOGOUT\"));"));
	
			listitem.click();
	
			MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
			logout_confirm.click();
			
		 }
		
	 }*/
	
	/*@Test(dataProvider = "RegData")
	public void playerdetailscurrent(String username, String password){
		
		try{
			
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
				MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
				existing_user.click();
		
				MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
				email_login.click();
		
				driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
				driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
		
				MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
				login_button.click();
		
				driver.navigate().back();
		
				Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();	
		
				MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
			
				if(close_help){
				
					close_help_icon.click();
				
					MobileElement fixture_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/fixtures_tv"));
					fixture_tab.click();
					
					MobileElement current_matchday = driver.findElement(By.id("com.geotechinfo.realfantasy:id/gs_title"));
					String current_matchday_text = current_matchday.getText();
					
					MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
					page_back.click();
					
					MobileElement player_details_to_check = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout[1]/android.widget.ImageView");
					player_details_to_check.click();
					
					MobileElement current_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/transfer_btn"));
					current_tab.click();
					
					MobileElement matchday_text = driver.findElement(By.id("com.geotechinfo.realfantasy:id/gs_title"));
					
					Assert.assertEquals(matchday_text.getText(), current_matchday_text);
					
					MobileElement page_back2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
					page_back2.click();
					
					MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
					settings_nav.click();
					
					MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
					
					MobileElement listitem = (MobileElement)driver.findElement(
							MobileBy.AndroidUIAutomator(
									"new UiScrollable(new UiSelector()).scrollIntoView("
											+ "new UiSelector().text(\"LOGOUT\"));"));
					
					listitem.click();
					
					MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
					logout_confirm.click();
				
			}
			
		}
		
			catch(Exception e){
			
			
				MobileElement fixture_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/fixtures_tv"));
				fixture_tab.click();
				
				MobileElement current_matchday = driver.findElement(By.id("com.geotechinfo.realfantasy:id/gs_title"));
				String current_matchday_text = current_matchday.getText();
				
				MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				page_back.click();
				
				MobileElement player_details_to_check = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player_details_to_check.click();
				
				MobileElement current_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/transfer_btn"));
				current_tab.click();
				
				MobileElement matchday_text = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_leagues"));
				
				Assert.assertEquals(matchday_text.getText(), current_matchday_text);
				
				MobileElement page_back2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				page_back2.click();
				
				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
				
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
				
				MobileElement listitem = (MobileElement)driver.findElement(
						MobileBy.AndroidUIAutomator(
								"new UiScrollable(new UiSelector()).scrollIntoView("
										+ "new UiSelector().text(\"LOGOUT\"));"));
				
				listitem.click();
				
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
			
		}
		
	}*/
		
	/*@Test(dataProvider = "RegData")
	public void dashboardplayerswap(String username, String password){
		
		try{
		
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
			MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			existing_user.click();
		
			MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
			email_login.click();
		
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
		
			MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			login_button.click();
		
			driver.navigate().back();
		
			Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();	
		
			MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
		
			if(close_help){
			
				close_help_icon.click();
				
				//MobileElement next_md = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_gs"));
				//next_md.click();
		
				//Drag and drop
		
				MobileElement source = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[9]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				MobileElement target = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[8]/android.widget.RelativeLayout[1]/android.widget.ImageView");

				TouchAction action = new TouchAction(driver);
		
				action.longPress(ElementOption.element(source)).moveTo(ElementOption.element(target)).release().perform();
		
				MobileElement confirm_swap = driver.findElement(By.id("com.geotechinfo.realfantasy:id/confirm_transfer"));
				confirm_swap.click();
				
				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
		
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
		
				MobileElement listitem = (MobileElement)driver.findElement(
						MobileBy.AndroidUIAutomator(
								"new UiScrollable(new UiSelector()).scrollIntoView("
								+ "new UiSelector().text(\"LOGOUT\"));"));
		
				listitem.click();
		
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
			}
		}
		
		catch(Exception e){
			
			MobileElement source = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[9]/android.widget.RelativeLayout[1]/android.widget.ImageView");
			MobileElement target = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[8]/android.widget.RelativeLayout[1]/android.widget.ImageView");

			TouchAction action = new TouchAction(driver);
	
			action.longPress(ElementOption.element(source)).moveTo(ElementOption.element(target)).release().perform();
	
			MobileElement confirm_swap = driver.findElement(By.id("com.geotechinfo.realfantasy:id/confirm_transfer"));
			confirm_swap.click();
			
			MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
			settings_nav.click();
	
			MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
	
			MobileElement listitem = (MobileElement)driver.findElement(
					MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector()).scrollIntoView("
							+ "new UiSelector().text(\"LOGOUT\"));"));
	
			listitem.click();
	
			MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
			logout_confirm.click();
		}
	}*/
		
		
		/*@Test(dataProvider = "RegData")
		public void dashboardleaderformationchange(String username, String password){
			
			try{
				
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
				existing_user.click();
			
				MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
				email_login.click();
			
				driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
				driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
			
				MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
				login_button.click();
			
				driver.navigate().back();
			
				Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();	
			
				MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
			
				if(close_help){
				
					close_help_icon.click();
					
					//MobileElement next_md = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_gs"));
					//next_md.click();
				
				TouchAction action = new TouchAction(driver);
				
				//Leader change
		
				MobileElement leader_change = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[11]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				action.longPress(ElementOption.element(leader_change)).release().perform();
		
				MobileElement select_leader = driver.findElement(By.id("com.geotechinfo.realfantasy:id/indicator_layout"));
				select_leader.click();
		
				//Formation change
		
				MobileElement formation_menu = driver.findElement(By.id("com.geotechinfo.realfantasy:id/formations_tv"));
				formation_menu.click();
		
				MobileElement formation_selection = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_next"));
				formation_selection.click();
		
				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
		
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
		
				MobileElement listitem = (MobileElement)driver.findElement(
						MobileBy.AndroidUIAutomator(
								"new UiScrollable(new UiSelector()).scrollIntoView("
								+ "new UiSelector().text(\"LOGOUT\"));"));
		
				listitem.click();
		
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
				
			}
		
		}
		
		catch(Exception e){
			
				TouchAction action = new TouchAction(driver);
			
				//Leader change
			
				MobileElement leader_change = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[11]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				action.longPress(ElementOption.element(leader_change)).perform();
			
				MobileElement select_leader = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.RelativeLayout");
				select_leader.click();
			
				//Formation change
			
				MobileElement formation_menu = driver.findElement(By.id("com.geotechinfo.realfantasy:id/formations_tv"));
				formation_menu.click();
			
				MobileElement formation_selection = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_next"));
				formation_selection.click();
			
				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
			
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
			
				MobileElement listitem = (MobileElement)driver.findElement(
						MobileBy.AndroidUIAutomator(
								"new UiScrollable(new UiSelector()).scrollIntoView("
									+ "new UiSelector().text(\"LOGOUT\"));"));
			
				listitem.click();
			
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
			
		}
		
	}*/
		
		/*@Test(dataProvider = "RegData")
		public void playertransfer(String username, String password){
			
			try{
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
				MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
				existing_user.click();
			
				MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
				email_login.click();
			
				driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
				driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
			
				MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
				login_button.click();
			
				//driver.navigate().back();
			
				Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();	
			
				MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
			
				if(close_help){
				
					close_help_icon.click();
					
					//MobileElement next_md = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_gs"));
					//next_md.click();
					
					//String transfers_left = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_transfer_players")).getText();
					
					MobileElement transfers_left = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_transfer_players"));
					transfers_left.click();
					
					String free_transfer_left = driver.findElement(By.id("com.geotechinfo.realfantasy:id/ft_value")).getText();
					int free_transfer_left_number = Integer.parseInt(free_transfer_left);
					
					String paid_transfer_left = driver.findElement(By.id("com.geotechinfo.realfantasy:id/gs_pt_value")).getText();
					int paid_transfer_left_number = Integer.parseInt(paid_transfer_left);
					
					driver.navigate().back();
					
					//Player transfer
					
					if(free_transfer_left_number == 0 && paid_transfer_left_number == 0){
						
						//MobileElement all_players_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/ivAllPlayer"));
						//all_players_icon.click();
						
						//MobileElement app_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
						//app_back.click();
						
						MobileElement source = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[9]/android.widget.RelativeLayout[1]/android.widget.ImageView");
						MobileElement target = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[3]");

						TouchAction action = new TouchAction(driver);
				
						action.longPress(ElementOption.element(source)).moveTo(ElementOption.element(target)).release().perform();
						
						MobileElement buy_packs = driver.findElement(By.id("com.geotechinfo.realfantasy:id/buy_transfer_txt_view"));
						buy_packs.click();
						
						MobileElement store_transfers = driver.findElement(By.id("com.geotechinfo.realfantasy:id/transfer_btn"));
						Assert.assertEquals(store_transfers.getText(), "TRANSFERS");
						
						MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
						settings_nav.click();
				
						MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
				
						MobileElement listitem = (MobileElement)driver.findElement(
								MobileBy.AndroidUIAutomator(
										"new UiScrollable(new UiSelector()).scrollIntoView("
										+ "new UiSelector().text(\"LOGOUT\"));"));
				
						listitem.click();
				
						MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
						logout_confirm.click();
						
						System.out.println("No transfers available");
						
					}
					else{
						
						MobileElement all_players_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/ivAllPlayer"));
						all_players_icon.click();
						
						MobileElement app_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
						app_back.click();
						
						MobileElement player_to_transfer = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout[1]/android.widget.ImageView");
						player_to_transfer.click();
				
						//MobileElement transfer_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/teamInfo_tv"));
						
						MobileElement transfer_button = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.TextView[1]");
						transfer_button.click();
				
						MobileElement price_sort = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
						price_sort.click();
				
						MobileElement new_player = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout");
						new_player.click();
				
						MobileElement confirm_new_player = driver.findElement(By.id("com.geotechinfo.realfantasy:id/confirm_transfer"));
						confirm_new_player.click();
						
						//MobileElement app_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
						//app_back.click();
						
						//MobileElement player_current = driver.findElement(By.id("com.geotechinfo.realfantasy:id/transfer_btn"));
						//player_current.click();
						
						//MobileElement app_back2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
						//app_back2.click();
				
						MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
						settings_nav.click();
				
						MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
				
						MobileElement listitem = (MobileElement)driver.findElement(
								MobileBy.AndroidUIAutomator(
										"new UiScrollable(new UiSelector()).scrollIntoView("
										+ "new UiSelector().text(\"LOGOUT\"));"));
				
						listitem.click();
				
						MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
						logout_confirm.click();
						
					}
			
				}
				
			}
			
			catch(Exception e){
				
				//Player transfer
				
				MobileElement transfers_left = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_transfer_players"));
				transfers_left.click();
				
				String free_transfer_left = driver.findElement(By.id("com.geotechinfo.realfantasy:id/ft_value")).getText();
				int free_transfer_left_number = Integer.parseInt(free_transfer_left);
				
				String paid_transfer_left = driver.findElement(By.id("com.geotechinfo.realfantasy:id/gs_pt_value")).getText();
				int paid_transfer_left_number = Integer.parseInt(paid_transfer_left);
				
				driver.navigate().back();
				
				if(free_transfer_left_number == 0 && paid_transfer_left_number == 0){
					
					//MobileElement all_players_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/ivAllPlayer"));
					//all_players_icon.click();
					
					//MobileElement app_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
					//app_back.click();
					
					MobileElement source = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[9]/android.widget.RelativeLayout[1]/android.widget.ImageView");
					MobileElement target = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[3]");

					TouchAction action = new TouchAction(driver);
			
					action.longPress(ElementOption.element(source)).moveTo(ElementOption.element(target)).release().perform();
					
					MobileElement buy_packs = driver.findElement(By.id("com.geotechinfo.realfantasy:id/buy_transfer_txt_view"));
					buy_packs.click();
					
					MobileElement store_transfers = driver.findElement(By.id("com.geotechinfo.realfantasy:id/transfer_btn"));
					Assert.assertEquals(store_transfers.getText(), "TRANSFERS");
					
					MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
					settings_nav.click();
			
					MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
			
					MobileElement listitem = (MobileElement)driver.findElement(
							MobileBy.AndroidUIAutomator(
									"new UiScrollable(new UiSelector()).scrollIntoView("
									+ "new UiSelector().text(\"LOGOUT\"));"));
			
					listitem.click();
			
					MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
					logout_confirm.click();
					
					System.out.println("No transfers available");
					
				}
				else{
					
				MobileElement all_players_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/ivAllPlayer"));
				all_players_icon.click();
					
				MobileElement app_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				app_back.click();
				
				MobileElement player_to_transfer = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player_to_transfer.click();
		
				MobileElement transfer_button = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.TextView[1]");
				transfer_button.click();
		
				MobileElement price_sort = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort.click();
		
				MobileElement new_player = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout");
				new_player.click();
		
				MobileElement confirm_new_player = driver.findElement(By.id("com.geotechinfo.realfantasy:id/confirm_transfer"));
				confirm_new_player.click();
		
				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
		
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
		
				MobileElement listitem = (MobileElement)driver.findElement(
						MobileBy.AndroidUIAutomator(
								"new UiScrollable(new UiSelector()).scrollIntoView("
								+ "new UiSelector().text(\"LOGOUT\"));"));
		
				listitem.click();
		
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
				
				}
			}
		}*/
		
		//New user registration
		
		/*@Test(dataProvider = "RegData2",priority = 1)
		public void registerAppwithemail(String username, String password, String confirm_password, String team_name) throws Exception{
			
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
			MobileElement email_register = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
			email_register.click();
			
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass_retype")).sendKeys(confirm_password);
			
			MobileElement tandc = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tc_cb"));
			tandc.click();
			
			//driver.navigate().back();
			
			MobileElement verify_email = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			verify_email.click();
			
			MobileElement register_verify_header = driver.findElement(By.id("com.geotechinfo.realfantasy:id/register_verify_header"));
			Assert.assertEquals(register_verify_header.getText(), "VERIFY YOUR EMAIL");
			
			driver2.openNotifications();
			
			Thread.sleep(10000);
			
			WebElement mail_expand = driver2.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.view.ViewGroup");
			mail_expand.click();
			
			WebElement email_otp = driver2.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView");
			//email_otp.click();
			
			String otp = email_otp.getText();
			
			String otpnumber = otp.replaceAll("[^-?0-9]+", " ");
			
			driver.navigate().back();
			
			Thread.sleep(2000);
			
			MobileElement verify_email_otp = driver.findElement(By.id("com.geotechinfo.realfantasy:id/register_otp_et"));
			verify_email_otp.sendKeys(otpnumber);
			
			MobileElement verify_email_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/signup_btn"));
			verify_email_button.click();
			
			MobileElement congratulation_page = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_btn"));
			congratulation_page.click();
			
			MobileElement after_registration_help_skip = driver.findElement(By.id("com.geotechinfo.realfantasy:id/skip"));
			after_registration_help_skip.click();
			
			MobileElement location_permission_allow = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
			location_permission_allow.click();
			
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_code")).sendKeys("700050");
			
			MobileElement confirm_prizezone = driver.findElement(By.id("com.geotechinfo.realfantasy:id/signInTxtView"));
			confirm_prizezone.click();
			
			Thread.sleep(3000);
			
			MobileElement choose_league_help = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]");
			choose_league_help.click();
			
			MobileElement choose_league = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.ImageView");
			choose_league.click();
			
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_team_name")).sendKeys(team_name);
			
			MobileElement create_team = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_next"));
			create_team.click();
			
			Thread.sleep(3000);
			
			MobileElement select_formation_help = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]");
			select_formation_help.click();
			
			MobileElement select_formation = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_next"));
			select_formation.click();
			
			Thread.sleep(3000);
			
			MobileElement select_players_help = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]");
			select_players_help.click();
			
			MobileElement leagues = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_leagues"));
			leagues.click();
			
			Thread.sleep(3000);
			
			MobileElement choose_league_help2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]");
			choose_league_help2.click();
			
			MobileElement league_page_options = driver.findElement(By.id("com.geotechinfo.realfantasy:id/option_header"));
			league_page_options.click();
			
			MobileElement logout_option = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tvLogout"));
			logout_option.click();
			
			MobileElement logout_confirmation = driver.findElement(By.id("android:id/button1"));
			logout_confirmation.click();
			
			/*MobileElement autofill = driver.findElement(By.id("com.geotechinfo.realfantasy:id/teamInfo_tv"));
			autofill.click();
			
			//MobileElement reset = driver.findElement(By.id("com.geotechinfo.realfantasy:id/teamInfo_tv"));
			//reset.click();
			
			//MobileElement autofill2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/teamInfo_tv"));
			//autofill2.click();
			
			WebDriverWait wait = new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("com.geotechinfo.realfantasy:id/tv_next")));
			
			MobileElement confirm_team_selection = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_next"));
			confirm_team_selection.click();
			
			//Thread.sleep(3000);
			
			MobileElement select_leader_help = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]");
			select_leader_help.click();
			
			MobileElement select_leader = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout");
			select_leader.click();
			
			MobileElement confirm_team = driver.findElement(By.id("com.geotechinfo.realfantasy:id/captain_btn"));
			confirm_team.click();
			
			//Thread.sleep(3000);
			
			MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
			close_help_icon.click();
			
			/*Thread.sleep(3000);
			
			//player transfer
			
			MobileElement all_players_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/ivAllPlayer"));
			all_players_icon.click();
			
			Thread.sleep(3000);
			
			MobileElement app_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
			app_back.click();
			
			Thread.sleep(5000);

			MobileElement source = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView");
			MobileElement target = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[3]");

			TouchAction action = new TouchAction(driver);
							
			action.longPress(ElementOption.element(source)).moveTo(ElementOption.element(target)).release().perform();
			
			Thread.sleep(2000);

			MobileElement price_sort = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
			price_sort.click();
			
			Thread.sleep(2000);

			MobileElement new_player = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout");
			new_player.click();
			
			Thread.sleep(2000);

			MobileElement confirm_new_player = driver.findElement(By.id("com.geotechinfo.realfantasy:id/confirm_transfer"));
			confirm_new_player.click();
			
			Thread.sleep(3000);
			
			//player position swap
			
			MobileElement source2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[9]/android.widget.RelativeLayout[1]/android.widget.ImageView");
			MobileElement target2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[8]/android.widget.RelativeLayout[1]/android.widget.ImageView");

			TouchAction action2 = new TouchAction(driver);
	
			action2.longPress(ElementOption.element(source2)).moveTo(ElementOption.element(target2)).release().perform();
			
			Thread.sleep(2000);
	
			MobileElement confirm_swap = driver.findElement(By.id("com.geotechinfo.realfantasy:id/confirm_transfer"));
			confirm_swap.click();
			
			Thread.sleep(3000);
			
			//change leader
			
			TouchAction action3 = new TouchAction(driver);
	
			MobileElement leader_change = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView");
			action3.longPress(ElementOption.element(leader_change)).release().perform();
			
			Thread.sleep(2000);
	
			MobileElement select_leader2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/indicator_layout"));
			select_leader2.click();
			
			//Thread.sleep(3000);

			//Logout
			
			MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
			settings_nav.click();
	
			MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
	
			MobileElement listitem = (MobileElement)driver.findElement(
					MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector()).scrollIntoView("
							+ "new UiSelector().text(\"LOGOUT\"));"));
	
			listitem.click();
	
			MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
			logout_confirm.click();
			
		}*/
		
		/*@Test
		public void registerAppwithmobile() throws Exception{
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		MobileElement mobile_register = driver.findElement(By.id("com.geotechinfo.realfantasy:id/mobile_layout"));
		mobile_register.click();
		
		MobileElement Country_code = driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_country_code"));
		Country_code.click();
		
		driver.findElement(By.id("com.geotechinfo.realfantasy:id/search_view")).sendKeys("UNITED STATES");
		
		MobileElement Country_code_select = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_country_name"));
		Country_code_select.click();
		
		driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_mobile")).sendKeys("2058832654");
		driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys("hello@123");
		driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass_retype")).sendKeys("hello@123");
		
		MobileElement tandc = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tc_cb"));
		tandc.click();
		
		MobileElement verify_mobile = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
		verify_mobile.click();
		
		//driver2.startActivity(new Activity("com.samsung.android.messaging","com.android.mms.ui.ComposeMessageMms"));
		
		//System.out.println("Current activity package name is: "+ driver2.getCurrentPackage());
		
		//driver2.startActivity(new Activity("com.geotechinfo.realfantasy","com.geotechinfo.realfantasy.activities.MainActivity"));
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		String smsBody = getMessage();
		logger.debug("Message is here: "+smsBody);
		String OTPNumber = smsBody.replaceAll("[^-?0-9]+", " ");
		System.out.println(OTPNumber);
				
		}*/
		
		/*@Test(dataProvider = "RegData3", priority = 2)
		public void loginappafterRegistrationwithAutofill(String username, String password) throws InterruptedException{
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			existing_user.click();
		
			MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
			email_login.click();
		
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
		
			MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			login_button.click();
			
	
			
			MobileElement select_players_help = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]");
			select_players_help.click();
			
			MobileElement autofill = driver.findElement(By.id("com.geotechinfo.realfantasy:id/teamInfo_tv"));
			autofill.click();
			
			WebDriverWait wait = new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("com.geotechinfo.realfantasy:id/tv_next")));
			
			MobileElement confirm_team_selection = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_next"));
			confirm_team_selection.click();
			
			//Thread.sleep(3000);
			
			MobileElement select_leader_help = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]");
			select_leader_help.click();
			
			MobileElement select_leader = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout");
			select_leader.click();
			
			MobileElement confirm_team = driver.findElement(By.id("com.geotechinfo.realfantasy:id/captain_btn"));
			confirm_team.click();
			
			//Thread.sleep(3000);
			
			MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
			close_help_icon.click();
			
			MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
			settings_nav.click();
	
			MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
	
			MobileElement listitem = (MobileElement)driver.findElement(
					MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector()).scrollIntoView("
							+ "new UiSelector().text(\"LOGOUT\"));"));
	
			listitem.click();
	
			MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
			logout_confirm.click();
		}*/
	
	
	/*@Test(dataProvider = "RegData3", priority = 3)
	public void newusertransfer(String username, String password) throws InterruptedException{
		
		try{
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
			MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			existing_user.click();
	
			MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
			email_login.click();
	
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
	
			//driver.navigate().back();
		
			MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			login_button.click();
			
			Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();	
			
			MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
		
			if(close_help){
			
				close_help_icon.click();
				
				MobileElement all_players_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/ivAllPlayer"));
				all_players_icon.click();
				
				MobileElement app_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				app_back.click();

				MobileElement source = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[9]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				MobileElement target = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[3]");

				TouchAction action = new TouchAction(driver);
								
				action.longPress(ElementOption.element(source)).moveTo(ElementOption.element(target)).release().perform();

				MobileElement price_sort = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort.click();

				MobileElement new_player = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout");
				new_player.click();

				MobileElement confirm_new_player = driver.findElement(By.id("com.geotechinfo.realfantasy:id/confirm_transfer"));
				confirm_new_player.click();

				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
						
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
						
				MobileElement listitem = (MobileElement)driver.findElement(
					MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector()).scrollIntoView("
						+ "new UiSelector().text(\"LOGOUT\"));"));
						
				listitem.click();
						
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();

				
			}	
		}
		
		catch(Exception e){
			
				MobileElement all_players_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/ivAllPlayer"));
				all_players_icon.click();
				
				MobileElement app_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				app_back.click();

				MobileElement source = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[9]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				MobileElement target = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[3]");

				TouchAction action = new TouchAction(driver);
							
				action.longPress(ElementOption.element(source)).moveTo(ElementOption.element(target)).release().perform();

				MobileElement price_sort = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort.click();

				MobileElement new_player = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout");
				new_player.click();

				MobileElement confirm_new_player = driver.findElement(By.id("com.geotechinfo.realfantasy:id/confirm_transfer"));
				confirm_new_player.click();

				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
					
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
					
				MobileElement listitem = (MobileElement)driver.findElement(
						MobileBy.AndroidUIAutomator(
								"new UiScrollable(new UiSelector()).scrollIntoView("
										+ "new UiSelector().text(\"LOGOUT\"));"));
					
				listitem.click();
					
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
			
		}
		
	}*/
	
	/*@Test(dataProvider = "RegData3", priority = 4)
	public void newuserplayerswap(String username, String password) throws InterruptedException{
		
		try{
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			existing_user.click();
		
			MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
			email_login.click();
		
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
		
			MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			login_button.click();
		
			driver.navigate().back();
		
			Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();	
		
			MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
		
			if(close_help){
			
				close_help_icon.click();
		
				//Drag and drop
		
				MobileElement source = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[9]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				MobileElement target = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[8]/android.widget.RelativeLayout[1]/android.widget.ImageView");

				TouchAction action = new TouchAction(driver);
		
				action.longPress(ElementOption.element(source)).moveTo(ElementOption.element(target)).release().perform();
		
				MobileElement confirm_swap = driver.findElement(By.id("com.geotechinfo.realfantasy:id/confirm_transfer"));
				confirm_swap.click();
				
				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
		
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
		
				MobileElement listitem = (MobileElement)driver.findElement(
						MobileBy.AndroidUIAutomator(
								"new UiScrollable(new UiSelector()).scrollIntoView("
								+ "new UiSelector().text(\"LOGOUT\"));"));
		
				listitem.click();
		
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
				
			}	
		}
		
		catch(Exception e){
			
			MobileElement source = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[9]/android.widget.RelativeLayout[1]/android.widget.ImageView");
			MobileElement target = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[8]/android.widget.RelativeLayout[1]/android.widget.ImageView");

			TouchAction action = new TouchAction(driver);
	
			action.longPress(ElementOption.element(source)).moveTo(ElementOption.element(target)).release().perform();
	
			MobileElement confirm_swap = driver.findElement(By.id("com.geotechinfo.realfantasy:id/confirm_transfer"));
			confirm_swap.click();
			
			MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
			settings_nav.click();
	
			MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
	
			MobileElement listitem = (MobileElement)driver.findElement(
					MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector()).scrollIntoView("
							+ "new UiSelector().text(\"LOGOUT\"));"));
	
			listitem.click();
	
			MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
			logout_confirm.click();
			
		}
		
	}*/
	
	/*@Test(dataProvider = "RegData3", priority = 5)
	public void newuserleaderboardcheck(String username, String password){
		
		try{
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
			
		
			MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			existing_user.click();
		
			MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
			email_login.click();
		
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
		
			MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			login_button.click();
		
			//driver.navigate().back();
		
			Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();
			MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
		
				if(close_help == true){
			
					close_help_icon.click();
			
					MobileElement leaderboard_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[4]/android.widget.ImageView");
					leaderboard_nav.click();
			
					MobileElement leaderboard_rank = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tvTPRank"));
					Assert.assertEquals(leaderboard_rank.getText(), "N/A");
			
					MobileElement leaderboard_history = driver.findElement(By.id("com.geotechinfo.realfantasy:id/credit_btn"));
					leaderboard_history.click();
			
					MobileElement history_rank = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.TextView[2]");
					Assert.assertEquals(history_rank.getText(), "Rank");
			
					MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
					settings_nav.click();
			
					MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
			
					MobileElement listitem = (MobileElement)driver.findElement(
							MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector()).scrollIntoView("
									+ "new UiSelector().text(\"LOGOUT\"));"));
			
					listitem.click();
			
					MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
					logout_confirm.click();
			
			}
			
		}
		catch( Exception e){
		
			MobileElement leaderboard_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[4]/android.widget.ImageView");
			leaderboard_nav.click();
		
			MobileElement leaderboard_rank = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tvTPRank"));
			Assert.assertEquals(leaderboard_rank.getText(), "N/A");
		
			MobileElement leaderboard_history = driver.findElement(By.id("com.geotechinfo.realfantasy:id/credit_btn"));
			leaderboard_history.click();
		
			MobileElement history_rank = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.TextView[2]");
			Assert.assertEquals(history_rank.getText(), "Rank");
		
			MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
			settings_nav.click();
		
			MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
		
			MobileElement listitem = (MobileElement)driver.findElement(
				MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector()).scrollIntoView("
								+ "new UiSelector().text(\"LOGOUT\"));"));
		
			listitem.click();
		
			MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
			logout_confirm.click();
		}
		
	}*/
	
	/*@Test(dataProvider = "RegData3", priority = 6)
	public void newuserteaminfocheck(String username, String password){
		
		try{
		
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
		existing_user.click();
		
		MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
		email_login.click();
		
		driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
		driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
		
		MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
		login_button.click();
		
		//driver.navigate().back();
		
		Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();
		MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
	
			if(close_help == true){
		
				close_help_icon.click();
		
				MobileElement teaminfo_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/teamInfo_tv"));
				teaminfo_button.click();
		
				MobileElement teaminfo_title = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_player_details"));
				Assert.assertEquals(teaminfo_title.getText(), "TEAM DETAILS");
		
				MobileElement teaminfo_playerlist = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.ImageView");
				teaminfo_playerlist.click();
		
				//MobileElement teaminfo_playerdetails = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_player_details"));
				MobileElement selected_by = driver.findElement(By.id("com.geotechinfo.realfantasy:id/selected_by_heading"));
				Assert.assertEquals(selected_by.getText(), "SELECTED BY");
		
				MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				page_back.click();
		
				MobileElement left_gs = driver.findElement(By.id("com.geotechinfo.realfantasy:id/left_gs"));
				left_gs.click();
		
				MobileElement page_back2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				page_back2.click();
		
				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
		
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
				
				MobileElement listitem = (MobileElement)driver.findElement(
						MobileBy.AndroidUIAutomator(
								"new UiScrollable(new UiSelector()).scrollIntoView("
										+ "new UiSelector().text(\"LOGOUT\"));"));
		
				listitem.click();
		
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
				
			}
		
		}
		
		catch(Exception e){
			
			MobileElement teaminfo_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/teamInfo_tv"));
			teaminfo_button.click();
	
			MobileElement teaminfo_title = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_player_details"));
			Assert.assertEquals(teaminfo_title.getText(), "TEAM DETAILS");
	
			MobileElement teaminfo_playerlist = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.ImageView");
			teaminfo_playerlist.click();
	
			//MobileElement teaminfo_playerdetails = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_player_details"));
			MobileElement selected_by = driver.findElement(By.id("com.geotechinfo.realfantasy:id/selected_by_heading"));
			Assert.assertEquals(selected_by.getText(), "SELECTED BY");
	
			MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
			page_back.click();
	
			MobileElement left_gs = driver.findElement(By.id("com.geotechinfo.realfantasy:id/left_gs"));
			left_gs.click();
	
			MobileElement page_back2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
			page_back2.click();
	
			MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
			settings_nav.click();
	
			MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
			
			MobileElement listitem = (MobileElement)driver.findElement(
					MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector()).scrollIntoView("
									+ "new UiSelector().text(\"LOGOUT\"));"));
	
			listitem.click();
	
			MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
			logout_confirm.click();
			
		}
		
	}*/
	
	/*@Test(dataProvider = "RegData3", priority = 7)
	public void newuserplayerdetailscurrent(String username, String password){
		
		try{
			
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
				MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
				existing_user.click();
		
				MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
				email_login.click();
		
				driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
				driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
		
				MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
				login_button.click();
		
				//driver.navigate().back();
		
				Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();	
		
				MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
			
				if(close_help){
				
					close_help_icon.click();
				
					MobileElement fixture_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/fixtures_tv"));
					fixture_tab.click();
					
					MobileElement current_matchday = driver.findElement(By.id("com.geotechinfo.realfantasy:id/gs_title"));
					String current_matchday_text = current_matchday.getText();
					
					MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
					page_back.click();
					
					MobileElement player_details_to_check = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout[1]/android.widget.ImageView");
					player_details_to_check.click();
					
					MobileElement current_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/transfer_btn"));
					current_tab.click();
					
					MobileElement matchday_text = driver.findElement(By.id("com.geotechinfo.realfantasy:id/gs_title"));
					
					Assert.assertEquals(matchday_text.getText(), current_matchday_text);
					
					MobileElement page_back2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
					page_back2.click();
					
					MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
					settings_nav.click();
					
					MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
					
					MobileElement listitem = (MobileElement)driver.findElement(
							MobileBy.AndroidUIAutomator(
									"new UiScrollable(new UiSelector()).scrollIntoView("
											+ "new UiSelector().text(\"LOGOUT\"));"));
					
					listitem.click();
					
					MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
					logout_confirm.click();
				
			}
			
		}
		
			catch(Exception e){
			
			
				MobileElement fixture_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/fixtures_tv"));
				fixture_tab.click();
				
				MobileElement current_matchday = driver.findElement(By.id("com.geotechinfo.realfantasy:id/gs_title"));
				String current_matchday_text = current_matchday.getText();
				
				MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				page_back.click();
				
				MobileElement player_details_to_check = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player_details_to_check.click();
				
				MobileElement current_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/transfer_btn"));
				current_tab.click();
				
				MobileElement matchday_text = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_leagues"));
				
				Assert.assertEquals(matchday_text.getText(), current_matchday_text);
				
				MobileElement page_back2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
				page_back2.click();
				
				MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
				settings_nav.click();
				
				MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
				
				MobileElement listitem = (MobileElement)driver.findElement(
						MobileBy.AndroidUIAutomator(
								"new UiScrollable(new UiSelector()).scrollIntoView("
										+ "new UiSelector().text(\"LOGOUT\"));"));
				
				listitem.click();
				
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
			
		}
		
	}*/
	
	
	/*@Test(dataProvider = "RegData3", priority = 8)
	public void newuserfixturecheck(String username, String password){
	
		try{
		
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
			MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			existing_user.click();
		
			MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
			email_login.click();
		
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
			driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
		
			MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
			login_button.click();
		
			//driver.navigate().back();
		 
			Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();
			MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
		
				if(close_help == true){
			
					close_help_icon.click();
		
					MobileElement fixture_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/fixtures_tv"));
					fixture_tab.click();
		
					MobileElement notstarted_match = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView");
					notstarted_match.click();
		
					MobileElement teamstanding_link = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tvViewPrize"));
					Assert.assertEquals(teamstanding_link.getText(), "TEAM STANDINGS");
		
					MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
					page_back.click();
		
					MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
					settings_nav.click();
		
					MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
		
					MobileElement listitem = (MobileElement)driver.findElement(
							MobileBy.AndroidUIAutomator(
									"new UiScrollable(new UiSelector()).scrollIntoView("
											+ "new UiSelector().text(\"LOGOUT\"));"));
		
					listitem.click();
		
					MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
					logout_confirm.click();
					
				}
		
		}
		
		catch(Exception e){
		
					MobileElement fixture_tab = driver.findElement(By.id("com.geotechinfo.realfantasy:id/fixtures_tv"));
					fixture_tab.click();
		
					MobileElement notstarted_match = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView");
					notstarted_match.click();
		
					MobileElement teamstanding_link = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tvViewPrize"));
					Assert.assertEquals(teamstanding_link.getText(), "TEAM STANDINGS");
		
					MobileElement page_back = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_back"));
					page_back.click();
		
					MobileElement settings_nav = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/androidx.appcompat.app.a.c[1]/android.widget.ImageView");
					settings_nav.click();
		
					MobileElement list = (MobileElement)driver.findElementByClassName("android.widget.TextView");
		
					MobileElement listitem = (MobileElement)driver.findElement(
							MobileBy.AndroidUIAutomator(
									"new UiScrollable(new UiSelector()).scrollIntoView("
											+ "new UiSelector().text(\"LOGOUT\"));"));
		
					listitem.click();
		
					MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
					logout_confirm.click();
		
		}
	}*/
	
	
	
		
	/*@Test(dataProvider = "RegData3", priority = 10)
	public void newusercreateteam(String username, String password, String nextteam) throws InterruptedException{
		
			try{
				
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				
				MobileElement existing_user = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
				existing_user.click();
			
				MobileElement email_login = driver.findElement(By.id("com.geotechinfo.realfantasy:id/email_layout"));
				email_login.click();
			
				driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_email")).sendKeys(username);
				driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_pass")).sendKeys(password);
			
				//driver.navigate().back();
				
				MobileElement login_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/next_tv"));
				login_button.click();
				
				Boolean close_help = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment")).isDisplayed();	
				
				MobileElement close_help_icon = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
			
			if(close_help){
				
				close_help_icon.click();
			
				MobileElement league_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_leagues"));
				league_button.click();
				
				MobileElement select_league = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[3]/android.widget.RelativeLayout/android.widget.ImageView");
				select_league.click();
				
				driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_team_name")).sendKeys(nextteam);
			
				MobileElement create_team = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_next"));
				create_team.click();
			
				MobileElement select_formation_help = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]");
				select_formation_help.click();
			
				MobileElement select_formation = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_next"));
				select_formation.click();
			
				MobileElement select_players_help = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]");
				select_players_help.click();
			
				//select players from dashboard
				
				MobileElement player1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player1.click();
				
				MobileElement close_help_icon2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
				close_help_icon2.click();
				
				MobileElement price_sort = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort.click();
		
				MobileElement select_player1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player1.click();
				
				
				MobileElement player2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player2.click();
				
				MobileElement price_sort2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort2.click();
		
				MobileElement select_player2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player2.click();
				
				
				MobileElement player3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[3]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player3.click();
				
				MobileElement price_sort3 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort3.click();
		
				MobileElement select_player3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player3.click();
				
				
				MobileElement player4 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[4]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player4.click();
				
				MobileElement price_sort4 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort4.click();
		
				MobileElement select_player4 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player4.click();
				
				
				
				MobileElement player5 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[5]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player5.click();
				
				MobileElement price_sort5 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort5.click();
		
				MobileElement select_player5 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[4]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player5.click();
				
				
				
				MobileElement player6 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[6]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player6.click();
				
				MobileElement price_sort6 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort6.click();
		
				MobileElement select_player6 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player6.click();
				
				
				MobileElement player7 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[7]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player7.click();
				
				MobileElement price_sort7 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort7.click();
		
				MobileElement select_player7 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player7.click();
				
				
				
				MobileElement player8 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[8]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player8.click();
				
				MobileElement price_sort8 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort8.click();
		
				MobileElement select_player8 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player8.click();
				
				
				
				MobileElement player9 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[9]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player9.click();
				
				MobileElement price_sort9 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort9.click();
		
				MobileElement select_player9 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player9.click();
				
				
				
				MobileElement player10 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[10]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player10.click();
				
				MobileElement price_sort10 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort10.click();
		
				MobileElement select_player10 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player10.click();
				
				
				
				MobileElement player11 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[11]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player11.click();
				
				MobileElement price_sort11 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort11.click();
		
				MobileElement select_player11 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player11.click();
				
				
			
				WebDriverWait wait = new WebDriverWait(driver,5);
				wait.until(ExpectedConditions.elementToBeClickable(By.id("com.geotechinfo.realfantasy:id/tv_next")));
			
				MobileElement confirm_team_selection = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_next"));
				confirm_team_selection.click();
			
				MobileElement select_leader_help = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]");
				select_leader_help.click();
			
				MobileElement select_leader = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout");
				select_leader.click();
			
				MobileElement confirm_team = driver.findElement(By.id("com.geotechinfo.realfantasy:id/captain_btn"));
				confirm_team.click();
			
				MobileElement close_help_icon3 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
				close_help_icon3.click();
			
				MobileElement leagues = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_leagues"));
				leagues.click();
			
				MobileElement top_nav_options = driver.findElement(By.id("com.geotechinfo.realfantasy:id/option_header"));
				top_nav_options.click();
			
				MobileElement top_nav_logout = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tvLogout"));
				top_nav_logout.click();
			
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
				
			}
		}
			
			catch(Exception e){
				
				MobileElement league_button = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_leagues"));
				league_button.click();
				
				MobileElement select_league = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[3]/android.widget.RelativeLayout/android.widget.ImageView");
				select_league.click();
				
				driver.findElement(By.id("com.geotechinfo.realfantasy:id/et_team_name")).sendKeys("NBTEAM2");
			
				MobileElement create_team = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_next"));
				create_team.click();
			
				MobileElement select_formation_help = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]");
				select_formation_help.click();
			
				MobileElement select_formation = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_next"));
				select_formation.click();
			
				MobileElement select_players_help = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]");
				select_players_help.click();
			
				//select players from dashboard
				
				MobileElement player1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player1.click();
				
				MobileElement close_help_icon2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
				close_help_icon2.click();
				
				MobileElement price_sort = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort.click();
		
				MobileElement select_player1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player1.click();
				
				
				MobileElement player2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player2.click();
				
				MobileElement price_sort2 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort2.click();
		
				MobileElement select_player2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player2.click();
				
				
				MobileElement player3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[3]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player3.click();
				
				MobileElement price_sort3 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort3.click();
		
				MobileElement select_player3 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player3.click();
				
				
				MobileElement player4 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[4]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player4.click();
				
				MobileElement price_sort4 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort4.click();
		
				MobileElement select_player4 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player4.click();
				
				
				
				MobileElement player5 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[5]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player5.click();
				
				MobileElement price_sort5 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort5.click();
		
				MobileElement select_player5 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player5.click();
				
				
				
				MobileElement player6 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[6]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player6.click();
				
				MobileElement price_sort6 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort6.click();
		
				MobileElement select_player6 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player6.click();
				
				
				MobileElement player7 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[7]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player7.click();
				
				MobileElement price_sort7 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort7.click();
		
				MobileElement select_player7 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player7.click();
				
				
				
				MobileElement player8 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[8]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player8.click();
				
				MobileElement price_sort8 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort8.click();
		
				MobileElement select_player8 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player8.click();
				
				
				
				MobileElement player9 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[9]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player9.click();
				
				MobileElement price_sort9 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort9.click();
		
				MobileElement select_player9 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player9.click();
				
				
				
				MobileElement player10 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[10]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player10.click();
				
				MobileElement price_sort10 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort10.click();
		
				MobileElement select_player10 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player10.click();
				
				
				
				MobileElement player11 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[11]/android.widget.RelativeLayout[1]/android.widget.ImageView");
				player11.click();
				
				MobileElement price_sort11 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/iv_sort"));
				price_sort11.click();
		
				MobileElement select_player11 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.TextView[1]");
				select_player11.click();
				
				
			
				WebDriverWait wait = new WebDriverWait(driver,5);
				wait.until(ExpectedConditions.elementToBeClickable(By.id("com.geotechinfo.realfantasy:id/tv_next")));
			
				MobileElement confirm_team_selection = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_next"));
				confirm_team_selection.click();
			
				MobileElement select_leader_help = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[3]");
				select_leader_help.click();
			
				MobileElement select_leader = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.RelativeLayout");
				select_leader.click();
			
				MobileElement confirm_team = driver.findElement(By.id("com.geotechinfo.realfantasy:id/captain_btn"));
				confirm_team.click();
			
				MobileElement close_help_icon3 = driver.findElement(By.id("com.geotechinfo.realfantasy:id/close_fragment"));
				close_help_icon3.click();
			
				MobileElement leagues = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tv_leagues"));
				leagues.click();
			
				MobileElement top_nav_options = driver.findElement(By.id("com.geotechinfo.realfantasy:id/option_header"));
				top_nav_options.click();
			
				MobileElement top_nav_logout = driver.findElement(By.id("com.geotechinfo.realfantasy:id/tvLogout"));
				top_nav_logout.click();
			
				MobileElement logout_confirm = driver.findElement(By.id("android:id/button1"));
				logout_confirm.click();
				
			}
					
		}*/
	
	
	
	
		
}
	
	
	
	/*@Test
	public void testOne(){

		// creates a toggle for the given test, adds all log events under it    
        ExtentTest test1 = extent.createTest("Test One", "Sample test case for demo");
        
     // log(Status, details)
        test1.log(Status.INFO, "Test one started");
		
		driver.get("https://in.yahoo.com/?p=us");
		
		test1.log(Status.PASS, "Navigate to https://yahoo.com");
		
		//driver.findElement(By.name("p")).sendKeys("Automation");
		//test1.log(Status.PASS, "Enter Automation in yahoo search box");
		
		//driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		//test1.log(Status.PASS, "Hit the keyboard enter key");
		
		System.out.println("Completeed Test One");
		//test1.log(Status.INFO, "Test One completed");
	}*/


