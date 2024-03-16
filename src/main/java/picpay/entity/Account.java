package picpay.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMBER")
    private Integer number;

    @Column(name = "PIX")
    private String pix;

    @Column(name = "BALANCE")
    private Double balance;


}
