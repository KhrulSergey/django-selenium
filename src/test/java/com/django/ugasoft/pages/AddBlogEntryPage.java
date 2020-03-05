package com.django.ugasoft.pages;

import com.django.ugasoft.model.BlogEntry;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class AddBlogEntryPage extends BasePage {

    //Properties
    /**
     * Header of the page
     */
    @FindBy(css = "div#content>h1")
    private WebElement pageHeader;
    /**
     * Title input of New Blog Entry
     */
    @FindBy(id = "id_title")
    private WebElement title;
    /**
     * Slug input of New Blog Entry
     */
    @FindBy(id = "id_slug")
    private WebElement slug;
    /**
     * Text MarkDown input of New Blog Entry
     */
    @FindBy(id = "id_text_markdown")
    private WebElement txtMarkdown;
    /**
     * Text input of New Blog Entry
     */
    @FindBy(id = "id_text")
    private WebElement text;
    /**
     * Submit button to create new Blog Entry
     */
    @FindBy(name = "_save")
    private WebElement createBtn;

    //Initializing the Page Object
    public AddBlogEntryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    //Getter and Setters
    /**
     * Set Blog Title data in input
     *
     * @param strTitle is data to fill
     */
    public void setTitle(String strTitle) {
        title.sendKeys(strTitle);
    }

    /**
     * Set Blog Slug data in input
     *
     * @param strSlug is data to fill
     */
    public void setSlug(String strSlug) {
        slug.sendKeys(strSlug);
    }

    /**
     * Set Blog Text MarkDown data in input
     *
     * @param strTxtMarkdown is data to fill
     */
    public void setTxtMarkdown(String strTxtMarkdown) {
        txtMarkdown.sendKeys(strTxtMarkdown);
    }

    /**
     * Set Blog Text data in input
     *
     * @param strText is data to fill
     */
    public void setText(String strText) {
        text.sendKeys(strText);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageHeader() {
        return pageHeader.getText();
    }

    //Methods
    /**
     * Clear Blog Slug  input
     */
    public void clearSlug() {
        slug.clear();
    }

    public void createBtnClick() {
        createBtn.click();
    }

    /**
     * Filling all input of new Blog Entry on the Page
     *
     * @param blogEntry is a data to create new Blog Entry
     * @return new instance (redirect to) BlogEntriesListPage
     */
    public BlogEntriesListPage fillDataAndSubmit(BlogEntry blogEntry) {
        setTitle(blogEntry.getTitle());
        clearSlug();
        setSlug(blogEntry.getSlug());
        setTxtMarkdown(blogEntry.getTxtMarkdown());
        setText(blogEntry.getText());
        createBtnClick();
        return new BlogEntriesListPage(driver);
    }
}
