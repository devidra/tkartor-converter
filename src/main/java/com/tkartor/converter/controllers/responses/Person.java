package com.tkartor.converter.controllers.responses;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.NONE)
public class Person {
    @XmlElement
    String firstname;
    @XmlElement
    String lastname;
    @XmlElement
    Address address;
    @XmlElement
    Phone phone;
    @XmlElement
    List<Family> family;
}
