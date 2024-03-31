package picpay.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class TransferAuthorizerResponse implements Serializable {

   private boolean authorized;
}


