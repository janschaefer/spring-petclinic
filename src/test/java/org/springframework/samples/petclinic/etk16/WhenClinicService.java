package org.springframework.samples.petclinic.etk16;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.Quoted;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;

import java.util.Collection;

@JGivenStage
public class WhenClinicService extends Stage<WhenClinicService> {

    @Autowired
    protected ClinicService clinicService;

    @ProvidedScenarioState
    private Collection<Owner> foundOwners;

    public void finding_owners_by_last_name( @Quoted String lastName ) {
        foundOwners = this.clinicService.findOwnerByLastName( lastName );

    }
}
