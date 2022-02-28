package ru.netology.javacore;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodosTests {

    Todos sut;

    @BeforeEach
    public void startEach() {
        System.out.println("One test started");
        sut = new Todos();
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finishEach() {
        System.out.println("One test finished");
    }

    @AfterAll
    public static void finished() {
        System.out.println("Tests finished");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Подъем", "Зарядка", "Завтрак"})
    public void testAddTask(String tasks) {
        List<String> list = new ArrayList<>(List.of(tasks));

        sut.addTask(tasks);
        int i = 0;

        assertEquals(list.get(i), sut.getTasksList().get(i));

        i++;
    }

    @Test
    public void test1RemoveTask() {
        sut.addTask("Подъем");
        sut.addTask("Зарядка");
        sut.addTask("Завтрак");

        sut.removeTask("Зарядка");

        assertEquals(2, sut.getTasksList().size());
    }

    @Test
    public void test2RemoveTask() {
        sut.addTask("Подъем");
        sut.addTask("Зарядка");
        sut.addTask("Завтрак");

        sut.removeTask("Другое");

        assertFalse (sut.isTaskStatus());
    }
}
