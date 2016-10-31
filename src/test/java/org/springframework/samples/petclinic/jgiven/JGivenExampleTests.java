package org.springframework.samples.petclinic.jgiven;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.ServiceTest;
import org.springframework.samples.petclinic.util.SpringDataProviderRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration( locations = { "classpath:spring/jgiven-config.xml" } )
@ActiveProfiles( { "spring-data-jpa", "inprocessdb" } )
@RunWith( SpringDataProviderRunner.class )
@ServiceTest
public class JGivenExampleTests
        extends SpringScenarioTest<GivenOwner, WhenClinicService, ThenSearchResult> {

    @Test
    public void shouldFindOwnerByLastName() {
        String lastName = "Müller";

        given().an_owner_with_last_name(lastName);

        when().searching_for(lastName);

        then().exactly_$_owner_is_found(1)
          .and().the_owner_is_the_given_one();
    }

}
