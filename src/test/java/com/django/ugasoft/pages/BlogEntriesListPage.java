package com.django.ugasoft.pages;

import com.django.ugasoft.model.BlogEntry;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Page with list of Blog Entries and action to perform with them
 */
@Data
@PropertySource("classpath:application.properties")
public class BlogEntriesListPage extends BasePage {

    //Properties
    /**
     * Header of the page
     */
    @FindBy(css = "div#content>h1")
    private WebElement pageHeader;

    /**
     * Selector of action to perform with entries
     */
    @FindBy(name = "action")
    private WebElement actionSelect;
    /**
     * Submit button to perform action
     */
    @FindBy(name = "index")
    private WebElement actionBtn;
    /**
     * Submit button to confirm action performing
     */
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement confirmBtn;
    /**
     * Table with list of Blog Entries
     */
    @FindBy(id = "result_list")
    private WebElement blogEntriesTable;
    /**
     * List of parsed Blog Entries from Table
     */
    private List<BlogEntry> blogEntriesList;

    //Constructors
    public BlogEntriesListPage(WebDriver driver) {
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
     * Parse Blog Entries from Table
     *
     * @param force is boolean parameter to parse table one more time (to imitate cache)
     * @return List of parsed Blog Entries from Table (only id, title and slug)
     */
    public List<BlogEntry> getBlogEntriesList(boolean force) {
        //Parse table only if we find the WebElement of it and force is true
        if (blogEntriesTable.isDisplayed() && (force || blogEntriesList == null)) {
            List<WebElement> blogEntriesRowList = blogEntriesTable.findElements(By.cssSelector("tr"));
            WebElement blogEntryRow;
            BlogEntry newBlogEntry;

            if (blogEntriesRowList.size() > 0) {
                blogEntriesList = new ArrayList<>(blogEntriesRowList.size());
                //Skip first row of Table Header. Check all rows of Blog Entries Table
                for (int i = 1; i < blogEntriesRowList.size(); i++) {
                    blogEntryRow = blogEntriesRowList.get(i);
                    //Find the Title of the Blog Entry
                    WebElement blogEntryTitle = blogEntryRow.findElement(By.cssSelector("th"));
                    //Find all other data of Blog Entry on the page
                    List<WebElement> blogEntriesColumnList = blogEntryRow.findElements(By.cssSelector("td"));

                    //Fill the blog entry with data
                    newBlogEntry = new BlogEntry();
                    newBlogEntry.setId(Long.parseLong(blogEntriesColumnList.get(0).
                            findElement(By.name("_selected_action")).getAttribute("value")));
                    newBlogEntry.setTitle(blogEntryTitle.getText());
                    newBlogEntry.setSlug(blogEntriesColumnList.get(1).getText());
                    blogEntriesList.add(newBlogEntry);
                }
            }
        }
        return blogEntriesList;
    }

    /**
     * Check Blog Entry in parsed list of Entries
     *
     * @param blogEntry data to find in Blog Entries
     * @return true - find at least one entry, false - there is no such entries
     */
    public List<BlogEntry> checkBlogEntryExist(BlogEntry blogEntry) {
        List<BlogEntry> resultBlogEntriesList = getBlogEntriesList(false);
        //find all elements with matched Title and Slug on the Page
        resultBlogEntriesList = resultBlogEntriesList.stream()
                .filter(blog ->
                        blog.getTitle().equals(blogEntry.getTitle()) && blog.getSlug().equals(blogEntry.getSlug()))
                .collect(Collectors.toList());

        //if we find at least one entry than return true
        return resultBlogEntriesList;
    }

    /**
     * Delete Blog Entry from the Blog
     *
     * @param blogEntry to delete from the Blog
     * @return
     */
    public BlogEntriesListPage deleteBlogEntry(BlogEntry blogEntry) {
        //Find the Blog Entry in a table on the page
        BlogEntry blogEntryToDelete = checkBlogEntryExist(blogEntry).get(0);

        if (blogEntryToDelete != null) {
            //Select right checkbox on the row to delete right entry
            WebElement blogEntryCheckBox = driver.findElement(
                    By.xpath("(//input[@value='" + blogEntryToDelete.getId() + "'])[2]"));
            blogEntryCheckBox.click();

            //Select the delete action to perform with selected row
            Select select = new Select(actionSelect);
            select.selectByValue("delete_selected");

            //Delete Entry
            actionBtn.click();

            //Confirmation of Deleting Entry
            confirmBtn.click();
            return this;
        }
        return null;
    }

    /**
     * Delete Blog Entry from the Blog by Title
     *
     * @param blogEntry to delete from the Blog
     * @return
     */
    public BlogEntriesListPage deleteBlogEntryByTitle(BlogEntry blogEntry) {
        //choosing right table row
        String linkToBlogEntry = driver.findElement(By.linkText(blogEntry.getTitle())).getAttribute("href");
        if (!linkToBlogEntry.isEmpty()) {
            //find last digits in link and parse it to int
            try {
                //delete last '/' in string
                linkToBlogEntry = linkToBlogEntry.substring(0, linkToBlogEntry.lastIndexOf('/'));
                //delete all symbols until last digits
                linkToBlogEntry = linkToBlogEntry.substring(linkToBlogEntry.lastIndexOf('/') + 1);
                Integer blogEntryId = Integer.parseInt(linkToBlogEntry);

                //Select right checkbox on the row to delete right entry
                WebElement blogEntryCheckBox = driver.findElement(
                        By.xpath("(//input[@value='" + blogEntryId + "'])[2]"));
                blogEntryCheckBox.click();

                //Select the delete action to perform with selected row
                Select select = new Select(actionSelect);
                select.selectByValue("delete_selected");

                //Delete Entry
                actionBtn.click();

                //Confirmation of Deleting Entry
                confirmBtn.click();
                return this;
            } catch (NumberFormatException exc) {
                System.out.println("Can't find BlogEntry Id in: " + exc);
            }
        }
        return null;
    }
}
