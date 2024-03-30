package io.javabrains.reactiveworkshop;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        // TODO: Write code here

//        StreamSources.intNumbersStream().forEach(value -> System.out.println(value));

        // Print numbers from intNumbersStream that are less than 5
        // TODO: Write code here

//        StreamSources.intNumbersStream().filter(value -> value < 5).forEach(value -> System.out.println(value));

        // Print the second and third numbers in intNumbersStream that's greater than 5
        // TODO: Write code here

        StreamSources.intNumbersStream().filter(value -> value > 5).skip(1).limit(2).forEach(value -> System.out.println(value));


        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        // TODO: Write code here

        StreamSources.intNumbersStream().filter(value -> value > 5)
                .findFirst()
                .ifPresentOrElse(
                        value -> System.out.println(value),
                        () -> System.out.println(-1));

        // Print first names of all users in userStream
        // TODO: Write code here

        StreamSources.userStream().forEach((user -> {
            System.out.println(user.getFirstName());
        }));

        // Print first names in userStream for users that have IDs from number stream and unique ids
        // TODO: Write code here


        StreamSources.intNumbersStream()
                .flatMap(id -> StreamSources.userStream().filter(user -> id==user.getId()))
                .filter(distinctById(User::getId))
                .forEach(user -> System.out.println(user));

    }

    public static <T> Predicate<T> distinctById(Function<T, Integer> keyExtractor) {
        Set<Integer> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

}
