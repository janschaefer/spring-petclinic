package org.springframework.samples.petclinic.jgiven;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.springframework.samples.petclinic.model.Owner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class ThenSearchResult extends Stage<ThenSearchResult> {

    @ScenarioState
    private Collection<Owner> foundOwners;

    @ScenarioState
    private Owner givenOwner;

    public ThenSearchResult exactly_$_owner_is_found(int i) {
        assertThat(foundOwners).hasSize(i);
        return this;
    }

    public void the_owner_is_the_given_one() {
        assertThat(foundOwners.iterator().next().getId()).isEqualTo(givenOwner.getId());
    }
}
