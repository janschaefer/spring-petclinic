package org.springframework.samples.petclinic.service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.tngtech.jgiven.annotation.IsTag;

@IsTag
@Retention( RetentionPolicy.RUNTIME )
public @interface FeatureOwners {

}
