package com.django.ugasoft.pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;

/**
 * The prototype for all page object instances
 */
@Data
public class Page {

    //Properties
    public static WebDriver driver;

    //Constructors
    public Page(WebDriver driver) {
        this.driver = driver;
    }

}
