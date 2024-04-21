package ru.netology.HW_Gradle_4;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAppCardDelivery {
    private WebDriver driver;

    @Test
    public void SuccessfulForm() throws InterruptedException {
        open("http://localhost:9999/");

        $("[data-test-id='city'] input").setValue("Челябинск");

        //начало блок установки даты
        LocalDate varDate = LocalDate.now();
        varDate = varDate.plusDays(7);
        String stringDate = varDate.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(stringDate);
        //конец блока установки даты

        $("[data-test-id='name'] input").setValue("Лебедев-Кумач Сергей Петрович");
        $("[data-test-id='phone'] input").setValue("+79501231234");
        $("[data-test-id='agreement']").click();
        $(".grid-row button").click();


        SelenideElement el2 = $("[data-test-id='notification'] .notification__content");
        el2.shouldBe(visible, Duration.ofSeconds(15));
        el2.shouldHave(exactText("Встреча успешно забронирована на "+stringDate));
    }
}
