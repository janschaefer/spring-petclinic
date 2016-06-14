package org.springframework.samples.petclinic.etk16;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.samples.petclinic.service.ServiceTest;
import org.springframework.samples.petclinic.util.SpringDataProviderRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;

@ContextConfiguration( locations = { "classpath:spring/jgiven-config.xml" } )
@ActiveProfiles( { "spring-data-jpa", "inprocessdb" } )
@RunWith( SpringDataProviderRunner.class )
@ServiceTest
public class PetClinicTests extends SpringScenarioTest<GivenOwner, WhenClinicService, ThenClinicService> {

    @Test
    @Transactional
    public void shouldFindOwnerByLastName() {

        String lastName = "Ruhe";

        given().an_owner()
                .with().last_name( lastName );

        when().finding_owners_by_last_name( lastName );

        then().exactly_the_given_owner_is_found();
    }

}
