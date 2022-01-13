package MainApp.dto.Character;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public record UpdateCharacterRequest(@NotNull @NotEmpty @Pattern(regexp = "^[0-9A-Za-z]+$")String nickname, int level, int idInventory, int idUser, int idWorldObject) {
}
