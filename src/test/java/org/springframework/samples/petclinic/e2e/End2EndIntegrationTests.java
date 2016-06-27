package org.springframework.samples.petclinic.e2e;

import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.samples.petclinic.service.FirstAndLastName;
import org.springframework.samples.petclinic.service.GivenDatabaseState;
import org.springframework.samples.petclinic.service.TestFixtures;
import org.springframework.samples.petclinic.util.PetClinicSpringScenarioTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration( locations = { "classpath:spring/business-config.xml" } )
@ActiveProfiles( { "spring-data-jpa", "serverdb" } )
@E2ETest
public class End2EndIntegrationTests extends PetClinicSpringScenarioTest<GivenDatabaseState<?>, WhenPetClinicWeb<?>, ThenPetClinicWeb<?>> {
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
        then().the_resulting_owners_table_contains_exactly(
                new FirstAndLastName( "Betty", "Davis" ),
                new FirstAndLastName( "Harold", "Davis" ) );
    }
}
