package com.interviews;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        String[][] studentCoursePairs1 = {
                {"58", "Linear Algebra"},
                {"94", "Art History"},
                {"94", "Operating Systems"},
                {"17", "Software Design"},
                {"58", "Mechanics"},
                {"58", "Economics"},
                {"17", "Linear Algebra"},
                {"17", "Political Science"},
                {"94", "Economics"},
                {"25", "Economics"},
                {"58", "Software Design"}
        };

        findPairs(studentCoursePairs1);

    }

    private static void findPairs(String[][] pairs) {
        Map<String, Set<String>> studentsByCourse = new HashMap<>();
        Map<String, Set<String>> allPairs = new HashMap<>();
        Set<String> students = new HashSet<>();

        for (String[] enrollment: pairs) {
            String student = enrollment[0];
            String course = enrollment[1];

            studentsByCourse.computeIfAbsent(course, s -> new HashSet<String>());
            studentsByCourse.get(course).add(student);

            students.add(student);
        }

        String[] studentsArray = students.toArray(new String[0]);

        for (int i = 0; i < studentsArray.length; i++) {
            for (int j = i+1; j < studentsArray.length; j++) {
                String student1 = studentsArray[i];
                String student2 = studentsArray[j];
                String pair = String.join(",", student1, student2);

                Set<String> courses = new HashSet<>();

                studentsByCourse.forEach((course, studentsEnrolled) -> {
                    if (studentsEnrolled.containsAll(Arrays.asList(student1, student2))) {
                        courses.add(course);
                    }
                });

                allPairs.put(pair, courses);
            }
        }

        for (Map.Entry<String, Set<String>> entry: allPairs.entrySet()) {
            System.out.println(entry);
        }
    }
}
