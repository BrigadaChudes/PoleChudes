package src;
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

    public static int isContinue(Scanner scanner) {
        boolean isIncorrect;
        int selectedMode;

        System.out.println("\nВы хотите продолжить игру? \n1: Да;\n2: Нет.");

        selectedMode = 0;

        do {
            System.out.print("Ввод: ");
            isIncorrect = false;

            try {
                selectedMode = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println("Ошибка ввода! Было введено неккоректное значение. Повторите ввод.");
                isIncorrect = true;
            } catch (Exception exception) {
                System.out.println("Иная ошибка : " + exception.getMessage());
                isIncorrect = true;
            }

            if (!isIncorrect && selectedMode != 1 && selectedMode != 2) {
                System.out.println("Выбран неправильный режим.\n1: Да;\n2: Нет.");
                isIncorrect = true;
            }
        } while (isIncorrect);

        return selectedMode;
    }

    public static void clearPointPlayers(Player[] players){
        for (var player: players) {
            player.clearPoints();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Drum drum = new Drum();
        Player[] players = new Player[3];
        int point = 0, playerNum;
        String symbol, resultOfDrum;
        Boolean isExit, willContinue;

        for (int i = 0; i < players.length; i++) {
            System.out.println("Игрок №" + (i + 1) + ".");
            players[i] = new Player(scanner);
        }

        Task task = new Task(scanner);

        System.out.println
                ("Здравствуйте, уважаемые дамы и господа!\n" +
                        "В консоли капитал-шоу \"Поле чудес\"! \n" +
                        "И как обычно под аплодисменты зрительного зала, я приглашаю в студию тройку игроков: \n\n\t" +
                        players[0].getNickname() + ", " + players[1].getNickname() + ", " + players[2].getNickname()
                        + ".\n\nВ студию!");




        int mode = 1;

        while (mode == 1) {

            task.generateNewQuestion();
            playerNum = 0;
            clearPointPlayers(players);

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

                boolean check;

                do {
                    check = false;

                    System.out.print("Буква: ");
                    symbol = scanner.nextLine();

                    for (int i = 0; i < task.getLengthUnknownWord(); i++) {
                        if (Character.toString(task.getUnknownWord()[i]).equals(symbol)) {
                            System.out.println("Эта буква уже открыта! Повторите ввод.");
                            check = true;
                        }
                    }

                } while (check);

                if (task.checkCharOnExist(symbol)) {
                    players[playerNum].setPoints(point * task.getNumOfLetters());
                } else {
                    playerNum++;
                    playerNum = checkOnThirdPlayer(playerNum);
                }
            }

            displayInfo(task);

            System.out.println("\nПобедил игрок " + players[playerNum].getNickname() + " и заработал " +
                    players[playerNum].getPoints() + " баллов!");

            mode = isContinue(scanner);
        }
        scanner.close();
    }
}