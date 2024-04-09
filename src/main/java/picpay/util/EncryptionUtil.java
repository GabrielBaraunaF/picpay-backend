package picpay.util;

import lombok.extern.slf4j.Slf4j;
import picpay.exception.ApplicationException;

import java.security.MessageDigest;

@Slf4j
public class EncryptionUtil {
    public static String generateHash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception ex) {
            log.error(ex.getMessage(),ex);
            throw new ApplicationException("falha ao gerar senha");
        }
    }
}
