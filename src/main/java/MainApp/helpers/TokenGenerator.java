package MainApp.helpers;

import MainApp.repo.DataBaseRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
public class TokenGenerator {
    private final DataBaseRepository dbRepository;

    public TokenGenerator(DataBaseRepository da) {
        this.dbRepository = da;
    }

    private static String GenerateToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes.toString();
    }

    public String GenerateTokenForUser() {
        String token;

        do {
            token = GenerateToken();
        } while (dbRepository.ExistsElementInList(DataBaseRepository.TOKENS_LIST_NAME, token));

        dbRepository.AddElementInList(DataBaseRepository.TOKENS_LIST_NAME, token);

        return token;
    }
}