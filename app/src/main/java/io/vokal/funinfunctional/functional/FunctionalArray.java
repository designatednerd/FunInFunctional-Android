package io.vokal.funinfunctional.functional;

import java.util.ArrayList;
import java.util.Collection;

public class FunctionalArray<T, U> extends ArrayList<T> {

    public FunctionalArray(Collection<? extends T> starter) {
        this.addAll(starter);
    }

    /*******
     * MAP *
     *******/

    public interface MapFunction<T, U> {
        U applyFunctionToItem(T item);
    }

    public ArrayList<U> map(MapFunction<T, U> aFunction) {
        ArrayList<U> mappedArray = new ArrayList<>();

        for (T item: this) {
            U result = aFunction.applyFunctionToItem(item);
            mappedArray.add(result);
        }

        return mappedArray;
    }

    /**********
     * FILTER *
     **********/

    public interface FilterFunction<T> {
        boolean resultShouldIncludeItem(T item);
    }

    public ArrayList<T> filter(FilterFunction<T> aFilterFunction) {
        ArrayList<T> resultArray = new ArrayList<>();

        for (T item: this) {
            if (aFilterFunction.resultShouldIncludeItem(item)) {
                resultArray.add(item);
            } //else, do nothing.
        }

        return resultArray;
    }

    /**********
     * REDUCE *
     **********/

    public interface ReduceFunction<T, U> {
        U addToInitialValue(U initialValue, T itemToAdd);
    }

    public U reduce(U initialValue, ReduceFunction<T, U> aReduceFunction) {
        U result = initialValue;
        for (T item: this) {
            result = aReduceFunction.addToInitialValue(result, item);
        }

        return result;
    }

//    /***********
//     * FLATMAP *
//     ***********/
//
//    private ArrayList<T> flatten(ArrayList<ArrayList<T>> aNestedList) {
//        ArrayList<T> flattenedList = new ArrayList<>();
//
//
//        for (ArrayList<T> list: aNestedList) {
//            flattenedList.addAll(list);
//        }
//
//        return flattenedList;
//    }
//
//    public U flatMap(MapFunction<T,U> aMapFunction) {
//
//        ArrayList<U> mapped = this.map(aMapFunction);
//
//    }
}
