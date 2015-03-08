package org.springframework.samples.petclinic.e2e;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.annotation.ProvidedScenarioState;

@ContextConfiguration( locations = { "classpath:spring/business-config.xml" } )
@ActiveProfiles( { "spring-data-jpa", "serverdb" } )
@RunWith( SpringJUnit4ClassRunner.class )
public class End2EndIntegrationTests {
    private static final String PORT = "9967";
    private static final String ROOT_URL = "http://localhost:" + PORT + "/petclinic/";

    @ProvidedScenarioState
    static WebDriver webDriver;

    @BeforeClass
    public static void setupWebDriver() {
        webDriver = new FirefoxDriver();
        webDriver.manage().window().setSize( new Dimension( 1280, 768 ) );
    }

    @AfterClass
    public static void closeWebDriver() {
        webDriver.close();
    }

    @Test
    public void main_page_shows_cute_dog_and_cat_image() {
        webDriver.get( ROOT_URL );
        List<WebElement> foundElements = webDriver.findElements( By.xpath( "//img/@src[contains(.,'pets.png')]/.." ) );
        Assertions.assertThat( foundElements ).as( "cute image could not be found" ).hasSize( 1 );
    }

}
