package com.tkartor.converter.controllers.responses;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "people")
@XmlAccessorType(XmlAccessType.NONE)
public class People {
    @XmlElement(name = "person")
    List<Person> person;
}
