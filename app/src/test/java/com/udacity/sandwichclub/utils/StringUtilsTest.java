package com.udacity.sandwichclub.utils;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void join() {
        List<String> abcd = Arrays.asList("a", "b", "c", "d");
        assertEquals(StringUtils.join(",", abcd), "a,b,c,d");
    }
}
