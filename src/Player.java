package src;

import java.io.IOException;
import java.util.Scanner;

public class Player {

    private String nickname;
    private int points;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Player(Scanner scanner) {
        boolean isCorrect;

        do{
            isCorrect = true;
            System.out.print("Введите ваше имя: ");

            try {
                this.nickname = scanner.nextLine();
            }catch (Exception ex){
                System.out.println("Ошибка ввода. Повторите попытку.");
            }

            if(isCorrect && this.nickname.length() > 20)
            {
                System.out.println("Максимальная длина - 20 символов. Пожалуйста, повторите попытку.");
                isCorrect = false;
            }

        }while (!isCorrect);
    }
}
