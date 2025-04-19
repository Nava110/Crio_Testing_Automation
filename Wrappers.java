package demo.wrappers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Wrappers {

    public static boolean enterText(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void dropDownClickByLoop(List<WebElement> elements, String dropDownText) {
        try {
            for (WebElement element : elements) {
                if (element.getText().equals(dropDownText)) {
                    element.click();
                    break;
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void radioButton(ChromeDriver driver, String radioButtonText) {
        try {
            WebElement element = driver.findElement(By.xpath(
                "//div[@role='radio' and @aria-label='" + radioButtonText + "']"));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkBox(ChromeDriver driver, String checkboxText) {
        try {
            // Direct XPath to the checkbox element using data-answer-value attribute
            String xpath = "//div[@role='checkbox' and @data-answer-value='" + checkboxText + "']";
            
            WebElement element = driver.findElement(By.xpath(xpath));
            
            // Check if already selected
            if (element.getAttribute("aria-checked").equals("false")) {
                element.click();
                // Wait briefly for the click to take effect
                Thread.sleep(500);
            }
            
            System.out.println("Checkbox '" + checkboxText + "' clicked successfully");
        } catch (Exception e) {
            System.out.println("Failed to click checkbox '" + checkboxText + "'");
            e.printStackTrace();
        }
    }

    public static void dropDownClick(ChromeDriver driver, String dropDownText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
            // Step 1: Wait for the option to be present (not just visible yet)
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@data-value='" + dropDownText + "']")));
    
            // Step 2: Wait until it's clickable and then click via JavaScript (more reliable)
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@data-value='" + dropDownText + "']")));
    
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
    
            System.out.println("✅ '" + dropDownText + "' selected successfully");
        } catch (Exception e) {
            System.out.println("❌ Failed to select dropdown option: " + dropDownText);
            e.printStackTrace();
        }
    }
    
    

    public static void selectFromDropdown(List<WebElement> elements, String valueToSelect) {
        try {
            for (WebElement element : elements) {
                if (element.getText().equals(valueToSelect)) {
                    element.click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickOnElement(ChromeDriver driver, WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public static String getDateSevenDaysAgo() {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateMinus7Days = currentDate.minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateMinus7Days.format(formatter);
    }

    public static boolean dismissAlert(ChromeDriver driver) {
        try {
            driver.switchTo().alert().dismiss();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getEpochTimeAsString() {
        long epochTime = System.currentTimeMillis() / 1000;
        return String.valueOf(epochTime);
    }

    public static boolean handleAlert(ChromeDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
