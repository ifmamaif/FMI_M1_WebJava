package MainApp.dto.Item;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public record GetItemRequest(@NotNull @NotEmpty @Pattern(regexp = "^[0-9A-Za-z]+$")String name) {
}
