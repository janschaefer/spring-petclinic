package org.springframework.samples.petclinic.service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.tngtech.jgiven.annotation.IsTag;

@IsTag( description = "Scenarios that are tested against the service layer" )
@Retention( RetentionPolicy.RUNTIME )
public @interface ServiceTest {}
