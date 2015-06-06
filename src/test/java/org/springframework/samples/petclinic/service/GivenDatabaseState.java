package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.AfterStage;
import com.tngtech.jgiven.annotation.ExtendedDescription;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.Table;

public class GivenDatabaseState<SELF extends GivenDatabaseState<?>> extends Stage<SELF> {
    @Autowired
    PetRepository petRepository;

    @Autowired
    OwnerRepository ownerRepostitory;

    @ProvidedScenarioState
    Pet pet;

    @ProvidedScenarioState
    Owner latestOwner;

    @ProvidedScenarioState
    List<Owner> owners = new ArrayList<Owner>();

    private List<PetType> petTypes;

    public SELF an_owner() {
        latestOwner = new Owner();
        latestOwner.setFirstName( "Harold" );
        latestOwner.setLastName( "Davis" );
        latestOwner.setTelephone( "6085553198" );
        latestOwner.setAddress( "563 Friendly St." );
        latestOwner.setCity( "Windsor" );
        owners.add( latestOwner );
        return self();
    }

    public SELF last_name( String lastName ) {
        latestOwner.setLastName( lastName );
        return self();
    }

    public SELF owners_with_following_first_and_last_names( @Table List<List<String>> firstAndLastNames ) {
        for( List<String> firstLastName : firstAndLastNames.subList( 1, firstAndLastNames.size() ) ) {
            an_owner_named_$_$( firstLastName.get( 0 ), firstLastName.get( 1 ) );
        }
        return self();
    }

    public SELF an_owner_named_$_$( String firstName, String lastName ) {
        an_owner();
        latestOwner.setFirstName( firstName );
        latestOwner.setLastName( lastName );
        return self();
    }

    @ExtendedDescription( "A lizard with name Leo" )
    public void a_pet() {
        pet = new Pet();
        pet.setName( "Leo" );
        pet_type( KnownPetTypes.LIZARD );
    }

    public void pet_type( KnownPetTypes petType ) {
        pet.setType( getPetType( petType ) );
    }

    private PetType getPetType( KnownPetTypes petTypeName ) {
        if( petTypes == null ) {
            petTypes = petRepository.findPetTypes();
        }
        for( PetType petType : petTypes ) {
            if( petType.getName().equals( petTypeName.name ) ) {
                return petType;
            }
        }
        return null;
    }

    @AfterStage
    protected void persist() {
        if( pet != null ) {
            petRepository.save( pet );
        }

        for( Owner owner : owners ) {
            ownerRepostitory.save( owner );
        }
    }

    /**
     * The pet types are predefined in the database and cannot be modified
     */
    public enum KnownPetTypes {
        CAT( "cat" ),
        DOG( "dog" ),
        LIZARD( "lizard" ),
        SNAKE( "snake" ),
        BIRD( "bird" ),
        HAMSTER( "hamster" );

        private final String name;

        private KnownPetTypes( String name ) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
