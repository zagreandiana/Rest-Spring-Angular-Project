package ro.ubb.music.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.client.ui.TextUi;


@Configuration
public class ClientConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    TextUi textUi() {
        return new TextUi();
    }
}
