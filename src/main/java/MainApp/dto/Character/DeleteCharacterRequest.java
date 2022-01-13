package MainApp.dto.Character;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public record DeleteCharacterRequest(@NotNull @NotEmpty @Pattern(regexp = "^[0-9A-Za-z]+$") String nickname) {
}
