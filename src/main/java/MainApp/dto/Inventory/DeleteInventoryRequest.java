package MainApp.dto.Inventory;

import com.sun.istack.NotNull;

import javax.validation.constraints.Positive;

public record DeleteInventoryRequest(@NotNull @Positive int id) {
}
