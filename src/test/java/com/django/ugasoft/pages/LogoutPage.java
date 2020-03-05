package com.django.ugasoft.pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Logout confirmation page
 */
@Data
public class LogoutPage extends Page {

    //Properties
    /**
     * Header of the page
     */
    @FindBy(css = "div#content>h1")
    WebElement pageHeader;

    //Constructors
    // Initializing the Page Object
    public LogoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    //Getter and Setters
    public String getPageHeader() {
        return pageHeader.getText();
    }

}

