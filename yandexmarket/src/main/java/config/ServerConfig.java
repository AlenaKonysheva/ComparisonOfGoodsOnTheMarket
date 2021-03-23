package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")
public interface ServerConfig extends Config {

    @Key("userEmail")
    String userEmail();

    @Key("userPassword")
    String userPassword();

    @Key("url.yandex")
    String urlYandex();

    @Key("urlOtus")
    String urlOtus();
}
