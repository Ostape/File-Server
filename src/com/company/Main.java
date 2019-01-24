package com.company;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> files = new ArrayList<>();
        String choose = "";
        String nameFile;

        while (!choose.equals("exit")){

            choose = scan.next();
            if (!choose.equals("exit")) {
                nameFile = scan.skip(" ").nextLine();
                switch (choose) {
                    case "add":
                        addFile(nameFile, files);
                        break;

                    case "delete":
                        deleteFile(nameFile, files);
                        break;

                    case "get":
                        getFile(nameFile, files);
                        break;
                }
            }
        }
    }

    private static void addFile(String name, List<String> nameFile){
        if (nameFile.contains(name) || !checkNameFile(name)){
            System.out.println("Cannot add the file");
        }
        else {
            nameFile.add(name);
            System.out.println("The file " + name + " added successfully");
        }
    }

    private static void deleteFile(String name, List<String> nameFile){
        if (!nameFile.contains(name)){
            System.out.println("File " + name + " not found");
        }
        else {
            nameFile.remove(name);
            System.out.println("The file was deleted");
        }
    }

    private static void getFile(String name, List <String> nameFile){
        if (nameFile.contains(name)){
            System.out.println("The file was sent");
        }
        else {
            System.out.println("File " + name + " not found");
        }
    }

    private static boolean checkNameFile(String name){
        switch (name){
            case "file1":case "file2":case "file3":case "file4":case "file5":
                case "file6":case "file7":case "file8":case "file9":case "file10":
                    return true;
                    default:return false;
        }
    }
}