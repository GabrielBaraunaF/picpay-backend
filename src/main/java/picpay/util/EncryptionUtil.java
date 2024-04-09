package picpay.util;

import picpay.exception.ApplicationException;

import java.security.MessageDigest;

public class EncryptionUtil {
    private String generateHashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new ApplicationException("falha ao gerar senha");
        }
    }
}
