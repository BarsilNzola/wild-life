package models;

public class Ranger {
    private String name;
    private String number;
    private int badgeNumber;

    public Ranger (String name, String number, int badgeNumber){
        this.name = name;
        this.number = number;
        this.badgeNumber = badgeNumber;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }
}
