package org.springframework.samples.petclinic.web;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.samples.petclinic.service.GivenDatabaseState;
import org.springframework.samples.petclinic.util.SpringScenarioTest;

import com.tngtech.jgiven.annotation.ProvidedScenarioState;

public class End2EndTests extends SpringScenarioTest<GivenDatabaseState<?>, WhenPetClinicWeb, ThenDatabaseState> {

    @ProvidedScenarioState
    static WebDriver webDriver;

    @BeforeClass
    public static void setupWebDriver() {
        webDriver = new HtmlUnitDriver();
        webDriver.manage().window().setSize( new Dimension( 1280, 768 ) );
    }

    @AfterClass
    public static void closeWebDriver() {
        webDriver.close();
    }

}
