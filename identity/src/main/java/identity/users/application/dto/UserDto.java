package identity.users.application.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
}
