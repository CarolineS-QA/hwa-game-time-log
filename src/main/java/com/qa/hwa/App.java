package com.qa.hwa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Add bean definitions here?
 * .close() of SpringApplication - need to set conditions...
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args ){
        SpringApplication.run(App.class);
        //add instance of appContext here....
        //conditions for shutdown?
        //SpringApplication.exit(appContext, 0);
    }
}
