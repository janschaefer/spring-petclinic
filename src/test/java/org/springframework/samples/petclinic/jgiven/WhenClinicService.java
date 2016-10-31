package org.springframework.samples.petclinic.jgiven;

import com.tngtech.jgiven.annotation.ScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;

import java.util.Collection;

@JGivenStage
public class WhenClinicService {
    @Autowired
    protected ClinicService clinicService;

    @ScenarioState
    private Collection<Owner> foundOwners;

    public void searching_for(String searchTearm) {
        foundOwners = this.clinicService.findOwnerByLastName( searchTearm );
    }
}
