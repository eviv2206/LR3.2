package com.company;

import java.io.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Эта программа выводит множество состоящее из арифметических знаков и цифр");
        System.out.println("Входной файл");
        String filepath = findFilePath(input);
        char[] array = readInputData(filepath, input);
        HashSet<Character> hash = createHashSet(array);
        System.out.println("Выходной файл");
        filepath = findFilePath(input);
        input.close();
        outputData(hash, filepath);
    }

    public static String findFilePath(Scanner input) {
        String filePath;
        FileReader reader;
        boolean isInCorrect;
        do {
            System.out.print("Введите путь к файлу: ");
            filePath = input.nextLine();
            isInCorrect = false;
            try {
                reader = new FileReader(filePath);
            } catch (FileNotFoundException e) {
                System.out.println("Ошибка! Файл не найден");
                isInCorrect = true;
            }
        } while (isInCorrect);
        return filePath;
    }

    public static char[] readInputData(String filePath, Scanner input) {
        char[] array = null;
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            line = reader.readLine();
            reader.close();
            if (Objects.isNull(line)) {
                System.out.println("В файле содержится пустое множество");
                System.out.println("Введите непустое множество");
                array = input.nextLine().toCharArray();
            } else {
                array = line.toCharArray();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return array;
    }

    public static HashSet<Character> createHashSet(char[] array) {
        HashSet<Character> hash = new HashSet<>();
        final char[] targetValues = {'+', '-', '/', '*', '=', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 15; j++) {
                if (array[i] == targetValues[j]) {
                    if (List.of(hash).contains(array[i]) == false) {
                        hash.add((array[i]));
                    }
                }
            }
        }
        return hash;
    }

    public static void outputData(HashSet<Character> hash, String filepath) {
        BufferedWriter writer;
        if (hash.isEmpty()){
            System.out.println("Нет удовлетворяющих элементов");
            try {
                writer = new BufferedWriter(new FileWriter(filepath));
                writer.write("Нет удовлетворяющих элементов");
            } catch (IOException e){
                System.err.println(e.getMessage());
            }
        } else {
            try {
                writer = new BufferedWriter(new FileWriter(filepath));
                writer.write(String.valueOf(hash));
                writer.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            System.out.println("Полученное множество");
            System.out.println(hash);
        }
    }
}
