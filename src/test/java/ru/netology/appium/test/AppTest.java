package ru.netology.appium.test;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.appium.screen.MainScreen;

import java.net.MalformedURLException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppTest {

    private AndroidDriver driver;

    @BeforeAll
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void emptyInputFieldTest() {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.inputField.click();
        mainScreen.inputField.sendKeys("qwerty");
        mainScreen.buttonChange.click();
        Assertions.assertEquals("qwerty", mainScreen.textToBeChanged.getText());
        mainScreen.inputField.click();
        mainScreen.inputField.clear();
        mainScreen.buttonChange.click();
        Assertions.assertEquals("qwerty", mainScreen.textToBeChanged.getText());
    }

    @Test
    public void newActivityTest() {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.inputField.click();
        mainScreen.inputField.sendKeys("123");
        mainScreen.buttonChange.click();
        Assertions.assertEquals("123", mainScreen.textToBeChanged.getText());
        mainScreen.buttonActivity.click();
        Assertions.assertEquals("123", mainScreen.newActivityText.getText());
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}
