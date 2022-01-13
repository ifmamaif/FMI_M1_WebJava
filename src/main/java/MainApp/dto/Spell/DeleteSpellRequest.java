package MainApp.dto.Spell;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public record DeleteSpellRequest(@NotNull @NotEmpty @Pattern(regexp = "^[0-9A-Za-z]+$")String name) {
}
