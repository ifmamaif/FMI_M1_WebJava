package MainApp.dto.User;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public record UpdateUserRequest(@NotNull @NotEmpty @Pattern(regexp = "^[0-9A-Za-z]+$")String username,
                                @NotNull @NotEmpty String oldPassword,
                                @NotNull @NotEmpty String newPassword) {
}
