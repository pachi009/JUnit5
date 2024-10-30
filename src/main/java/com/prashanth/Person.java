package com.prashanth;

public class Person {

    private String name;
    private Integer age;
    private String phoneNumber;

    Person(String name, Integer age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void validateName(){
        if(this.name.isBlank())
            throw new RuntimeException("Name cannot be null or empty");
    }

    public void validateAge(){
        if(this.age <= 0)
            throw new RuntimeException("Age must be greater than 0");
    }

    public void validatePhoneNumber() {
        if(this.phoneNumber.isBlank())
            throw new RuntimeException("Phone number cannot be null or empty");
        if (this.phoneNumber.length() != 10)
            throw new RuntimeException("Phone number must be 10 digits");
        if (!this.phoneNumber.matches("\\d+"))
            throw new RuntimeException("Phone number must contain digits only");
    }
}
