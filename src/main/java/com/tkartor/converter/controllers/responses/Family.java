package com.tkartor.converter.controllers.responses;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "family")
@XmlAccessorType(XmlAccessType.NONE)
public class Family {
    @XmlElement
    String name;
    @XmlElement
    String born;
    @XmlElement
    Address address;
    @XmlElement
    Phone phone;
}
