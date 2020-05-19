package com.qa.hwa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Add bean definitions here?
 * add instance of appContext in main?
 * .close() of SpringApplication - need to set conditions...
 * SpringApplication.exit(appContext, 0);
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args ){
        SpringApplication.run(App.class);
    }
}
