package org.sample.reflection_api;

import java.io.Serializable;

public class User extends Person implements Serializable,Comparable<User> {

    private String name;

    String lastName;

    public User(Long id, String name,String lastName) {
        super(id);
        this.name = name;
        this.lastName = lastName;
    }

    private static class TestInner {

    }

    private enum TestEnumInner {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int compareTo(User o) {
        return 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
