import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallbackTest {
    
    @BeforeEach
    public void openForm() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldSubmitRequest() {
        $("[data-test-id='name'] input").setValue("Симонов Андрей");
        $("[data-test-id='phone'] input").setValue("+79508556982");
        $("[data-test-id='agreement']").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void nameFieldValidation() {
        $("[data-test-id='name'] input").setValue("Ivan trukchachev");
        $("[data-test-id='phone'] input").setValue("+79508556982");
        $("[data-test-id='agreement']").click();
        $("[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void phoneFieldValidation() {
        $("span[data-test-id='name'] input").setValue("Симонов Андрей");
        $("span[data-test-id='phone'] input").setValue("+7950855698");
        $("[data-test-id='agreement']").click();
        $("[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void checkboxValidation() {
        $("[data-test-id='name'] input").setValue("Симонов Андрей");
        $("[data-test-id='phone'] input").setValue("+79508556982");
        $("[type=button]").click();
        $("[data-test-id='agreement'].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    public void notFillingInTheNameField() {
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+79508556982");
        $("[data-test-id='agreement']").click();
        $("[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void notFillingInThePhoneField() {
        $("[data-test-id='name'] input").setValue("Симонов Андрей");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement']").click();
        $("[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void enteringLastNameWithAHyphen() {
        $("[data-test-id='name'] input").setValue("Муравьев-Трухачев Сергей");
        $("[data-test-id='phone'] input").setValue("+79508556982");
        $("[data-test-id='agreement']").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
