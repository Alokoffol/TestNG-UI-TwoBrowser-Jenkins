package tests;

import base.TestBase;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Feature("Корзина")
public class CartTests extends TestBase {

    @Test(description = "Добавление товара в корзину")
    @Story("Добавление товара")
    public void testAddProductToCart() {
        logStep("1. Логин");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        assertEquals(productsPage.getPageTitle(), "Products");

        logStep("2. Добавляем товар в корзину");
        productsPage.addFirstProductToCart();
        assertEquals(productsPage.getCartItemCount(), 1, "Счётчик корзины не обновился");

        logStep("3. Переходим в корзину и проверяем");
        productsPage.goToCart();
        CartPage cartPage = new CartPage(driver);

        assertTrue(cartPage.isPageLoaded(), "Страница корзины не загрузилась");
        assertEquals(cartPage.getItemCount(), 1, "Товар не появился в корзине");

        logStep("✅ Товар успешно добавлен в корзину");
    }

    @Test(description = "Удаление товара из корзины")
    @Story("Удаление товара")
    public void testRemoveProductFromCart() {
        logStep("1. Логин и добавление товара");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        assertEquals(cartPage.getItemCount(), 1, "Товар не добавился");

        logStep("2. Удаляем товар");
        cartPage.removeFirstItem();

        logStep("3. Проверяем, что корзина пуста");
        assertTrue(cartPage.isCartEmpty(), "Корзина должна быть пустой после удаления");

        logStep("✅ Товар успешно удалён из корзины");
    }

    @Test(description = "Продолжить покупки из корзины")
    @Story("Продолжить покупки")
    public void testContinueShopping() {
        logStep("1. Логин и добавление товара");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isPageLoaded());

        logStep("2. Нажимаем 'Continue Shopping'");
        cartPage.clickContinueShopping();

        logStep("3. Проверяем, что вернулись на страницу товаров");
        assertEquals(productsPage.getPageTitle(), "Products", "Не вернулись на страницу товаров");
        assertTrue(productsPage.isProductListDisplayed(), "Список товаров не отображается");

        logStep("✅ Успешное возвращение к покупкам");
    }

    @Test
    public void testForceFailWithScreenshot() {
        logStep("Этот тест специально упадет для проверки скриншота");

        // Проверка, которая упадет
        assertEquals(1, 2, "Специально сломали тест для проверки скриншота");
    }
}