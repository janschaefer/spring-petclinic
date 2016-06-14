package org.springframework.samples.petclinic.etk16;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.springframework.samples.petclinic.model.Owner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class ThenClinicService {

    @ExpectedScenarioState
    private Owner givenOwner;

    @ExpectedScenarioState
    private Collection<Owner> foundOwners;

    public void exactly_the_given_owner_is_found() {
        assertThat( foundOwners ).hasSize( 1 );
        assertThat( foundOwners.iterator().next().getId() ).isEqualTo( givenOwner.getId() );

    }
}
