package org.springframework.samples.petclinic.etk16;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.ServiceTest;
import org.springframework.samples.petclinic.util.SpringScenarioTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration( locations = { "classpath:spring/business-config.xml" } )
@ActiveProfiles( { "spring-data-jpa", "inprocessdb" } )
@RunWith( DataProviderRunner.class )
@ServiceTest
public class PetClinicTests extends SpringScenarioTest<Object, Object, Object> {

    @Autowired
    protected ClinicService clinicService;

    @Autowired
    protected OwnerRepository ownerRepository;

    @Autowired
    protected PetRepository petRepository;

    @Test
    @Transactional
    public void shouldFindOwnerByLastName() {
        Owner givenOwner = new Owner();
        givenOwner.setFirstName( "Karl" );
        givenOwner.setLastName( "Ruhe" );
        givenOwner.setTelephone( "08154711" );
        givenOwner.setAddress( "Lammstraße 13" );
        givenOwner.setCity( "Karlsruhe" );
        ownerRepository.save( givenOwner );

        Collection<Owner> foundOwners = this.clinicService.findOwnerByLastName( givenOwner.getLastName() );

        assertThat( foundOwners ).hasSize( 1 );
        assertThat( foundOwners.iterator().next().getCity() ).isEqualTo( givenOwner.getCity() );
    }

}
