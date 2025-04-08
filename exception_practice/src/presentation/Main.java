package presentation;

import util.Validator;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Boolean.parseBoolean("fsd");

        Validator.validateInt("nhap so nguyen",sc);
        Validator.validateFloat("nhap float",sc);
        Validator.validateDouble("nhap double",sc);
        Validator.validateBoolean("nhap boolean",sc);
        Validator.validateString("nhap string",sc,3,10);
    }
}
