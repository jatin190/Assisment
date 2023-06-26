import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;

public class Task {

    private static void testValidLogin(WebDriver driver) {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys("validusername");
        passwordField.sendKeys("validpassword");

        WebElement loginButton = driver.findElement(By.id("log-in"));
        loginButton.click();

        String url = driver.getCurrentUrl();
        if (url.equals("https://sakshingp.github.io/assignment/home.html")) {
            System.out.println("You have logged in successfully.");
        } else {
            System.out.println("You have made a mistake in the code.");
        }
    }

    private static void testInvalidUsername(WebDriver driver) {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys("invalidusername");
        passwordField.sendKeys("validpassword");

        WebElement loginButton = driver.findElement(By.id("log-in"));
        loginButton.click();

        String url = driver.getCurrentUrl();
        if (url.equals("https://sakshingp.github.io/assignment/home.html")) {
            System.out.println("Test case failed: You are logged in with an invalid username.");
        } else {
            System.out.println("Test case passed: You entered an invalid username and could not log in, as expected.");
        }
    }

    private static void testInvalidPassword(WebDriver driver) {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys("validusername");
        passwordField.sendKeys("invalidpassword");

        WebElement loginButton = driver.findElement(By.id("log-in"));
        loginButton.click();

        String url = driver.getCurrentUrl();
        if (url.equals("https://sakshingp.github.io/assignment/home.html")) {
            System.out.println("Test case failed: You are logged in with an invalid password.");
        } else {
            System.out.println("Test case passed: You entered an invalid password and could not log in, as expected.");
        }
    }

    private static boolean isSortTableCalledSuccessfully(WebDriver driver) {
        WebElement table = driver.findElement(By.id("transactionsTable"));

        List<String> initialOrder = getTableRowValues(table);

        WebElement amountHeader = driver.findElement(By.id("amount"));
        amountHeader.click();

        List<String> updatedOrder = getTableRowValues(table);

        boolean isSortTableCalled = !initialOrder.equals(updatedOrder);

        return isSortTableCalled;
    }

    private static List<String> getTableRowValues(WebElement table) {
        List<String> rowValues = new ArrayList<>();

        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            StringBuilder rowValue = new StringBuilder();
            for (WebElement cell : cells) {
                rowValue.append(cell.getText()).append(" ");
            }
            rowValues.add(rowValue.toString().trim());
        }

        return rowValues;
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jatin\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sakshingp.github.io/assignment/login.html");
        testValidLogin(driver);
        testInvalidUsername(driver);
        testInvalidPassword(driver);
        driver.close();

        WebDriver secondDriver = new ChromeDriver();
        secondDriver.get("https://sakshingp.github.io/assignment/home.html");
        boolean isSortTableCalled = isSortTableCalledSuccessfully(secondDriver);
        if (isSortTableCalled) {
            System.out.println("The values are sorted. The sortTable function is being called (assuming sortTable function is written correctly).");
        } else {
            System.out.println("The values are not sorted. The sortTable function is not being called (assuming sortTable function is written correctly).");
        }
        secondDriver.close();
    }
}
