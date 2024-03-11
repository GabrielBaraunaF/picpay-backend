package picpay.entity;

import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NUMBER")
    private Integer number;

    @Column(name = "PIX")
    private String pix;

    @Column(name = "BALANCE")
    private Double balance;


}
