package chap05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LifecycleTest {
    public LifecycleTest() {
        System.out.println("new LifecycleTest");
    }

    @BeforeEach
    void setUp() {
        System.out.println("set up!!");
    }

    @Test
    void a() {
        System.out.println("AAA");
    }

    @Test
    void b() {
        System.out.println("BBB");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tear down!!");
    }

}
