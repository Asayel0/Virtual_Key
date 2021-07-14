package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    static String pathName ="src/folders/";
    public static void main(String[] args) {
        String welcomeText = "Welcome to VirtualKey!";
        String developerText = "Developer: Asayel Falatah ";
        System.out.println(welcomeText);
        System.out.println(developerText);
        System.out.println("\n");
        File theDir = new File(pathName);
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        welcomeScreen();

    }

    static ArrayList<String> options = new ArrayList<String>();

    static void welcomeScreen() {
        options.clear();
        options.add("1. Show Files");
        options.add("2. Show File Options Menu");
        options.add("3. Quit");
        System.out.println("Main Menu");
        for (String s : options) {
            System.out.println(s);
        }

        Scanner in = new Scanner(System.in);

        int returnOption = 0;
        try {
            returnOption = in.nextInt();
        } catch (InputMismatchException ex) {

        }
        switch (returnOption) {

            case 1: // Show Files in Directory
                Path path = FileSystems.getDefault().getPath(pathName).toAbsolutePath();
                File file = path.toFile();
                File[] files = file.listFiles();

                if (files != null) {
                    Arrays.sort(files);
                    for (int i = 0; i < files.length; i++) {
                        System.out.println(i + 1 + ":" + files[i].getName());
                    }
                }
                System.out.println("\n");
                welcomeScreen();
                break;

            case 2: // Show File Options menu
                fileOptionsScreen();

                break;
            case 3: //close app

                break;
            default:
                System.out.println("Invalid Option");
                welcomeScreen();
                break;
        }

    }
    private static ArrayList<String> options2 = new ArrayList<String>();

    static void fileOptionsScreen() {
        options2.clear();

        options2.add("1. Add a File");
        options2.add("2. Delete A File");
        options2.add("3. Search A File");
        options2.add("4. Return to Menu");

        System.out.println("File Options Menu");
        for (String s : options2) {
            System.out.println(s);
        }
        Scanner in = new Scanner(System.in);

        int returnOption = 0;
        try {
            returnOption = in.nextInt();
        }
        catch (InputMismatchException ex) {

        }
        switch(returnOption) {

            case 1: // Add File
                AddFile();

                fileOptionsScreen();
                break;
            case 2: // Delete File
                DeleteFile();
                fileOptionsScreen();
                break;
            case 3: // Search File
                SearchFile();
                fileOptionsScreen();
                break;

            case 4: // Return to Menu
                welcomeScreen();
                break;

            default:
                System.out.println("Invalid Option");
                fileOptionsScreen();
                break;

        }
    }
    public static void AddFile() {

        File theDir = new File(pathName);
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        System.out.println("Please Enter the Filename:");

        String fileName = getInputString();

        System.out.println("You are adding a file named: " + fileName);

        try {
            File file = new File(pathName+ fileName);

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());

            } else {
                System.out.println("This File Already Exits");
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    static void DeleteFile() {

        System.out.println("Please Enter the Filename:");

        String fileName = getInputString();

        System.out.println("You are deleting a file named: " + fileName);


        Path path = FileSystems.getDefault().getPath(pathName + fileName).toAbsolutePath();
        File file = path.toFile();
        if (file.getName().equals(fileName)) {
            if (file.delete()) {
                System.out.println("Deleted File: " + file.getName());
            } else {
                System.out.println("Failed to delete file:" + fileName + ", file was not found.");
            }
        }else {
            System.out.println("File not found");
        }
    }

    static void SearchFile() {

        Boolean found = false;

        System.out.println("Please Enter the Filename:");

        String fileName = getInputString();

        System.out.println("You are searching for a file named: " + fileName);

        Path path = FileSystems.getDefault().getPath(pathName).toAbsolutePath();
        File file = path.toFile();
        File[] files = file.listFiles();

        if (files != null) {
            for(int i = 0; i < files.length; i++) {
                if(files[i].getName().equals(fileName)) {
                    System.out.println("Found " + fileName);
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("File not found");
        }
    }

    private static String getInputString() {

        Scanner in = new Scanner(System.in);
        return(in.nextLine());

    }
}
