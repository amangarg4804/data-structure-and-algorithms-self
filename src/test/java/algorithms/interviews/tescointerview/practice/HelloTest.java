package algorithms.interviews.tescointerview.practice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTest {

    @Test
    void get() {
        Hello hello = new Hello();
        assertEquals("hello", hello.get("hello"));
    }
}