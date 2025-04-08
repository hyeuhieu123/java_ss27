import presentation.StudentUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("---------------------MENU------------------------");
            System.out.println("1. quan ly student");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    StudentUI.displayMenu(sc);
                    break;
                default:
                    System.out.println("chon lai");
            }
        }while(true);
    }
}
