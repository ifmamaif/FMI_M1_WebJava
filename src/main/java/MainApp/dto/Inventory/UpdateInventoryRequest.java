package MainApp.dto.Inventory;

import com.sun.istack.NotNull;

import javax.validation.constraints.Positive;

public record UpdateInventoryRequest(@NotNull @Positive int id, String items) {
}
