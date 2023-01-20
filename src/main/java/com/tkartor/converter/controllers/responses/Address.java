package com.tkartor.converter.controllers.responses;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.NONE)
public class Address {
    @XmlElement
    String street;
    @XmlElement
    String city;
    @XmlElement
    String zipCode;
}
