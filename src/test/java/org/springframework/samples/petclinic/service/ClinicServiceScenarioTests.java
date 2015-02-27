package org.springframework.samples.petclinic.service;

import static java.util.Arrays.asList;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.samples.petclinic.util.SpringScenarioTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;

@ContextConfiguration( locations = { "classpath:spring/business-config.xml" } )
@ActiveProfiles( "spring-data-jpa" )
@RunWith( DataProviderRunner.class )
public class ClinicServiceScenarioTests extends SpringScenarioTest<GivenDatabaseState<?>, WhenClinicService, ThenClinicService> {

    @Test
    @Transactional
    @DataProvider( {
        "Davis, 2",
        "McTavish, 1",
        "Coleman, 0" } )
    public void clinic_service_should_find_owners_by_last_name( String searchName, int expectedOwnerCount ) {

        given().owners_with_following_first_and_last_names( asList(
            asList( "First Name", "Last Name" ),
            asList( "Harold", "Davis" ),
            asList( "Peter", "McTavish" ),
            asList( "Betty", "Davis" ) ) );
        when().searching_for_owners_with_last_name( searchName );
        then().the_result_should_exactly_contain_$_owners( expectedOwnerCount );

    }

}
