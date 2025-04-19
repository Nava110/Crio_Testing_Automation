package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Level;
import demo.wrappers.Wrappers;
import org.openqa.selenium.WebElement;




public class TestCases {
    ChromeDriver driver;



    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
 
     */
      @Test
public void testCase01() throws  InterruptedException{
WebElement nameInputBox = driver.findElement(By.xpath("//input[@type='text' and contains(@class, 'whsOnd')]"));
Thread.sleep(3000);
Wrappers.enterText(nameInputBox,"Crio Learner");

WebElement practicingAutomationTextArea =driver.findElement(By.xpath("//textarea[contains(@class, 'tL9Q4c')]"));
String practicingAutomationText = "I want to be the best QA Engineer!";
String epochTimeString = Wrappers.getEpochTimeAsString();
System.out.println("wait 2");
Wrappers.enterText(practicingAutomationTextArea, practicingAutomationText+" "+epochTimeString);


//select radio button as per automation experience
Thread.sleep(3000);
System.out.println("wait3");
Wrappers.radioButton(driver,  "6 - 10");

//select all skillset

Thread.sleep(3000);
System.out.println("wait4");

Wrappers.checkBox(driver, "Java");
Wrappers.checkBox(driver, "Selenium");
Wrappers.checkBox(driver, "TestNG");

//click on dropdown
WebElement dropDoWebElement= driver.findElement(By.xpath("//div[contains(@class,'DEh1R')]"));
System.out.println("wait 5");
        Thread.sleep(3000);
        Wrappers.clickOnElement(driver, dropDoWebElement);
        Thread.sleep(1000); 
        // Wrappers.dropDownClick(driver,  "Ms");
        List<WebElement> dropDownList =driver.findElements(By.xpath("//div[contains(@class, 'ncFHed')]//span[not(contains(text(), 'Choose'))]"));
        Wrappers.dropDownClickByLoop(dropDownList,"Ms");

        
 //select  Days 7 days ago
 WebElement dateInputBox= driver.findElement(By.xpath("//input[@type='date']"));
 String sevenDaysAgoDate = Wrappers.getDateSevenDaysAgo();
 Thread.sleep(3000);
 System.out.println("wait 6");
Wrappers.enterText(dateInputBox, sevenDaysAgoDate);
 // Enter current time in HH:MM
 WebElement hourElement = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
 WebElement miElement = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
 WebElement submitBtn = driver.findElement(By.xpath("//span[@class='NPEfkd RveJvd snByac' and text()='Submit']"));

 Wrappers.enterText(hourElement,  "07");
 Wrappers.enterText(miElement,  "30");
 Wrappers.clickOnElement(driver, submitBtn);
 
 Thread.sleep(3000);
 System.out.println("wait 7");
 WebElement successMsgElement = driver.findElement(By.xpath("//div[@class='vHW8K' and contains(text(), 'Thanks for your response')]" ));
 System.out.println(successMsgElement.getText());
    

}   
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        //navigateto url

           driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}