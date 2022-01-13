package MainApp.model;

import java.util.Objects;

public final class Character {
    private int id;
    private String nickname;
    private int level;
    private int inventoryId;
    private int userId;
    private int worldObjectId;

    public Character() {
    }

    public Character(int id, String nickname, int level, int inventoryId, int userId, int worldObjectId) {
        this.id = id;
        this.nickname = nickname;
        this.level = level;
        this.inventoryId = inventoryId;
        this.userId = userId;
        this.worldObjectId = worldObjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWorldObjectId() {
        return worldObjectId;
    }

    public void setWorldObjectId(int worldObjectId) {
        this.worldObjectId = worldObjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return id == character.id && nickname.equals(character.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname);
    }

    @Override
    public String toString() {
        return "Character{" +
                "nickname='" + nickname + '\'' +
                ", level=" + level +
                ", inventoryId=" + inventoryId +
                ", userId=" + userId +
                ", worldObjectId=" + worldObjectId +
                '}';
    }
}