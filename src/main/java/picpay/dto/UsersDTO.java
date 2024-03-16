package picpay.dto;

import lombok.Data;
import picpay.entity.Account;
@Data
public class UsersDTO {

    private Integer id;

    private Account account;

    private String name;

    private String cpf;

    private String email;

    private String password;

    private Character type;

    private Boolean active;

}
