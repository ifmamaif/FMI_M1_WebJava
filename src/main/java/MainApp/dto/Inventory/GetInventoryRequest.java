package MainApp.dto.Inventory;

import com.sun.istack.NotNull;

import javax.validation.constraints.Positive;

public record GetInventoryRequest (@NotNull @Positive int id) {
}
