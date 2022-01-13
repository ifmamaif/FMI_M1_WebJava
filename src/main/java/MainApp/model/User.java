package MainApp.model;

import java.util.Objects;

public final class User {
    private int id = 0;
    private String username;
    private String pass;

    public User() {
    }

    public User(int id, String username, String pass) {
        this.id = id;
        this.username = username;
        this.pass = pass;
    }

    public User(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return this.username.equals(user.username) && pass.equals(user.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, pass);
    }

    @Override
    public String toString() {
        return "Username{" +
                "user='" + username + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}