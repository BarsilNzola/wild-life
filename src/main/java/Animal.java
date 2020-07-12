import org.sql2o.*;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class Animal {
    private String name;
    private String nickname;
    private String species;
    private int id;

    public Animal (String name, String nickname, String species) {
        this.name = name;
        this.nickname = nickname;
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getNickname() {
        return nickname;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object otherAnimal) {
        if (!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.getName().equals(newAnimal.getName()) &&
                    this.getNickname().equals(newAnimal.getNickname()) &&
                    this.getSpecies().equals(newAnimal.getSpecies());
        }
    }


    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, nickname, species) VALUES (:name, :nickname, :species)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("nickname", this.nickname)
                    .addParameter("species", this.species)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

    public static Animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
           String sql = "SELECT * FROM animals where id=:id";
           Animal animal = con.createQuery(sql)
                   .addParameter("id", id)
                   .executeAndFetchFirst(Animal.class);
           return animal;
        }
    }
}
