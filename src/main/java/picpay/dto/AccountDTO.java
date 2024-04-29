package picpay.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private Integer accountIdLogged;
    private String keypix;
    private Double value;
}
