package ro.ubb.music.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.music.client.ui.TextUi;


public class ClientApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.music.client.config");

        TextUi ui = context.getBean(TextUi.class);
        ui.start();
    }
}
