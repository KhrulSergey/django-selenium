package com.django.ugasoft.tests;

import com.django.ugasoft.Application;
import com.django.ugasoft.config.TestBase;
import com.django.ugasoft.data.BlogEntryDataProvider;
import com.django.ugasoft.model.BlogEntry;
import com.django.ugasoft.pages.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest(classes = Application.class)
public class LoginAndCRUDBlogEntryTest extends TestBase {

    //Properties
    @Value("${baseUrl}")
    private String baseURL = "https://igorakintev.ru/admin/";

    @Value("${blogListUrl}")
    private String blogListPageUrl;

    @Value("${testData.LoginPageTitle}")
    String LoginPageTitle;

    @Value("${testData.AdminHomePageHeader}")
    String AdminHomePageHeader;

    @Value("${testData.AdminHomePageTitle}")
    String AdminHomePageTitle;

    @Value("${testData.AddBlogEntryPageHeader}")
    String AddBlogEntryPageHeader;

    @Value("${testData.ChangeBlogEntryPageHeader}")
    String ChangeBlogEntryPageHeader;

    @Value("${testData.LogoutPageHeader}")
    String LogoutPageHeader;

    @Value("${existing.username}")
    String username;

    @Value("${existing.password}")
    String password;

    LoginPage loginPage;
    AdminHomePage homePage;
    AddBlogEntryPage blogEntryPage;
    BlogEntriesListPage blogEntriesListPage;
    LogoutPage logoutPage;

    BlogEntryDataProvider blogEntryDataProvider;

    private void openStartPage(){
        driver.get(baseURL);
    }

    private void openPublicBlogPage(){
        driver.get(blogListPageUrl);
    }

    /**
     * Тест на открытие страницы авторизации в административную панель
     * Test of Authorisation Page opened to Admin Panel
     *
     * Заходит на страницу - http://igorakintev.ru/admin/
     * Проверяет Title страницы
     * Go to - http://igorakintev.ru/admin/
     * Check Page Title
     */
    @Test(priority = 0)
    public void loginPageTitleVerifyTest() {
        openStartPage();
        loginPage = new LoginPage(driver);
        String title = loginPage.getPageTitle();
        Assert.assertEquals(title, LoginPageTitle);
    }

    /**
     * Тест на авторизацию
     * Test of authorisation
     *
     * Заполняет имя пользователя
     * Заполняет пароль
     * Нажимает кнопку Войти
     * Fill username
     * Fill password
     * Click on "Sign in" button
     */
    @Test(priority = 1)
    public void loginSuccessTest() {
        homePage = loginPage.login("selenium", "super_password");
        String title = loginPage.getPageTitle();
        Assert.assertNotEquals(title, LoginPageTitle);
    }

    /**
     * Тест на открытие административной панели
     * Test of Admin Panel opened
     *
     * Проверяет что на новой станице присутствует заголовок - “Панель управления”
     * Check the main Header of the page
     */
    @Test(priority = 2)
    public void adminHomePageTitleVerifyTest() {
        String pageHeader = homePage.getPageHeader();
        Assert.assertEquals(pageHeader, AdminHomePageHeader);
    }

    /**
     * Тест на переход на страницу создания новой записи в Блоге
     * Test of opening New Blog Entry Page
     *
     * Проверяет открытие новой станице с отличным Title от предыдущей
     * Check the main Title of the page
     */
    @Test(priority = 3)
    public void openAddBlogEntryPageTest() {
        Assert.assertTrue(homePage.getAddBlogEntryBtn().isDisplayed());
        blogEntryPage = homePage.clickOnAddBlogEntryBtn();
        String title = homePage.getPageTitle();
        Assert.assertNotEquals(title, AdminHomePageTitle);
    }

    /**
     * Тест на открытие страницы создания новой записи в Блоге
     * Test of New Blog Entry Page opened
     *
     * Проверяет что на новой станице присутствует заголовок - “Добавить entry”
     * Check the main Header of the page
     */
    @Test(priority = 4)
    public void addBlogEntryPageTitleVerifyTest() {
        String pageHeader = blogEntryPage.getPageHeader();
        Assert.assertEquals(pageHeader, AddBlogEntryPageHeader);
    }

    /**
     * Тест на заполнение (создание) новой записи в Блоге
     * Test of New Blog Entry creation
     *
     * Заполняет поле title, Slug, Text markdown, Text. Нажимает кнопку Сохранить
     * Fill data in inputs of title, Slug, Text markdown, Text. Click on Create
     */
    @Test(priority = 5)
    public void fillBlogEntryTest() {
        blogEntryDataProvider = new BlogEntryDataProvider(1);
        BlogEntry blogEntry = blogEntryDataProvider.getBlogEntryList().get(0);
        blogEntriesListPage = blogEntryPage.fillDataAndSubmit(blogEntry);
        //If creating of blog entry was success, then it will be redirected to home page
        String pageHeader = blogEntriesListPage.getPageHeader();
        Assert.assertEquals(pageHeader, ChangeBlogEntryPageHeader);
    }

    /**
     * Тест на существования созданной ранее записи в Блоге в административной панели
     * Test of previously created Blog Entry in Admin Panel
     *
     * Распознает все имеющиеся записи на странице. Сверяет с данными ранее созданной записи в Блоге.
     * Parse all Blog Entries data on the page. Match with previously created Blog Entry
     */
    @Test(priority = 6)
    public void checkBlogEntryInAdminPageExistTest() {
        BlogEntry blogEntry = blogEntryDataProvider.getBlogEntryList().get(0);
        //Check if result list of Blog Entries with the same Title and Slug is not empty
        Assert.assertNotNull(blogEntriesListPage.checkBlogEntryExist(blogEntry));
    }

    /**
     * Тест на существования созданной ранее записи в Блоге на публичной странце
     * Test of previously created Blog Entry on Public Page
     *
     * Переходит на страницу: http://igorakintev.ru/blog/
     * Удостоверяется что ранее созданная запись отображается на сайте
     * Go to - http://igorakintev.ru/blog/
     * Check Blog Entry data with previously created Blog Entry
     */
    @Test(priority = 7)
    public void checkBlogEntryInPublicPageExistTest() {
        openPublicBlogPage();
        PublicBlogPage publicBlogPage = new PublicBlogPage(driver);
        BlogEntry blogEntry = blogEntryDataProvider.getBlogEntryList().get(0);
        //Check if result list of Blog Entries with the same Title and Slug is not empty
        Assert.assertNotNull(publicBlogPage.checkBlogEntryExist(blogEntry));
    }

    /**
     * Тест на удаление ранее созданной записи в Блоге
     * Test of deleting previously created Blog Entry
     *
     * Открывает администратвную панель, переходит на страницу списка записей в блоге. Удаляет ранее созданную запись.
     * Go to  admin panel, goes to the blog entries list page. Deletes a previously created entry.
     */
    @Test(priority = 8)
    public void deleteBlogEntryTest() {
        openStartPage();
        homePage.blogEntriesListBtnClick();
        BlogEntry blogEntry = blogEntryDataProvider.getBlogEntryList().get(0);
        blogEntriesListPage = blogEntriesListPage.deleteBlogEntry(blogEntry);
        String pageHeader = blogEntriesListPage.getPageHeader();
        Assert.assertEquals(pageHeader, ChangeBlogEntryPageHeader);
    }

    /**
     * Тест на выход из административной панели
     * Test of Logout from Admin Panel
     *
     * Нажимает на кнопку выхода и проверяет заголовок страницы.
     * Click on exit button and checks the page header.
     */
    @Test(priority = 9)
    public void logoutTest() {
        logoutPage = blogEntriesListPage.LogoutBtnClick();
        String pageHeader = logoutPage.getPageHeader();
        Assert.assertEquals(pageHeader, LogoutPageHeader);
    }

}
