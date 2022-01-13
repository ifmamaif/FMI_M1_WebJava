package MainApp.model;

import java.util.Objects;

public final class Inventory {
    private int id;
    private String items;

    public Inventory() {
    }

    public Inventory(int id, String items) {
        this.id = id;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return id == inventory.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", items='" + items + '\'' +
                '}';
    }
}