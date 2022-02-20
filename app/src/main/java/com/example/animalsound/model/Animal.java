package com.example.animalsound.model;

import java.util.Objects;

public class Animal {
    private int idPhoto;
    private String name;
    private int idSound;

    public Animal(int idPhoto, String name, int idSound) {
        this.idPhoto = idPhoto;
        this.name = name;
        this.idSound = idSound;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdSound() {
        return idSound;
    }

    public void setIdSound(int idSound) {
        this.idSound = idSound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return idPhoto == animal.idPhoto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPhoto);
    }
}
