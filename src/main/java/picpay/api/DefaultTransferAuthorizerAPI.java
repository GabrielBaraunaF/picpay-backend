package picpay.api;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DefaultTransferAuthorizerAPI implements  TransferAuthorizerAPI {

    @Override
    public boolean isAuthorized() {
        RestTemplate restTemplate = new RestTemplate();
        String apiResourceUrl = "https://f64d0253a17840aa94d8daa158c65217.api.mockbin.io/";

        TransferAuthorizerResponse transferAuthorizerResponse = restTemplate
                .getForObject(apiResourceUrl , TransferAuthorizerResponse.class);

        return transferAuthorizerResponse.isAuthorized();
    }
}
