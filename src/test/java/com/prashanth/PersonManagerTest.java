package com.prashanth;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonManagerTest {

    private static PersonManager personManager;

    @BeforeAll
    public static void init() {
        System.out.println("@BeforeAll called");
    }

    @AfterAll
    public static void destroy() {
        personManager = null;
        System.out.println("@AfterAll called");
    }

    @Test
    public void testMethod1(){
        System.out.println("@Test method 1 called");
    }

    @Test
    public void testMethod2(){
        System.out.println("@Test method 2 called");
    }

    @BeforeEach
    public void beforeEachMethod(){
        personManager = new PersonManager();
        System.out.println("@BeforeEach method called, new object created");
    }

    @AfterEach
    public void afterEachMethod(){
        System.out.println("@AfterEach method called");
    }

    @Test
    public void testCreatePerson(){
        personManager.addPerson("Prashanth", 36, "0123456789");
        Assertions.assertFalse(personManager.getPeople().isEmpty());
        Assertions.assertEquals(1, personManager.getPeople().size());
        Assertions.assertTrue(personManager.getPeople().stream().
                anyMatch(p -> p.getName().equals("Prashanth")
                        && p.getAge() == 36 && p.getPhoneNumber().equals("0123456789")));
    }

    @Test
    @DisplayName("Test Invalid Name")
    public void testInvalidName(){
        Assertions.assertThrows(RuntimeException.class, () -> personManager.addPerson(null, 35, "0123456789"));
    }

    @Test
    @DisplayName("Test Invalid Age")
    public void testInvalidAge(){
        Assertions.assertThrows(RuntimeException.class, () -> personManager.addPerson("Prashanth", null, "0123456789"));
    }

    @Test
    @DisplayName("Test Invalid Phone Number")
    public void testInvalidPhonenumber(){
        Assertions.assertThrows(RuntimeException.class, () -> personManager.addPerson("Prashanth", 36, null));
    }

    @Test
    public void testCreatePersonWithAssumptions(){
        System.out.println(System.getProperty("ENV"));
        personManager.addPerson("Ramu", 36, "0123456789");
        Assumptions.assumeFalse("PROD".equals(System.getProperty("ENV")));
        Assertions.assertEquals(1, personManager.getPeople().size());
    }

    @RepeatedTest(value = 5)
    public void repeatedTest(){
        personManager.addPerson("Ramu", 36, "0123456789");
        Assertions.assertEquals(1, personManager.getPeople().size());
    }

    @Test
    @EnabledOnOs(value = OS.MAC)
    @DisplayName("Test enabled on MAC")
    public void enabledOperatingSystem(){
        System.out.println("This test case is enabled on Mac OS");
    }

    @Test
    @DisabledOnOs(value = OS.LINUX)
    @DisplayName("Test disabled on Linux")
    public void disabledOperatingSystem(){
        System.out.println("This test case is disabled on Linux OS");
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ParameterizedNestedTestClass {

        @ParameterizedTest
        @ValueSource(strings = {"0123456789", "1123456789", "2123456789"})
        public void parameterizedTest(String phonenumber){
            personManager.addPerson("Ramu", 36, phonenumber);
            Assertions.assertEquals(1, personManager.getPeople().size());
        }

        @ParameterizedTest
        @MethodSource("getAge")
        public void parameterizedTest2(Integer age){
            personManager.addPerson("Raghava", age, "0123456789");
            Assertions.assertEquals(1, personManager.getPeople().size());
        }

        public List<Integer> getAge(){
            return Arrays.asList(45, 25, 95, 1);
        }

        @ParameterizedTest
        @CsvSource({"Raghava", "Madhava", "Sharana"})
        public void parameterizedTest3(String name){
            personManager.addPerson(name, 39, "0123456789");
            Assertions.assertTrue(personManager.getPeople().stream().anyMatch(person -> person.getName().equals(name)));
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/data.txt") // or data.csv
        public void parameterizedTest4(String phoneNumber){
            personManager.addPerson("Raghava", 50, phoneNumber);
            Assertions.assertEquals(1, personManager.getPeople().size());
        }
    }
}
