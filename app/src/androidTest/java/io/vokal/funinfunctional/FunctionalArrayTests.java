package io.vokal.funinfunctional;

import io.vokal.funinfunctional.functional.FunctionalArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

public class FunctionalArrayTests {

    @Test
    public void mappingIntegers() {
        FunctionalArray<Integer, Integer> ints = new FunctionalArray<>(Arrays.asList(7, 14, 21));
        ArrayList<Integer> mappedInts = ints.map(new FunctionalArray.MapFunction<Integer, Integer>() {
            @Override
            public Integer applyFunctionToItem(Integer item) {
                return item / 7;
            }
        });


        ArrayList<Integer> expectedInts = new ArrayList<>(Arrays.asList(1 ,2, 3));
        assertEquals(mappedInts, expectedInts);
    }

    @Test
    public void reducingIntegers() {
        FunctionalArray<Integer, Integer> ints = new FunctionalArray<>(Arrays.asList(11, 22, 33));

        Integer result = ints.reduce(0, new FunctionalArray.ReduceFunction<Integer, Integer>() {
            @Override
            public Integer addToInitialValue(Integer initialValue, Integer itemToAdd) {
                return initialValue + itemToAdd;
            }
        });

        Integer expected = 66;
        assertEquals(result, expected);
    }

    @Test
    public void filteringIntegers() {
        FunctionalArray<Integer, Integer> ints = new FunctionalArray<>(Arrays.asList(1, 2, 3, 4));

        ArrayList<Integer> evens = ints.filter(new FunctionalArray.FilterFunction<Integer>() {
            @Override
            public boolean resultShouldIncludeItem(Integer item) {
                return item % 2 == 0;
            }
        });

        ArrayList<Integer> expectedEvens = new ArrayList<>(Arrays.asList(2, 4));
        assertEquals(expectedEvens, evens);
    }
}
