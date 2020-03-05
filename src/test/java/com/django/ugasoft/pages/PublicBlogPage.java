package com.django.ugasoft.pages;

import com.django.ugasoft.model.BlogEntry;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Page with list of Public Blog Entries
 */
@Data
public class PublicBlogPage extends BasePage {

    //Properties
    /**
     * List of WebElement's with
     */
    @FindBy(className = "entry_title")
    private List<WebElement> blogEntitiesTitleLinkList;

    /**
     * List of parsed Blog Entries from WebElement's
     */
    private List<BlogEntry> blogEntriesList;

    //Constructors
    public PublicBlogPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    //Methods

    /**
     * Parse Blog Entries from List WebElement's
     *
     * @return List of parsed Blog Entries from WebElement's (only title)
     */
    public List<BlogEntry> getBlogEntriesList() {
        //Parse table only if we find the WebElement of it and force is true
        if (blogEntitiesTitleLinkList.size() > 0) {
                blogEntriesList = new ArrayList<>(blogEntitiesTitleLinkList.size());
                BlogEntry newBlogEntry;
                for (WebElement blogEntityTitleLink: blogEntitiesTitleLinkList) {
                    //Fill the blog entry with data
                    newBlogEntry = new BlogEntry();
                    newBlogEntry.setTitle(blogEntityTitleLink.getText());
                    blogEntriesList.add(newBlogEntry);
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
         getBlogEntriesList();
        //find all elements with matched Title and Slug on the Page
        List<BlogEntry> resultBlogEntriesList = blogEntriesList.stream()
                .filter(blog ->
                        blog.getTitle().equals(blogEntry.getTitle()))
                .collect(Collectors.toList());

        //if we find at least one entry than return true
        return resultBlogEntriesList;
    }
}
