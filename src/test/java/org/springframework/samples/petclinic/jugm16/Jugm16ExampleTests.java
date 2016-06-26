package org.springframework.samples.petclinic.jugm16;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.ServiceTest;
import org.springframework.samples.petclinic.util.SpringDataProviderRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration( locations = { "classpath:spring/jgiven-config.xml" } )
@ActiveProfiles( { "spring-data-jpa", "inprocessdb" } )
@RunWith( SpringDataProviderRunner.class )
@ServiceTest
public class Jugm16ExampleTests {

    @Autowired
    protected OwnerRepository ownerRepository;

    @Autowired
    protected ClinicService clinicService;

    @Test
    public void shouldFindOwnerByLastName() {
        Owner givenOwner = new Owner();
        givenOwner.setFirstName( "Karl" );
        givenOwner.setLastName( "Müller" );
        givenOwner.setTelephone( "08154711" );
        givenOwner.setAddress( "Lammstraße 13" );
        givenOwner.setCity( "Karlsruhe" );

        ownerRepository.save( givenOwner );

        Collection<Owner> foundOwners = this.clinicService.findOwnerByLastName( "Müller" );

        assertThat( foundOwners ).hasSize( 1 );
        assertThat( foundOwners.iterator().next().getId() ).isEqualTo( givenOwner.getId() );
    }

}
