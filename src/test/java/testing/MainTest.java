package testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private Main main;

    @BeforeEach
    public void init() {
        main = new Main();
    }

    @Test
    public void arrMaker() {
        int[] a = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        Integer[] b = {1, 7};

        Assertions.assertArrayEquals(b, main.arrayMaker(a));
    }
    @Test
    public void arrMaker1() {
        int[] a = {1, 2, 4, 4, 2, 3, 4};
        Integer[] b = {};

        Assertions.assertArrayEquals(b, main.arrayMaker(a));
    }
    @Test
    public void arrMaker3() {
        int[] a = {};
        Integer[] b = {1, 7};

        Assertions.assertThrows(RuntimeException.class, () -> {
            Assertions.assertArrayEquals(b, main.arrayMaker(a));
        });
    }
    @Test
    public void arrMaker4() {
        int[] a = {1, 2, 3, 5, 6, 7, 8, 9,};
        Integer[] b = {1, 2, 3, 5, 6, 7, 8, 9};

        Assertions.assertThrows(RuntimeException.class, () -> {
            Assertions.assertArrayEquals(b, main.arrayMaker(a));
        });
    }

    @Test
    public void arrContains() {
        int[] a = {1, 1, 4, 4};

        Assertions.assertEquals(true, main.arrayContains(a));
    }
    @Test
    public void arrContains2() {
        int[] a = {2, 1, 1, 4};

        Assertions.assertEquals(false, main.arrayContains(a));
    }
    @Test
    public void arrContains3() {
        int[] a = { 1, 1, 1, 1};

        Assertions.assertEquals(false, main.arrayContains(a));
    }
    @Test
    public void arrContains4() {
        int[] a = { 4, 4, 4, 4};

        Assertions.assertEquals(false, main.arrayContains(a));
    }

}