package College.Library.Management.System.Project.config;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtKeyGenerator {
    public static void main(String[] args)throws Exception{
        KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey=keyGenerator.generateKey();

        String encodedKey= Base64.getEncoder()
                .encodeToString(secretKey.getEncoded());

        System.out.println(encodedKey);
    }

}
