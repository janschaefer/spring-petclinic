package org.springframework.samples.petclinic.e2e;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.tngtech.jgiven.annotation.IsTag;

@IsTag( description = "Scenarios that are tested end-to-end against the system" )
@Retention( RetentionPolicy.RUNTIME )
public @interface E2ETest {}
