package config;

import config.web.WebConfig;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {
    @Getter
    private static final WebConfig webConfig =
            ConfigFactory.create(
                    WebConfig.class,
                    System.getProperties()
            );
}
