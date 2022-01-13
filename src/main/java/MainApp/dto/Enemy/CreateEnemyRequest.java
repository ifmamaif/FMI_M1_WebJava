package MainApp.dto.Enemy;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public record CreateEnemyRequest(@NotNull @NotEmpty @Pattern(regexp = "^[0-9A-Za-z]+$")String name,
                                 @NotNull @Positive int level) {
}
