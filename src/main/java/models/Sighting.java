package models;

import org.sql2o.*;
import java.util.List;

public class Sighting implements DatabaseManagement{
    private String location;
    private String rangerName;
    private int id;

    public Sighting(String location, String rangerName) {
        this.location = location;
        this.rangerName = rangerName;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return location.equals(sighting.location) &&
                rangerName.equals(sighting.rangerName);
    }

    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (location, rangerName) VALUES (:location, :rangerName)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("location", this.location)
                    .addParameter("rangerName", this.rangerName)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }

    public List<Animal> getAnimals() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where sightingId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Animal.class);
        }
    }
}
