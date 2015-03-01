package org.springframework.samples.petclinic.web;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

public class ThenPetClinicWeb<SELF extends ThenPetClinicWeb<?>> extends Stage<SELF> {
    @ExpectedScenarioState
    protected WebDriver webDriver;

    public SELF a_cute_image_with_a_dog_and_a_cat_is_shown() {
        List<WebElement> foundElements = webDriver.findElements( By.tagName( "img" ) );
        boolean found = false;
        for( WebElement w : foundElements ) {
            if( w.getAttribute( "src" ).contains( "images/pets.png" ) ) {
                found = true;
            }
        }
        Assertions.assertThat( found ).as( "cute image could not be found" ).isTrue();
        return self();
    }
}
