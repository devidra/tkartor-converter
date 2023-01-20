package com.tkartor.converter.services;

import com.tkartor.converter.controllers.responses.Address;
import com.tkartor.converter.controllers.responses.Family;
import com.tkartor.converter.controllers.responses.People;
import com.tkartor.converter.controllers.responses.Person;
import com.tkartor.converter.controllers.responses.Phone;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@Service
public class ConverterService {

    public People convert(MultipartFile file) {
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        List<Person> persons = new ArrayList<>();

        List<String> lines = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .toList();

        boolean lastPersonFound = false;
        int start = 0;
        while (!lastPersonFound) {
            OptionalInt startPerson = IntStream.range(start, lines.size())
                    .filter(i -> "P".equals(lines.get(i).split("\\|")[0]))
                    .findFirst();

            if (startPerson.isPresent()) {
                OptionalInt endPerson = IntStream.range(startPerson.getAsInt() + 1, lines.size())
                        .filter(i -> "P".equals(lines.get(i).split("\\|")[0]))
                        .findFirst();

                if (endPerson.isPresent()) {
                    start = endPerson.getAsInt();
                    persons.add(convertPerson(lines.subList(startPerson.getAsInt(), endPerson.getAsInt() - 1)));
                } else {
                    persons.add(convertPerson(lines.subList(startPerson.getAsInt(), lines.size() - 1)));
                    lastPersonFound = true;
                }
            }
        }
        People people = new People();
        people.setPerson(persons);
        return people;
    }

    private Person convertPerson(List<String> personValues) {
        Person person = new Person();
        person.setFamily(new ArrayList<>());
        boolean firstFamily = false;
        Family family = null;
        for (String personValue : personValues) {
            String[] line = personValue.split("\\|");
            if (line[0].equals("P")) {
                person.setFirstname(line[1]);
                person.setLastname(line[2]);
            }

            if (line[0].equals("A")) {
                Address address = new Address();
                address.setStreet(line[1]);
                address.setCity(line[2]);
                address.setZipCode(line[3]);
                if (!firstFamily) {
                    person.setAddress(address);
                } else {
                    family.setAddress(address);
                }
            }

            if (line[0].equals("T")) {
                Phone phone = new Phone();
                phone.setMobile(line[1]);
                phone.setLandline(line[2]);
                if (!firstFamily) {
                    person.setPhone(phone);
                } else {
                    family.setPhone(phone);
                }
            }

            if (line[0].equals("F")) {
                firstFamily = true;
                if (family != null) {
                    person.getFamily().add(family);
                }
                family = new Family();
                family.setName(line[1]);
                family.setBorn(line[2]);
            }
        }
        if (family != null) {
            person.getFamily().add(family);
        }
        return person;
    }

}
