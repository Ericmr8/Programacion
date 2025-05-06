package view.console;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import model.Funciones;
import java.util.Scanner;

public class main {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String option = "";
        do {
            System.out.println("");
            System.out.println("");

            System.out.println("1.-Create Folder");
            System.out.println("2.-Create File");
            System.out.println("3.-Show List of Files");
            System.out.println("4.-Show File Content");
            System.out.println("5.-Overwrite File");
            System.out.println("6.-Delete File");
            System.out.println("7.-Count Chars");
            System.out.println("8.-Count Words");
            System.out.println("9.-Replace Words");
            System.out.println("10.-Print PDF");
            System.out.println("z.-Exit Program");

            System.out.println("Option");
            option = scan.next();

            System.out.println("");
            System.out.println("");

            switch (option) {
                case "1":
                    createFolder();
                    break;
                case "2":
                    createFile();
                    break;
                case "3":
                    showListFiles();
                    break;
                case "4":
                    showFile();
                    break;
                case "5":
                    overWriteFile();
                    break;
                case "6":
                    deleteFile();
                    break;
                case "7":
                    countChars();
                    break;
                case "8":
                    countWords();
                    break;
                case "9":
                    swapWords();
                    break;
                case "10":
                    printPDF();
                    break;
                case "z":
                    System.out.println("Exit successfull");
                    break;

                default:
                    System.out.println("Incorrect Option");
            }
        } while (!option.equals("z"));
    }

    private static void createFolder() {
        String folderPath = "";

        System.out.print("Where do you want to create your new folder?: ");
        folderPath = scan.next();

        System.out.print("Name the new folder: ");
        String folderName = scan.next();

        File folder = new File(folderPath, folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private static void createFile() {

        String filePath = "";

        System.out.print("Where do you want to create your new file?: ");
        filePath = scan.next();

        System.out.print("Name the new file: ");
        String fileName = scan.next();

        fileName = fileName + ".txt";

        File file = new File(filePath, fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

        } catch (IOException e) {
            System.out.println("Unable to make your file " + file.toString());
        }
    }

    private static void showListFiles() {

        System.out.print("Write the path from where you want to see the files: ");
        String folderPath = scan.next();

        File folder = new File(folderPath);
        String[] list = folder.list();
        if (list == null || list.length == 0) {
            System.out.println("There are no files inside this folder.");
            return;
        } else {
            for (int i = 0; i < list.length; i++) {
                System.out.println(list[i]);
            }
        }
    }

    private static void showFile() {
        System.out.print("Write the file path: ");
        String filePath = scan.next();

        System.out.print("Write the name of the file: ");
        String fileName = scan.next();

        File file = new File(filePath, fileName);

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("We can't find your file: " + file.toString());
        } catch (IOException ex) {
            System.out.println("We can't read the file: " + file.toString());
        }
    }

    private static void overWriteFile() {
        System.out.print("Write the path of the file: ");
        String filePath = scan.next();

        System.out.print("Write the name of the file: ");
        String fileName = scan.next();

        System.out.print("How many lines do you want to write? ");
        int lines = scan.nextInt();

        File file = new File(filePath, fileName);
        scan.useDelimiter("\n");
        try (BufferedWriter write = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < lines; i++) {
                String text = scan.next();
                write.write(text + "\n");
            }
        } catch (IOException ex) {
            System.out.println("Unable to overwrite your file: " + file.toString());
        }

    }

    private static void deleteFile() {
        System.out.print("Write the path of the file that your attempting to delete: ");
        String filePath = scan.next();

        System.out.print("Write the name of the file: ");
        String fileName = scan.next();

        File file = new File(filePath, fileName);

        if (file.exists()) {
            file.delete();
        } else {
            System.out.println("There are no files with that name >:c");
        }
    }

    private static void countChars() {
        System.out.print("Write the file path: ");
        String filePath = scan.next();

        System.out.print("Write the name of the file: ");
        String fileName = scan.next();

        File file = new File(filePath, fileName);

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;

            int chars = 0;
            while ((line = br.readLine()) != null) {
                chars = line.length() + chars;

            }
            System.out.println("Your file has " + chars + " characters.");

        } catch (FileNotFoundException ex) {
            System.out.println("We can't find your file: " + file.toString());
        } catch (IOException ex) {
            System.out.println("We can't read the file: " + file.toString());
        }

    }

    private static void countWords() {
        System.out.print("Write the file path: ");
        String filePath = scan.next();

        System.out.print("Write the name of the file: ");
        String fileName = scan.next();

        File file = new File(filePath, fileName);
        int count = 0;

        try (Scanner FileScanner = new Scanner(file)) {
            while (FileScanner.hasNext()) {
                FileScanner.next();
                count++;
            }
            System.out.println("This file contains " + count + " words.");
        } catch (IOException ex) {
            System.out.println("I can't read the words.");
        }
    }

    private static void swapWords() {
        System.out.print("Write the file path: ");
        String filePath = scan.next();

        System.out.print("Write the name of the file: ");
        String fileName = scan.next();

        File file = new File(filePath, fileName);

        System.out.println("Write the word what are you trying to replace");
        String oldWord = scan.next();

        System.out.println("Now write the new word");
        String newWord = scan.next();

        try {
            String content = "";
            content = new String(Files.readAllBytes(Paths.get(filePath, fileName)));

            String replace = content.replace(oldWord, newWord);

            System.out.println("You replaced " + oldWord + " by " + newWord);
            Files.write(Paths.get(filePath, fileName), replace.getBytes());

        } catch (IOException ex) {
            System.out.println("Error reading or writing the file: " + ex.getMessage());
        }
    }

    private static void printPDF() {

        String filePath = "";

        System.out.print("Where do you want to create your new pdf?: ");
        filePath = scan.next();

        System.out.print("Name the new pdf: ");
        String fileName = scan.next();

        fileName = fileName + ".pdf";

        File file = new File(filePath, fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

        } catch (IOException e) {
            System.out.println("Unable to make your pdf " + file.toString());
        }
    }
}
