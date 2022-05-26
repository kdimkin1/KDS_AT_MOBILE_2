package kds.at.tests.appium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import kds.at.config.Credentials;
import kds.at.drivers.AppiumMobileDriver;
import kds.at.drivers.BrowserstackMobileDriver;
import kds.at.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.step;
import static kds.at.helpers.Attach.getSessionId;

public class TestBase {

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        switch (System.getProperty("device")) {
            case "real":
            case "emulator":
                Configuration.browser = AppiumMobileDriver.class.getName();
                break;
            case "browserstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            default:
                throw new IllegalArgumentException("Unknown device");
        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        open();  // Selenide "atavism" for Appium
    }

    @AfterEach
    public void afterEach() {
        String sessionId = "";
        if (Credentials.isBrowserStack()) {
            sessionId = getSessionId();
        }

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        step("Close driver", Selenide::closeWebDriver);

        // for Browserstack attach video available only after close browser
        if (Credentials.isBrowserStack()) {
            Attach.video(sessionId);
        }
    }
}
