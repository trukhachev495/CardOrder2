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
    public void shouldSubmitRequest(){
        $("span[data-test-id='name'] input").setValue("Симонов Андрей");
        $("span[data-test-id='phone'] input").setValue("+79508556982");
        $("[data-test-id=\"agreement\"]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }


}
