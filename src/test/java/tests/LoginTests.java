package tests;

import base.TestBase;
import pages.LoginPage;
import pages.ProductsPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Feature("Логин")
public class LoginTests extends TestBase {

    @Test(description = "Успешный логин")
    @Story("Успешный логин")
    public void testSuccessfulLogin() {
        logStep("Тест успешного логина");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        assertEquals(productsPage.getPageTitle(), "Products", "Заголовок страницы не соответствует");

        logStep("Успешный вход выполнен");
    }

    @Test(description = "Неверный логин")
    @Story("Неверный логин")
    public void testInvalidLogin() {
        logStep("Тест с неверными данными");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrong_user", "wrong_pass");

        assertTrue(loginPage.isErrorMessageDisplayed(), "Сообщение об ошибке не отобразилось");
        assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Текст ошибки не соответствует");

        logStep("Ошибка отобразилась корректно");
    }

    @Test(description = "Пустые поля логин и пароль")
    @Story("Пустые поля")
    public void testEmptyFields() {
        logStep("Тест с пустыми полями");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();

        assertTrue(loginPage.isErrorMessageDisplayed(), "Сообщение об ошибке не отобразилось");
        assertTrue(loginPage.getErrorMessage().contains("Username is required"),
                "Текст ошибки не соответствует");

        logStep("Ошибка отобразилась корректно");
    }
}