package MainApp.dto.Character;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public record CreateCharacterRequest(@Positive
                                     int id,
                                     @NotNull @NotEmpty @Pattern(regexp = "^[0-9A-Za-z]+$")
                                     String nickname,
                                     @Positive
                                     int level,
                                     @Positive
                                     int idInventory,
                                     @Positive
                                     int idUser,
                                     @Positive
                                     int idWorldObject) {
}
