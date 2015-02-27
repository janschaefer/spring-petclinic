package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.springframework.samples.petclinic.model.Owner;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;

public class ThenClinicService {

    @ExpectedScenarioState
    protected Collection<Owner> ownersResult;

    public void the_result_should_exactly_contain_$_owners( int numberOfOwners ) {
        Assertions.assertThat( ownersResult ).hasSize( numberOfOwners );
    }

}
