package src;

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

    public Player(String nickname) {
        this.nickname = nickname;
    }
}
