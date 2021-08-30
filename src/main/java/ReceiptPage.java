import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;

public class ReceiptPage extends PageObject {

    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")
    WebElement managerId;

    @FindBy(xpath = "/html/body/div[3]/div/ul/li[15]/a")
    WebElement logOutBtn;

    public ReceiptPage(WebDriver driver) {
        super(driver);
    }

    public String confirmationHeader(){
        return driver.getTitle();
    }

    public String getInvalidAlert(){
        Alert alert = driver.switchTo().alert();

        String alertInvalidMessage= driver.switchTo().alert().getText();
        alert.accept();

        return alertInvalidMessage;
    }

    public String getManagerId(){
        return this.managerId.getText();
    }

    public void logOut(){
        this.logOutBtn.click();
    }

    public String getLogOutAlert(){

        Alert alert = driver.switchTo().alert();

        String logOutAlertMessage = driver.switchTo().alert().getText();
        alert.accept();

        return logOutAlertMessage;
    }

    public void takeScreenshot(String fileName){
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screenFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(fileName);

        try {
            FileUtils.copyFile(screenFile, destinationFile);
        } catch (IOException e) {
            System.out.println("Something went wrong with the screenshotting");
        }
    }
}
