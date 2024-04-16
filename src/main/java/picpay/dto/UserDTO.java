package picpay.dto;

import lombok.Data;
import picpay.entity.Account;
@Data
public class UserDTO {

    private Integer id;

    private Integer accountNumber;

    private String keyPix;

    private String name;

    private String cpf;

    private String email;

    private String password;

    private Character type;

    private Boolean active;

}
