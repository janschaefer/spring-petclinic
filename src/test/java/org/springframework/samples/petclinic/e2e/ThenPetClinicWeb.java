package org.springframework.samples.petclinic.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.samples.petclinic.service.FirstAndLastName;

import com.tngtech.jgiven.CurrentStep;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.AfterStage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Table;
import com.tngtech.jgiven.attachment.Attachment;
import com.tngtech.jgiven.attachment.MediaType;

public class ThenPetClinicWeb<SELF extends ThenPetClinicWeb<?>> extends Stage<SELF> {
    @ExpectedScenarioState
    protected WebDriver webDriver;

    @ExpectedScenarioState
    protected CurrentStep currentStep;

    public SELF a_cute_image_with_a_dog_and_a_cat_is_shown() {
        List<WebElement> foundElements = webDriver.findElements( By.xpath( "//img/@src[contains(.,'pets.png')]/.." ) );
        Assertions.assertThat( foundElements ).as( "cute image could not be found" ).hasSize( 1 );
        return self();
    }

    public void the_resulting_owners_table_contains_exactly( @Table FirstAndLastName... owners ) {
        List<WebElement> rows = webDriver.findElements( By.xpath( "//table[@id='owners']/tr" ) );
        assertThat( rows ).hasSize( owners.length );
    }

    @AfterStage
    public void takeScreenshot() {
        String base64 = ( (TakesScreenshot) webDriver ).getScreenshotAs( OutputType.BASE64 );
        currentStep.addAttachment( Attachment.fromBase64( base64, MediaType.PNG ).withTitle( "Screenshot" ) );
    }

}
