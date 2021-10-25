import com.sun.org.glassfish.gmbal.Description;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Dz3 {
    WebDriver driver;
    private Logger logger = (Logger) LogManager.getLogger(Dz3.class);

    @Before
    public void StartUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Driver is up");
    }
    @After
    public void End(){
        if(driver!=null)
            driver.quit();
    }

    @Test
    @Description("Открыть Chrome в headless режиме" +
            "Перейти на https://duckduckgo.com/" +
            "В поисковую строку ввести ОТУС" +
            "Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение")
    public void windows1()throws InterruptedException{
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://duckduckgo.com/");
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("ОТУС");
        driver.findElement(By.id("search_button_homepage")).click();
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение...",driver.findElement(By.cssSelector("a[href='https://otus.ru/']")).getText());
    }
    @Test
    @Description("Открыть Chrome в режиме киоска" +
            "Перейти на https:..." +
            "Нажать на любую картинку" +
            "Проверить что картинка открылась в модальном окне")
    public void windows2()throws InterruptedException{
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        driver.manage().window().fullscreen();
        driver.findElement(By.cssSelector("li[data-id='id-1']")).click();
        Assert.assertNotNull(driver.findElement(By.cssSelector("div[class='pp_content_container']")));
    }

    @Test
    @Description("Открыть Chrome в режиме полного экрана" +
            "Перейти на https://otus.ru" +
            "Авторизоваться под каким-нибудь тестовым пользователем" +
            "Вывести в лог все cookie")
    public void windows3()throws Exception{
        driver.get("https://otus.ru");
        driver.manage().window().maximize();
        String login = "kukunina_ey@interrao.ru";
        String password = "Aa!23456789";
        String locator = "button.header2__auth.js-open-modal";
        driver.findElement(By.cssSelector(locator)).click();
        driver.findElement(By.cssSelector(".js-email-input")).sendKeys(login);
        driver.findElement(By.cssSelector(".js-psw-input")).sendKeys(password);
        driver.findElement(By.cssSelector("div.new-input-line.new-input-line_last.new-input-line_relative")).click();
        logger.info("Log in");
        logger.info(driver.manage().getCookies());
    }
}
