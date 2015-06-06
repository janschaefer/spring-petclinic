package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;

import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.Quoted;

public class WhenClinicService {

    @Autowired
    protected ClinicService clinicService;

    @ProvidedScenarioState
    protected Collection<Owner> ownersResult;

    public void searching_for_owners_with_last_name( @Quoted String lastName ) {
        ownersResult = this.clinicService.findOwnerByLastName( lastName );
    }

}
