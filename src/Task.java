package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Task {
    private String questionText;
    private String answer;
    private String [][] matrixOfQuestions = new String [100][2];

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswer() {
        return answer;
    }

    private void fillMatrixOfQuestions(){
        File file = new File("C:\\Users\\fedor\\Desktop\\БГУИР\\ОАиП\\Лабораторная №2\\2.2\\f.txt");
        String readedLine;
        String [] arr;
        int counter = 0;

        try(var bufferedReader = new BufferedReader(new FileReader(file))){
           while((readedLine = bufferedReader.readLine()) != null && counter <= 100){
               arr = readedLine.split(" <> ");

               matrixOfQuestions[counter][0] = arr[0];
               matrixOfQuestions[counter][1] = arr[1];

               counter++;
           }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не был найден. Обратитесь к сис. админу.");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    public static int rnd(int max)
    {
        return (int) (Math.random() * ++max);
    }

    public void generateNewQuestion(){
        int link = rnd(100);
        this.questionText = matrixOfQuestions[link][0];
        this.answer = matrixOfQuestions[link][1];
    }

    public Task()
    {
        // Таск
        fillMatrixOfQuestions();
    }
}
