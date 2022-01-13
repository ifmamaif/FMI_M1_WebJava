package MainApp.model;

import java.util.Objects;

public final class WorldObject {
    private int id;
    private String name;
    private String position;
    private String description;
    private String whatDo;

    public WorldObject() {
    }

    public WorldObject(int id, String name, String position, String description, String whatDo) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.description = description;
        this.whatDo = whatDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWhatDo() {
        return whatDo;
    }

    public void setWhatDo(String whatDo) {
        this.whatDo = whatDo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorldObject that = (WorldObject) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "WorldObject{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                ", whatDo='" + whatDo + '\'' +
                '}';
    }
}