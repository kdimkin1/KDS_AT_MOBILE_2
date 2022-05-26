package kds.at.drivers;

import com.codeborne.selenide.WebDriverProvider;
import kds.at.config.Credentials;
import kds.at.helpers.Browserstack;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class BrowserstackMobileDriver implements WebDriverProvider {

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);
        mutableCapabilities.setCapability("browserstack.appium_version", "1.22.0");
//        caps.setCapability("browserstack.user", "dmitrykir_ruSiVY");
//        caps.setCapability("browserstack.key", "qd7vwBPZgU98h5mcgpBh");
        mutableCapabilities.setCapability("browserstack.user", Browserstack.browserstackLogin);
        mutableCapabilities.setCapability("browserstack.key", Browserstack.browserstackPassword);
        mutableCapabilities.setCapability("app", Credentials.config.app());
        mutableCapabilities.setCapability("device", Credentials.config.deviceName());
        mutableCapabilities.setCapability("os_version", Credentials.config.platformVersion());
        mutableCapabilities.setCapability("project", "KDS_AT: Browserstack Java project");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "Sample tests run");

        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL(Credentials.config.url());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}