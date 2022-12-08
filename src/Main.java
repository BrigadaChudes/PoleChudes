package src;

import java.io.FileWriter;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int checkOnThirdPlayer(int playerNum) {
        return playerNum == 3 ? 0 : playerNum;
    }

    static void displayInfo(Task task) {
        System.out.print(task.getQuestionText() + " ");

        for (int i = 0; i < task.getLengthUnknownWord(); i++) {
            System.out.print(task.getUnknownWord()[i]);
        }
    }

    static void displayBalance(Player[] players) {
        System.out.println("\nБаланс игроков: \t\t 1 игрок \t 2 игрок \t 3 игрок\n" +
                "\t\t\t\t\t\t\t" + players[0].getPoints() + " \t\t " +
                players[1].getPoints() + " \t\t\t " + players[2].getPoints());
    }

    static int determinateSectorAndNextMove(String resultOfDrum) {


        if (resultOfDrum.equals("переход хода")) {
            return 0;
        } else if (resultOfDrum.equals("+")) {
            return 1;
        }

        return 2;
    }

    public static boolean isContinue(Scanner scanner) {

        String text;
        boolean isCorrect;
        text = "";
        System.out.println("\n\n Поздравляем с победой! " +
                "\n");
        do {
            System.out.println("Вы хотите продолжить игру? Да или нет.");

            isCorrect = true;

            try {
                text = scanner.nextLine();
            } catch (Exception ex) {
                System.out.println("Ошибка: " + ex.getMessage());
                isCorrect = false;
            }

            if (isCorrect && (text.toLowerCase() != "да" || text.toLowerCase() != "нет")) {
                System.out.println("Введите: Да или Нет.");
                isCorrect = false;
            }
        }
        while (isCorrect);

        return text.toLowerCase() == "да" ? true : false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Drum drum = new Drum();
        Player[] players = new Player[3];
        int point = 0, playerNum;
        String symbol, resultOfDrum;
        Boolean isExit, willContinue;
        ;
        for (int i = 0; i < players.length; i++) {
            System.out.println("Игрок №" + (i + 1) + ".");
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

        willContinue = true;

        playerNum = 0;

        while (task.checkOnUnderlining()) {

            displayInfo(task);
            displayBalance(players);

            System.out.print("Ваш ход, " + players[playerNum].getNickname() + ": ");
            resultOfDrum = drum.spin();
            System.out.print("\t\tНА БАРАБАНЕ \t\t" + resultOfDrum + "\n");

            if (determinateSectorAndNextMove(resultOfDrum) == 0) { // Переход хода
                point = 0;
                playerNum++;
                playerNum = checkOnThirdPlayer(playerNum);
                System.out.print("Ваш ход, " + players[playerNum].getNickname() + ": ");
                resultOfDrum = drum.spin();
                System.out.print("\t\tНА БАРАБАНЕ \t\t" + resultOfDrum + "\n");
            } else if (determinateSectorAndNextMove(resultOfDrum) == 1) { // Сектор +
                point = 0;
                task.sectorPlus(scanner);
                continue;
            } else if (determinateSectorAndNextMove(resultOfDrum) == 2) { // Все ок
                point = Integer.parseInt(resultOfDrum);
            }

            System.out.print("Буква: ");
            symbol = scanner.nextLine();

            if (task.checkCharOnExist(symbol)) {
                players[playerNum].setPoints(point * task.getNumOfLetters());
            } else {
                playerNum++;
                playerNum = checkOnThirdPlayer(playerNum);
            }
        }

        displayInfo(task);

        System.out.println("Победил игрок " + players[playerNum].getNickname() + " и заработал" +
                players[playerNum].getPoints() + " баллов!");


        scanner.close();
    }
}