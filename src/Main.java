package src;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player[] players = new Player[3];
        for (int i = 0; i < players.length; i++){
            System.out.println("Игрок №" + (i+1) + ".");
            players[i] = new Player(scanner);
        }

        boolean checkOnExit;

        Task task = new Task();

        System.out.println
                ("Здравствуйте, уважаемые дамы и господа!\n" +
                "В консоли капитал-шоу \"Поле чудес\"! \n" +
                "И как обычно под аплодисменты зрительного зала, я приглашаю в студию тройку игроков: \n\n\t" +
                players[0].getNickname() + ", " + players[1].getNickname() + ", " + players[2].getNickname()
                + ".\n\nВ студию!");

        task.generateNewQuestion();

        checkOnExit = true;

        do{

            System.out.print(task.getQuestionText());
            for(int i = 0; i < task.getLengthUnknownWord(); i++){
                System.out.print(task.getUnknownWord()[i]);
            }

        }while (!checkOnExit);
    }
}
