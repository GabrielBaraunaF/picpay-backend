package picpay.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_ACCOUNT_PAYEE", referencedColumnName = "number")
    private  Account payee;

    @ManyToOne
    @JoinColumn(name = "ID_ACCOUNT_PAYER", referencedColumnName = "number")
    private Account payer;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "DATE")
    private LocalDateTime date;
}
