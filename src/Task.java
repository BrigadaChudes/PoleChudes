package src;

import java.io.*;

public class Task {
    private String questionText;
    private String answer;

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
        int link = rnd(10);
        this.questionText = matrixOfQuestions[link][0];
        this.answer = matrixOfQuestions[link][1];
        for(int i = 0; i < answer.length(); i++)
            this.unknownWord[i] = '_';
    }

    public boolean checkCharOnExist(String symbol){
        char[] arrCompare = this.getAnswer();
        boolean isExist = false;

        for(int i = 0; i < arrCompare.length; i++) {
            if(Character.toString(arrCompare[i]).equals(symbol)){
                unknownWord[i] = symbol.toCharArray()[0];
                isExist = true;
            }
        }
        return isExist;
    }

    public Task()
    {
        fillMatrixOfQuestions();
    }
}
