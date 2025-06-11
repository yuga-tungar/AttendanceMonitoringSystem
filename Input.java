package Final;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Input {
    Scanner s = new Scanner(System.in);

    void rec(String[][] data, ArrayList<String>[][] p_or_a) {
        System.out.println("\nWhich day of the week?\nEnter\n1 for Monday\n2 for Tuesday\n3 for Wednesday\n4 for Thursday\n5 for Friday");
        int n1 = s.nextInt();
        System.out.println("Lecture number?");
        int n2 = s.nextInt();
        while (n2 > 3) {
            System.out.println("Lecture does not exist!");
            System.out.println("Enter Again:");
            n2 = s.nextInt();
        }

        System.out.println("D division students:");
        if (p_or_a[n1 - 1][n2] == null) {
            p_or_a[n1 - 1][n2] = new ArrayList<>();
        }

        for (int i = 0; i < 5; i++) {
            if (i == n1 - 1) {
                System.out.println("\nToday is " + data[i][0]);
                System.out.println("It's " + data[i][n2] + " lecture");
            }
        }

        System.out.println("Enter attendance for " + data[n1 - 1][0] + " " + data[n1 - 1][n2] + ":");
        for (int k = 0; k < 5; k++) {
            System.out.print("RNO " + (k + 1) + " (p/a): ");
            String input = s.next();
            p_or_a[n1 - 1][n2].add(input);
        }
    }

    void searchByRollNumber(String[][] data, ArrayList<String>[][] p_or_a) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the course:");
        String subject = sc.nextLine();
        System.out.println("Enter the roll number of the student:");
        int roll = sc.nextInt();
        int found = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j].trim().equalsIgnoreCase(subject.trim())) {
                    if (p_or_a[i][j] != null && p_or_a[i][j].size() >= roll) {
                        System.out.print(data[i][0] + ": ");
                        if (p_or_a[i][j].get(roll - 1).equalsIgnoreCase("p")) {
                            System.out.println("Present");
                            found++;
                        } else if (p_or_a[i][j].get(roll - 1).equalsIgnoreCase("a")) {
                            System.out.println("Absent");
                            found++;
                        }
                        break;
                    }
                }
            }
        }

        if (found == 0) {
            System.out.println("No lecture found for the given subject.");
        }
    }

    void defaulter(String[][] data, ArrayList<String>[][] p_or_a) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the course:");
        String subject = sc.nextLine();
        ArrayList<Integer> defaulters = new ArrayList<>();

        for (int k = 0; k < 5; k++) {
            int attendedLectures = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 1; j < 4; j++) {
                    if (data[i][j].trim().equalsIgnoreCase(subject.trim())) {
                        if (p_or_a[i][j] != null && p_or_a[i][j].size() >= k + 1) {
                            String attendance = p_or_a[i][j].get(k);
                            if (attendance.equalsIgnoreCase("p")) {
                                attendedLectures++;
                            }
                        }
                    }
                }
            }
            if (attendedLectures < 2) {
                defaulters.add(k + 1);
            }
        }

        if (defaulters.isEmpty()) {
            System.out.println("No defaulters found for the given subject.");
        } else {
            Set<Integer> uniqueElements = new HashSet<>(defaulters);
            System.out.println("Defaulter List for " + subject + ":");
            for (Integer element : uniqueElements) {
                System.out.println("Roll No: " + element);
            }
        }

        String[] emails = {
            "vishnulp2027@gmail.com",
            "shruti.shinde@cumminscollege.in",
            "yuga.tungar@cumminscollege.in",
            "vishnupriya.lappasi@cumminscollege.in",
            "tanishka.patil@cumminscollege.in"
        };
        String subject_of_email = "Notice for Defaulters";
        String body = "Dear Student,\nYou have been listed in the Defaulter list for this week. Kindly contact your mentor for the same and do the needful.";

        for (int i = 0; i < defaulters.size(); i++) {
            if (defaulters.contains(i + 1)) {
                SendEmailTo send_email = new SendEmailTo(emails[i], subject_of_email, body);
            }
        }
    }

    void absent(String[][] data, ArrayList<String>[][] p_or_a) {
        System.out.println("List of absent students:");
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 1; j < 4; j++) {
                    if (p_or_a[i][j] == null) {
                        continue;
                    }
                    if (p_or_a[i][j].get(k).equalsIgnoreCase("a")) {
                        System.out.println("Day: " + data[i][0] + " lecture: " + data[i][j] + " Roll no: " + (k + 1));
                    }
                }
            }
        }
    }

    void pres(String[][] data, ArrayList<String>[][] p_or_a) {
        System.out.println("List of present students:");
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 1; j < 4; j++) {
                    if (p_or_a[i][j] == null) {
                        continue;
                    }
                    if (p_or_a[i][j].get(k).equalsIgnoreCase("p")) {
                        System.out.println("Day: " + data[i][0] + " lecture: " + data[i][j] + " Roll no: " + (k + 1));
                    }
                }
            }
        }
    }
}