package com.prashanth;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PersonManager {
    Map<String, Person> people = new ConcurrentHashMap <String, Person>();

    public void addPerson(String name, Integer age, String phoneNumber) {
        Person person = new Person(name, age, phoneNumber);
        validatePerson(person);
        checkIfPersonExists(person);
        people.put(generateKey(person), person);
    }

    public Collection<Person> getPeople() {
        return people.values();
    }

    private void checkIfPersonExists(Person person) {
        if(people.containsKey(generateKey(person)))
            throw new RuntimeException("Person already exists");
    }

    private void validatePerson(Person person) {
        person.validateName();
        person.validateAge();
        person.validatePhoneNumber();
    }

    public String generateKey(Person person) {
        return person.getName() + person.getAge() + person.getPhoneNumber();
    }
}
