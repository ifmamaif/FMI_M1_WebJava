package MainApp.model;

import java.util.Objects;

public final class Spell {
    private int id;
    private String name;
    private String description;
    private String whatDo;
    private String sourceId;
    private String targetId;

    public Spell() {
    }

    public Spell(int id, String name, String description, String whatDo, String sourceId, String targetId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.whatDo = whatDo;
        this.sourceId = sourceId;
        this.targetId = targetId;
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

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spell spell = (Spell) o;
        return id == spell.id && name.equals(spell.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", whatDo='" + whatDo + '\'' +
                '}';
    }
}