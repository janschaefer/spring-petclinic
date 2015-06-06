package org.springframework.samples.petclinic.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

public class WhenPetClinicWeb<SELF extends WhenPetClinicWeb<?>> extends Stage<SELF> {
    private static final String PORT = "9967";
    private static final String ROOT_URL = "http://localhost:" + PORT + "/petclinic/";

    @ExpectedScenarioState
    protected WebDriver webDriver;

    public void the_main_page_is_opened() {
        openPage( "" );
    }

    private void openPage( String page ) {
        webDriver.get( ROOT_URL + page );
    }

    public SELF the_owners_search_page_is_opened() {
        openPage( "owners/find.html" );
        return self();
    }

    public SELF for_lastname_$_is_searched( String lastName ) {
        WebElement searchElement = webDriver.findElement( By.xpath( "//input[@id='lastName']" ) );
        searchElement.sendKeys( lastName );
        webDriver.findElement( By.id( "search-owner-form" ) ).submit();
        return self();
    }

}
