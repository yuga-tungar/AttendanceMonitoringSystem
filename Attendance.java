package Final;

import java.util.ArrayList;
import java.util.Scanner;

public class Attendance {
    String[][] data = new String[5][4];
    ArrayList<String>[][] p_or_a = new ArrayList[5][4];

    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        int n;
        Attendance at = new Attendance();
        Input in = new Input();
        do {
            System.out.println("\nEnter: \n1-Display time table.\n2-Mark attendance\n3-Access weekly attendance record of a student for a subject\n4-Send email to Defaulters & Display defaulter's list\n5-Display list of absent students.\n6-Display list of present students.\n7-Exit");
            n = sc1.nextInt();
            switch (n) {
                case 1:
                    at.print();
                    System.out.println();
                    break;
                case 2:
                    in.rec(at.data, at.p_or_a);
                    System.out.println();
                    break;
                case 3:
                    in.searchByRollNumber(at.data, at.p_or_a);
                    System.out.println();
                    break;
                case 4:
                    in.defaulter(at.data, at.p_or_a);
                    System.out.println();
                    break;
                case 5:
                    in.absent(at.data, at.p_or_a);
                    break;
                case 6:
                    in.pres(at.data, at.p_or_a);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        } while (n != 7);
    }

    void print() {
        data[0][0] = "Monday";
        data[1][0] = "Tuesday";
        data[2][0] = "Wednesday";
        data[3][0] = "Thursday";
        data[4][0] = "Friday";
        data[0][1] = "MVC";
        data[0][2] = "OOPJ";
        data[0][3] = "SE";
        data[1][1] = "SE";
        data[1][2] = "PHY";
        data[1][3] = "EG";
        data[2][1] = "EG";
        data[2][2] = "SE";
        data[2][3] = "OOPJ";
        data[3][1] = "OOPJ";
        data[3][2] = "PHY";
        data[3][3] = "MVC";
        data[4][1] = "EG";
        data[4][2] = "MVC";
        data[4][3] = "PHY";

        int maxLen = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j].length() > maxLen) {
                    maxLen = data[i][j].length();
                }
            }
        }

        System.out.println("***WEEKLY TIMETABLE***\n");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%-" + (maxLen + 3) + "s", data[i][j]);
            }
            System.out.println();
        }
    }
}