package com.django.ugasoft.pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Login (start) page
 */
@Data
public class LoginPage extends Page {

    //Properties
    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement loginBtn;

    //Constructors
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    //Getter and Setters

    /**
     * Set user name in input
     *
     * @param strUserName is data to fill
     */
    public void setUserName(String strUserName) {
        username.sendKeys(strUserName);
    }

    /**
     * Set password in input
     *
     * @param strPassword is data to fill
     */
    public void setPassword(String strPassword) {
        password.sendKeys(strPassword);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    //Methods

    /**
     * Login to Blog Admin Home Page
     *
     * @param username credential to login with
     * @param password credential to login with
     * @return new instance (redirect) to Admin Home Page
     */
    public AdminHomePage login(String username, String password) {
        setUserName(username);
        setPassword(password);
        loginBtn.click();

        return new AdminHomePage(driver);
    }

}
