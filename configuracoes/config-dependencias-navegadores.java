// 1. Dependências no `pom.xml` (Maven) - Se estiver usando **Maven**, adicione:
// XML
<dependencies>
    <!-- Selenium Java -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.22.0</version>
    </dependency>

    <!-- WebDriverManager (gerencia os drivers automaticamente) -->
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.9.2</version>
    </dependency>

    <!-- JUnit ou TestNG, escolha um (aqui vai exemplo com JUnit 5) -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>


//JAVA
// 2. Classe Base para Configuração de Navegadores

//Você pode criar uma classe utilitária para inicializar o `WebDriver` conforme o navegador desejado:

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "safari":
                // SafariDriver já vem embutido no macOS, não precisa baixar driver
                driver = new SafariDriver();
                break;

            default:
                throw new IllegalArgumentException("Navegador não suportado: " + browser);
        }

        driver.manage().window().maximize();
        return driver;
    }
}

//JAVA
// 3. Exemplo de Teste com JUnit 5

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleTest {
    private WebDriver driver;

    @BeforeEach
    void setup() {
        // Troque o nome do navegador: "chrome", "firefox", "edge", "safari"
        driver = DriverFactory.createDriver("chrome");
    }

    @Test
    void testGoogleTitle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        assertEquals("Google", title, "Título da página deve ser Google");
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

/*Agora você tem:

* **Configuração automática** de drivers com WebDriverManager.
* **Suporte para Chrome, Firefox, Edge e Safari**.
* Estrutura base de testes com JUnit 5.*/


