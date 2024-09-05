package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public class CollectionsPart8 {
    /*
        Object ordering/ Comparable interface:
            - A List l may be sorted as follows:
                Collections.sort(l);
            - If the List consists of String elements, it will be sorted into alphabetical order. If it consists of
                Date elements, it will be sorted into chronological order.
            - Comparable implementations provide a natural ordering for a class, which allows objects of that class to
                be sorted automatically.
            - If you try to sort a list, the elements of which do not implement Comparable, Collections.sort(list)
                will throw a ClassCastException.
            - The following table summarizes some of the more important Java platform classes that implement Comparable.
                Class	    Natural Ordering
                ---         ---
                Integer	    Signed numerical
                Boolean	    FALSE < TRUE
                String	    Lexicographic
                Date	    Chronological

       Comparator Interface
            - Comparator interface consists of a single method.
                public interface Comparator<T> {
                    int compare(T o1, T o2);
                }
            - The compare method compares its two arguments, returning
                a negative integer - o1 < o2
                0, - o1 == o2
                a positive integer o1 > o2
     */
    class Person implements Comparable<Person> {
        String firstname;
        String lastname;

        public Person(String firstname, String lastname) {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    "} \n";
        }

        // the class's compareTo method is referred to as its natural comparison method.
        @Override
        public int compareTo(Person p) {
            if (p == null) return 1;
            return (this.firstname.compareTo(p.firstname));
        }
    }

    public void comparatorDemo() {
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 5, 2, 8, 3, 55, 3));
        System.out.println("integerList = " + integerList);
        Collections.sort(integerList);
        System.out.println("sorted integerList = " + integerList);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Ron", "Weaseley"));
        personList.add(new Person("Harry", "Potter"));
        personList.add(new Person("Hermoine", "Granger"));
        personList.add(new Person("Tom", "Riddle"));
        personList.add(new Person("Ron", "Macarthy"));
        System.out.println("personList = " + personList);

        // natural ordering
        Collections.sort(personList);
        System.out.println("sorted natural ordering personList = " + personList);

        // custom comparator
        Collections.sort(personList, (p1, p2) ->
            (p1.firstname.compareTo(p2.firstname)) == 0 ?
                    (p1.lastname.compareTo(p2.lastname)) : (p1.firstname.compareTo(p2.firstname))
        );
        System.out.println("sorted custom comparator personList = " + personList);
    }

    /*
        SortedSet & SortedMap Interface:
            - A SortedSet is a Set that maintains its elements in ascending order, sorted according to the elements'
                natural ordering or according to a Comparator provided at SortedSet
     */
}
