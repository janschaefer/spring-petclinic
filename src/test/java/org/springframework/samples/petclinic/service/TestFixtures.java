package org.springframework.samples.petclinic.service;

import static java.util.Arrays.asList;

import java.util.List;

public class TestFixtures {

    public static List<List<String>> sampleOwnerNames() {
        return asList(
            asList( "First Name", "Last Name" ),
            asList( "Harold", "Davis" ),
            asList( "Peter", "McTavish" ),
            asList( "Betty", "Davis" ) );
    }

}
