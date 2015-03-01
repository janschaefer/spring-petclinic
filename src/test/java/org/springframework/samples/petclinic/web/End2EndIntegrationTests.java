package org.springframework.samples.petclinic.web;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.samples.petclinic.service.GivenDatabaseState;
import org.springframework.samples.petclinic.service.TestFixtures;
import org.springframework.samples.petclinic.util.SpringScenarioTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tngtech.jgiven.annotation.ProvidedScenarioState;

@ContextConfiguration( locations = { "classpath:spring/business-config.xml" } )
@ActiveProfiles( "spring-data-jpa" )
@RunWith( SpringJUnit4ClassRunner.class )
public class End2EndIntegrationTests extends SpringScenarioTest<GivenDatabaseState<?>, WhenPetClinicWeb<?>, ThenPetClinicWeb<?>> {
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
        when().the_main_page_is_opened();
        then().a_cute_image_with_a_dog_and_a_cat_is_shown();
    }

    @Test
    public void owner_page_shows_all_existing_owners() {
        given().owners_with_following_first_and_last_names(
            TestFixtures.sampleOwnerNames() );
        when().the_owners_search_page_is_opened()
            .and().for_lastname_$_is_searched( "Davis" );
        then();
    }
}
