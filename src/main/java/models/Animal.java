package models;

import org.sql2o.*;

import java.util.List;

public class Animal {
    public String name;
    public String nickname;
    public String species;
    public String type;
    public int id;

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
            return  this.getName().equals(newAnimal.getName()) &&
                    this.getNickname().equals(newAnimal.getNickname()) &&
                    this.getSpecies().equals(newAnimal.getSpecies());
        }
    }


    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, nickname, species, type) VALUES (:name, :nickname, :species, :type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("nickname", this.nickname)
                    .addParameter("species", this.species)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<EndangeredAnimal> all() {
        String sql = "SELECT * FROM animals";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimal.class);
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

    public List<Sighting> getSightings() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where animalId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Sighting.class);
        }
    }
}
