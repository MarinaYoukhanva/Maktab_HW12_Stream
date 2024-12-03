package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        record groupedNames(Integer length, Integer count, List<String> names) {
        }

        List<String> names = Arrays.asList("Amir", "Hatef", "Mehran", "Mojtaba",
                "Mohammad", "Ali", "Davood", "Reza", "Mohsen");


        Map<Integer, List<String>> groupedByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));
        groupedByLength.forEach((k,v)-> System.out.println(k + ": " + v));

        Map<Integer, Integer> count = groupedByLength.entrySet().stream()
                .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue().size()));
        System.out.println(count);

        Map<Integer, Long> count2 = names.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(count2 + "\n");


        Map<Integer, groupedNames> result = groupedByLength.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> new groupedNames(entry.getKey(), entry.getValue().size(), entry.getValue())
                ));
        result.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println();


        Map<Integer, Map.Entry<Integer, List<String>>> result2 = groupedByLength.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey(),
                        entry -> Map.entry(entry.getValue().size(), entry.getValue())));
        result2.forEach((k, v) -> System.out.println(
                "length " + k + ": " +
                        "number of names: " + v.getKey() + " ---- " +
                        "names: " + v.getValue()));


//                names.stream().collect(Collectors.collectingAndThen(Collectors.groupingBy(String::length),
//                new groupedNames()))
    }


}