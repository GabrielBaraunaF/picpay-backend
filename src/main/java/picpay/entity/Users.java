package picpay.entity;

import jakarta.persistence.*;


@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "ID_ACCOUNT", referencedColumnName = "id")
    private Account account;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TYPE")
    private Character type;

    @Column(name = "ACTIVE")
    private Boolean active;

}
