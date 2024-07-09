package picpay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import picpay.entity.Account;
import picpay.enums.UserType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    private Integer accountNumber;

    private String keyPix;

    private String name;

    private String cpf;

    private String email;

    private String password;

    private UserType type;

    private Boolean active;

}
