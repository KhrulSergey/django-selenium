package com.django.ugasoft.pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class BasePage extends Page {

    //Properties
    /**
     * The link to admin home page
     */
    @FindBy(xpath = "//a[@href='/admin/']")
    private WebElement homeLink;

    /**
     * Logout button
     */
    @FindBy(xpath = "//a[@href='/admin/logout/']")
    private WebElement logoutBtn;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    //Constructors

    /**
     * Open admin home page
     *
     * @return admin home page
     */
    public AdminHomePage homeLinkRedirect() {
        if (homeLink.isDisplayed()) {
            homeLink.click();
            return new AdminHomePage(driver);
        }
        return null;
    }

    //Methods
    public LogoutPage LogoutBtnClick() {
        logoutBtn.click();
        return new LogoutPage(driver);
    }

}
