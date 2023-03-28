package com.victor.a1.task1;

import com.victor.a1.task1.view.Menu;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigReactiveWebApplicationContext("com.victor.a1.task1");
        Menu menu = context.getBean(Menu.class);
        menu.run();
    }
}
