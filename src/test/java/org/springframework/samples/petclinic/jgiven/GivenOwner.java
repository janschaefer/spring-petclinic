package org.springframework.samples.petclinic.jgiven;

import com.tngtech.jgiven.annotation.ScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;

@JGivenStage
public class GivenOwner {

    @Autowired
    protected OwnerRepository ownerRepository;

    @ScenarioState
    private Owner givenOwner;

    public void an_owner_with_last_name(String lastName) {
        givenOwner = new Owner();
        givenOwner.setFirstName( "Karl" );
        givenOwner.setLastName( lastName );
        givenOwner.setTelephone( "08154711" );
        givenOwner.setAddress( "Beta Str. 13a" );
        givenOwner.setCity( "Unterföhring" );

        ownerRepository.save(givenOwner);

    }
}
