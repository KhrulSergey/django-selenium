package com.django.ugasoft.pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class AdminHomePage extends BasePage {

    //Properties
    /**
     * Header of the page
     */
    @FindBy(className = "dashboard-title")
    private WebElement pageHeader;
    /**
     * Add Blog Entity Button
     */
    @FindBy(xpath = "(//span[text()='Добавить'])[3]")
    private WebElement addBlogEntryBtn;
    /**
     * Link to Blog Entities List and also change entities action
     */
    @FindBy(xpath = "(//span[text()='Изменить'])[3]")
    private WebElement blogEntriesListBtn;

    //Constructors

    /**
     * Initializing the Page Object
     *
     * @param driver is Selenium WebDriver
     */
    public AdminHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    //Getter and Setters
    public String getPageHeader() {
        return pageHeader.getText();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    //Methods

    /**
     * Open Add Blog Entry Page by clicking button
     *
     * @return new instance of AddBlogEntryPage
     */
    public AddBlogEntryPage clickOnAddBlogEntryBtn() {
        addBlogEntryBtn.click();
        return new AddBlogEntryPage(driver);
    }

    /**
     * Open Blog Entries List Page by clicking button
     *
     * @return new instance of BlogEntriesListPage
     */
    public BlogEntriesListPage blogEntriesListBtnClick() {
        blogEntriesListBtn.click();
        return new BlogEntriesListPage(driver);
    }
}
