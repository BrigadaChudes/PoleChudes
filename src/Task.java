package src;

import java.io.*;
import java.util.Scanner;

public class Task {
    private String questionText;
    private String answer;
    private int numOfLetters;

    private char [] unknownWord = new char[100];

    private String [][] matrixOfQuestions = new String [100][2];

    public String getQuestionText() {
        return questionText;
    }

    public int getLengthUnknownWord(){
        return answer.length();
    }

    public char[] getUnknownWord(){
        return  this.unknownWord;
    }

    public char[] getAnswer() {
        return answer.toCharArray();
    }

    public boolean checkOnUnderlining(){
        boolean isCheck;
        for (var item: this.getUnknownWord()){
            if (item == '_')
                return true;
        }
        return false;
    }

    public static String getPathToFile(Scanner scanner) {
        boolean isIncorrect;
        String pathToFile;

        pathToFile = "";

        do {
            isIncorrect = false;

            if (isIncorrect) {
                System.out.println("Ошибка открытия файла! Повторите попытку.");
            }

            System.out.print("Введите путь к файлу: ");

            try {
                pathToFile = scanner.nextLine();
            } catch (Exception exception) {
                System.out.println("Ошибка : " + exception.getMessage());
                scanner.next();
                isIncorrect = true;
            }

        } while (isIncorrect);

        return pathToFile;
    }

    private void fillMatrixOfQuestions(Scanner scanner){
        String path;
        boolean isIncorrect;

        do{
            isIncorrect = false;

            path = getPathToFile(scanner);

            File file = new File(path);
            String readedLine;
            String[] arr;
            int counter = 0;

            try (var bufferedReader = new BufferedReader(new FileReader(file))) {
                while ((readedLine = bufferedReader.readLine()) != null && counter <= 100) {
                    arr = readedLine.split(" <> ");

                    matrixOfQuestions[counter][0] = arr[0];
                    matrixOfQuestions[counter][1] = arr[1];

                    counter++;
                }
            } catch (FileNotFoundException e) {
                isIncorrect = true;
                System.out.println("Файл не был найден.");
            } catch (IOException e) {
                System.out.println("Ошибка: " + e.getMessage());
                isIncorrect = true;
            }
        } while (isIncorrect);
    }

    public void sectorPlus(Scanner scanner) {
        boolean isIncorrect;
        int orderOfLetter = 0;

        do {
            isIncorrect = false;

            System.out.print("Введите букву, которую нужно открыть: ");

            try {
                orderOfLetter = Integer.parseInt(scanner.nextLine());
            } catch (Exception exception) {
                System.out.println("Было введено неправильное значение. Повторите ввод.");
                isIncorrect = true;
            }

            if (!isIncorrect && (this.answer.length() < orderOfLetter || orderOfLetter <= 0)) {
                System.out.println("Порядок буквы больше длины загадонного слова.");
                isIncorrect = true;
            }

            if (!isIncorrect && this.unknownWord[orderOfLetter] != '_') {
                System.out.println("Была выбрана уже открытая буква!");
                isIncorrect = true;
            }

        } while (isIncorrect);

        this.unknownWord[orderOfLetter - 1] = this.getAnswer()[orderOfLetter - 1];
    }

    public static int rnd(int max)
    {
        return (int) (Math.random() * ++max);
    }

    public void generateNewQuestion(){
        int link = rnd(2);
        this.questionText = matrixOfQuestions[link][0];
        this.answer = matrixOfQuestions[link][1];
        for(int i = 0; i < answer.length(); i++)
            this.unknownWord[i] = '_';
    }

    public int getNumOfLetters(){
        return this.numOfLetters;
    }

    public boolean checkCharOnExist(String symbol){
        char[] arrCompare = this.getAnswer();
        boolean isExist = false;
        this.numOfLetters = 0;

        for(int i = 0; i < arrCompare.length; i++) {
            if(Character.toString(arrCompare[i]).toLowerCase().equals(symbol.toLowerCase())){
                unknownWord[i] = symbol.toCharArray()[0];
                isExist = true;
                numOfLetters++;
            }
        }
        return isExist;
    }

    public Task(Scanner scanner)
    {
        fillMatrixOfQuestions(scanner);
    }
}
