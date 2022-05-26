package kds.at.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:config/${device}.properties",
        "classpath:config/emulator.properties",
        "system:properties",})
public interface CredentialsConfig extends Config {
    @Key("deviceName")
    @DefaultValue("Pixel_4_API_30")
    String deviceName();
    String platformName();
    @Key("platformVersion")
    @DefaultValue("11.0")
    String platformVersion();

    // caps.setCapability: "browserstack.user"
    String user();
    // caps.setCapability: "browserstack.user"
    String key();
    // caps.setCapability: "app"
    String app();
    //URL("http://hub.browserstack.com/wd/hub");
    String url();


}