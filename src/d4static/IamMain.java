package d4static;

import java.util.Scanner;

public class IamMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Util util = new Util();
        System.out.println("Running Class "+IamMain.class.getName());
        System.out.println("Enter '1' for Project properties '2' for Jar Properties");
        int choice = scanner.nextInt();
        System.out.println("Writing in properties file");
        System.out.println("Enter key");
        String key = scanner.next();
        System.out.println("Enter Value ");
        String value = scanner.next();
        util.write(choice,key,value);
        System.out.println("Reading properties file!");
        util.read(choice);

    }




}
