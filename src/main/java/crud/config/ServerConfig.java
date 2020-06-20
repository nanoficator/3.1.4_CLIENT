package crud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:conf.properties")
public class ServerConfig {

    @Value("${server_IP}")
    private String serverIp;

    @Value("${server_port}")
    private String serverPort;

    public String getServerAddress() {
        String url = "http://" + serverIp + ":" + serverPort;
        return url;
    }

}
