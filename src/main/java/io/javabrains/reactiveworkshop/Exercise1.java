package io.javabrains.reactiveworkshop;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        System.out.println("Print all numbers in the intNumbersStream stream");
        StreamSources.intNumbersStream().forEach(System.out::println);

        System.out.println("Print numbers from intNumbersStream that are less than 5");
        StreamSources.intNumbersStream().filter(num -> num < 5).forEach(System.out::println);

        System.out.println("Print the second and third numbers in intNumbersStream that's greater than 5");
        StreamSources.intNumbersStream().filter(num -> num > 5).skip(1).limit(2).forEach(System.out::println);

        System.out.println("Print the first number in intNumbersStream that's greater than 5 If nothing is found, print -1: ");
        StreamSources.intNumbersStream().filter(num -> num > 5).findFirst().ifPresentOrElse(x -> System.out.println(x), () -> System.out.println(-1));
        // alt way
        int number = StreamSources.intNumbersStream().filter(num -> num > 5).findFirst().orElse(-1);
        System.out.println(number);

        System.out.println("Print first names of all users in userStream: ");
        StreamSources.userStream().map(user -> user.getFirstName()).forEach(name -> System.out.println(name));

        System.out.println("Print first names in userStream for users that have IDs from number stream");
        StreamSources.intNumbersStream().map(id -> StreamSources.userStream().filter(user -> user.getId() == id).findFirst()).forEach(System.out::println);
        // alt way
        StreamSources.intNumbersStream().flatMap(id -> StreamSources.userStream().filter(user -> user.getId() == id)).forEach(System.out::println);

    }

}
