package picpay.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_ACCOUNT", referencedColumnName = "number")
    private Account account;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CPF", updatable = false)
    private String cpf;

    @Column(name = "EMAIL", updatable = false)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TYPE")
    private Character type;

    @Column(name = "ACTIVE")
    private Boolean active;

}
