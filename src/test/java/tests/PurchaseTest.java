package tests;

import base.TestBase;
import pages.*;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Feature("Полный путь покупки")
public class PurchaseTest extends TestBase {

    @Test(description = "Полный путь покупки")
    @Story("Полный путь покупки")
    public void testCompletePurchase() {
        logStep("1. Логин");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        assertEquals(productsPage.getPageTitle(), "Products", "Не удалось войти");

        logStep("2. Добавляем товар в корзину");
        productsPage.addFirstProductToCart();
        assertEquals(productsPage.getCartItemCount(), 1, "Товар не добавился в корзину");

        logStep("3. Переходим в корзину");
        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isPageLoaded(), "Страница корзины не загрузилась");
        assertEquals(cartPage.getItemCount(), 1, "Некорректное количество товаров в корзине");

        logStep("4. Начинаем оформление");
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCheckoutInfo("John", "Doe", "12345");

        logStep("5. Подтверждаем заказ");
        assertTrue(checkoutPage.isOverviewLoaded(), "Страница подтверждения не загрузилась");
        checkoutPage.clickFinish();

        logStep("6. Проверяем успешное завершение");
        assertEquals(checkoutPage.getCompleteHeader(), "Thank you for your order!",
                "Заказ не был успешно оформлен");

        logStep("✅ Покупка успешно завершена");
    }
}