package MainApp.dto.WorldObject;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public record CreateWorldObjectRequest(@NotNull @NotEmpty @Pattern(regexp = "^[0-9A-Za-z]+$")String name,
                                       String position,
                                       String description,
                                       String whatDo) {
}
