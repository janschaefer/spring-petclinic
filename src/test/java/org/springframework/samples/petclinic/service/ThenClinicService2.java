package org.springframework.samples.petclinic.service;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.assertj.core.api.Assertions;
import org.springframework.samples.petclinic.model.Owner;

import java.util.Collection;

public class ThenClinicService2 {

    @ExpectedScenarioState
    protected Collection<Owner> ownersResult;

    public void the_result_should_exactly_contain_$_owners( int numberOfOwners ) {
        Assertions.assertThat( ownersResult ).hasSize( numberOfOwners );
    }

}
