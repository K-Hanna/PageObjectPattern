import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class ReceiptPage extends PageObject {

    public ReceiptPage(WebDriver driver) {
        super(driver);
    }

    public String confirmationHeader(){
        return driver.getTitle();
    }

    public String getAlert(){
        Alert alert = driver.switchTo().alert();

        String alertMessage= driver.switchTo().alert().getText();
        alert.accept();

        return alertMessage;
    }
}
