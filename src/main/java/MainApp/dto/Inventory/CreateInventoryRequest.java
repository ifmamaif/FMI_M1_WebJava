package MainApp.dto.Inventory;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public record CreateInventoryRequest(int id,
                                     @NotNull @NotEmpty @Pattern(regexp = "^[0-9A-Za-z]+$") String items) {
}
