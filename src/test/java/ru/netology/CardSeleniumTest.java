package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardSeleniumTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void test1() { // Заполнение формы ФИО с пробелом
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79051122323");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();
        WebElement result = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        assertTrue(result.isDisplayed());

//        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", result.getText().trim());

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = result.getText().trim();

        assertEquals(expected, actual);
    }

    @Test
    void test2() { // Заполнение формы ФИО через дефис
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров-Сидоров Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79051122323");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        WebElement result = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        assertTrue(result.isDisplayed());

//        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", result.getText().trim());

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = result.getText().trim();

        assertEquals(expected, actual);
    }

    @Test
    void test3() { // Заполнение формы ФИО только имя
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79051122323");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        WebElement result = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        assertTrue(result.isDisplayed());

//        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", result.getText().trim());

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = result.getText().trim();

        assertEquals(expected, actual);
    }

    @Test
    void test4() { // Заполнение формы ФИО на английском
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Peter Petrov");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79051122323");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        WebElement result = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"));
        assertTrue(result.isDisplayed());

        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", result.getText().trim());

//        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
//        String actual = result.getText().trim();
//
//        assertEquals(expected, actual);
    }

    @Test
    void test5() { // Заполнение формы телефон через 8
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Петр");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("89051122323");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        WebElement result = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"));
        assertTrue(result.isDisplayed());

        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", result.getText().trim());

    }

    @Test
    void test6() { // Заполнение формы телефон 10 цифр
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Петр");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7905112232");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        WebElement result = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"));
        assertTrue(result.isDisplayed());

        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", result.getText().trim());

    }

    @Test
    void test7() { // Заполнение формы телефон 12 цифр
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Петр");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+790511223235");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        WebElement result = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"));
        assertTrue(result.isDisplayed());

        Assertions.assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", result.getText().trim());

    }

    @Test
    void test8() { // Заполнение формы не заполнено ФИО

        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79051122323");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        WebElement result = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub"));
        assertTrue(result.isDisplayed());

        Assertions.assertEquals("Поле обязательно для заполнения", result.getText().trim());

    }

    @Test
    void test9() { // Заполнение формы телефон не заполнен
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Петр");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        WebElement result = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub"));
        assertTrue(result.isDisplayed());

        Assertions.assertEquals("Поле обязательно для заполнения", result.getText().trim());

    }

    @Test
    void test10() { // Заполнение формы флажок согласия не стоит
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Петр");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79051122323");
        driver.findElement(By.cssSelector("button")).click();
        WebElement result = driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid .checkbox__text"));
        assertTrue(result.isDisplayed());

        Assertions.assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй", result.getText().trim());

    }

}
