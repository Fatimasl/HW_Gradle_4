package ru.netology.HW_Gradle_4;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        varDate = varDate.plusDays(1);
        String stringDate = varDate.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(stringDate);
        //конец блока установки даты

        $("[data-test-id='name'] input").setValue("Лебедев-Кумач Сергей Петрович");
        $("[data-test-id='phone'] input").setValue("+79501231234");
        $("[data-test-id='agreement']").click();
        $(".grid-row button").click();


        WebElement el2 = $("[data-test-id='notification'] .notification__title");
        ((SelenideElement) el2).shouldBe(visible, Duration.ofSeconds(15));
        String actual = el2.getText().trim();
        assertEquals("Успешно!", actual);

        Thread.sleep(20000);
    }
}
