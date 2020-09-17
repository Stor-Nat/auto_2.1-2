package ru.netology;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CallBackTest {

    @Test
    void shouldReturnErrorNameLat() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Anna");
        form.$("[data-test-id=phone] input").setValue("+79001234567");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();

        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldReturnErrorNoName() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79001234567");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();

        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldReturnErrorPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Анна Иванова");
        form.$("[data-test-id=phone] input").setValue("89001234567");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();

        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldReturnErrorNoPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Анна Иванова");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();

       $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно  для заполнения"));
    }

    @Test
    void shouldReturnErrorNoCheckbox() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Анна Иванова");
        form.$("[data-test-id=phone] input").setValue("+79001234567");
        form.$("[type=button]").click();

        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

}
