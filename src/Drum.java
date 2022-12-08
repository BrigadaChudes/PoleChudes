package src;
public class Drum {
    final String [] points =
            {"100", "110", "120", "130", "переход хода",  "140", "150", "160", "170", "180", "190", "200",
                    "210", "220", "+", "230", "240", "250", "260", "270", "280", "290", "300"};
    public static int rnd(int max)
    {
        return (int) (Math.random() * ++max);
    }

    public String spin(){
        return points[rnd(22)];
    }
}
