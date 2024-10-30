package com.prashanth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class PersonTest {
    Person person;

    @BeforeEach
    public void init(){
        person = new Person("Raj", 85, "0123456789");
    }

    @ParameterizedTest
//    @ValueSource(strings = {"1Ramanna", "1Bheemanna", "1Laxmanna"})
//    @MethodSource("getNames")
//    @CsvSource({"2Ramanna", "2Bheemanna", "2Laxmanna"})
    @CsvFileSource(resources = "/names.csv", numLinesToSkip = 1)
    public void user_should_be_at_least_18(String name, int age) {
        System.out.println("Name: " +name +" Age: " +age);
        assertThat(name).endsWith("anna");
        assertThat(age).isGreaterThanOrEqualTo(18);
        assertThat(age).isPositive();
    }

    static List<String> getNames(){
        return Arrays.asList("3Ramanna", "3Bheemanna", "3Laxmanna");
    }

    @Test
    public void user_should_be_raj(){
        assertThat(person.getName()).isEqualTo("Raj");
    }
}
