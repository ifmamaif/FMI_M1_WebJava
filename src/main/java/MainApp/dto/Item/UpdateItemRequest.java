package MainApp.dto.Item;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public record UpdateItemRequest(@NotNull @NotEmpty @Pattern(regexp = "^[0-9A-Za-z]+$")String name, String description, String whatDo) {
}
