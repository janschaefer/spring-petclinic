package org.springframework.samples.petclinic.etk16;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.AfterStage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.Quoted;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;

@JGivenStage
public class GivenOwner extends Stage<GivenOwner> {

    @ProvidedScenarioState
    private Owner givenOwner;

    @Autowired
    protected OwnerRepository ownerRepository;

    public GivenOwner an_owner() {
        givenOwner = new Owner();
        givenOwner.setFirstName( "Karl" );
        givenOwner.setLastName( "Heinz" );
        givenOwner.setTelephone( "08154711" );
        givenOwner.setAddress( "Lammstraße 13" );
        givenOwner.setCity( "Karlsruhe" );

        return self();
    }

    public GivenOwner last_name( @Quoted String lastName ) {
        givenOwner.setLastName( lastName );
        return self();
    }

    @AfterStage
    protected void saveOwner() {
        ownerRepository.save( givenOwner );
    }
}
