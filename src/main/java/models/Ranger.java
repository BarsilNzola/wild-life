package models;

import org.sql2o.Connection;

import java.util.List;

public class Ranger {
    private String name;
    private String number;
    private int badgeNumber;
    private int id;

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

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object otherRanger) {
        if(!(otherRanger instanceof Ranger)) {
            return false;
        } else {
            Ranger newRanger = (Ranger) otherRanger;
            return this.getName().equals(newRanger.getName()) &&
                    this.getNumber().equals(newRanger.getNumber()) &&
                    this.getBadgeNumber() == (newRanger.getBadgeNumber());
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO rangers (name, number, badgeNumber) VALUES (:name, :number, :badgeNumber)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("number", this.number)
                    .addParameter("badgeNumber", this.badgeNumber)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Ranger> all() {
        String sql = "SELECT * FROM rangers";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Ranger.class);
        }
    }

    public static Ranger find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM rangers WHERE id=:id;";
            Ranger ranger = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
            return ranger;
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }
}
