package com.django.ugasoft.data;

import com.django.ugasoft.model.BlogEntry;
import com.django.ugasoft.util.DataGenerator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Data
public class BlogEntryDataProvider {

  /**
   * Default length of List Blog Entries
   */
  @Value("${blogEntry.defaultDataListLength}")
  private Integer defaultDataListLength;

  /**
   * Length of test data for Title and Slug
   */
  @Value("${blogEntry.dataLength}")
  private static int dataLength=10;

  /**
   * Length of test data for TxtMarkdown and Text
   */
  @Value("${blogEntry.dataTextLength}")
  private static int dataTextLength=50;

  private List<BlogEntry> blogEntryList;

  public BlogEntryDataProvider (Integer length){
    this.generateData(length);
  }

  /**
   * Generate data list of blog enties
   * @param lenght the size of list of blog enties. If null then the default value will be generated
   * @return list of blog enties
   */
  public void generateData(Integer lenght){
    lenght = lenght == null?defaultDataListLength:lenght;

    BlogEntry blogEntry;
    blogEntryList = new ArrayList<>(lenght);
    for (int i = 0; i < lenght; i++) {
      blogEntry = new BlogEntry();
      blogEntry.setTitle(generateBlogTitle());
      blogEntry.setSlug(generateBlogSlug());
      blogEntry.setTxtMarkdown(generateBlogTxtMarkdown());
      blogEntry.setText(generateBlogText());
      blogEntryList.add(blogEntry);
    }
  }

  /**
   * Generate blog Title with template "Title43565463456"
   *
   * @return
   */
  public String generateBlogTitle() {
    DataGenerator passwordGenerator = new DataGenerator.DataGeneratorBuilder()
            .useDigits(true)
            .useLower(false)
            .useUpper(false)
            .usePunctuation(false)
            .build();
    String generatedTitle = "Title" + passwordGenerator.generate(dataLength);
    return generatedTitle;
  }

  /**
   * Generate blog Slug with template "Slug43565463456"
   *
   * @return
   */
  public String generateBlogSlug() {
    DataGenerator passwordGenerator = new DataGenerator.DataGeneratorBuilder()
            .useDigits(true)
            .useLower(false)
            .useUpper(false)
            .usePunctuation(false)
            .build();
    String generatedSlug = "Slug" + passwordGenerator.generate(dataLength);
    return generatedSlug;
  }

  /**
   * Generate blog TxtMarkdown with template "TxtMarkdown4g3F5HJ6dfs5sdf46sdf"
   *
   * @return
   */
  public String generateBlogTxtMarkdown() {
    DataGenerator passwordGenerator = new DataGenerator.DataGeneratorBuilder()
            .useDigits(true)
            .useLower(true)
            .useUpper(true)
            .usePunctuation(false)
            .build();
    String generatedTitle = "TxtMarkdown" + passwordGenerator.generate(dataLength);
    return generatedTitle;
  }

  /**
   * Generate blog Text with template "Text<-4,! g/3*F5HJ6dfs5sdf46sdf"
   *
   * @return
   */
  public String generateBlogText() {
    DataGenerator passwordGenerator = new DataGenerator.DataGeneratorBuilder()
            .useDigits(true)
            .useLower(true)
            .useUpper(true)
            .usePunctuation(true)
            .build();
    String generatedTitle = "Text" + passwordGenerator.generate(dataLength);
    return generatedTitle;
  }
}
