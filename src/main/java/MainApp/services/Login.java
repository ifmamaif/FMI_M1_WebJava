package MainApp.services;

import MainApp.helpers.TokenGenerator;
import MainApp.repo.DataBaseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {
    private final TokenGenerator tokenGenerator;
    private final DataBaseRepository dbRepository;

    public Login(TokenGenerator tokenGenerator, DataBaseRepository dataBaseService) {
        this.tokenGenerator = tokenGenerator;
        this.dbRepository = dataBaseService;
    }

    private Boolean LoginToPersistanceDB(String username, String password) {
        // we don't have persistance DB, we will assume is ok like we register this user;
        return true;
    }

    @PostMapping("/login")
    public String loginUser(@RequestHeader(name = "username") String username, @RequestHeader(name = "password") String password) {
        String pass = dbRepository.GetValueInHash(DataBaseRepository.USERPASS_HASH_NAME, username);
        if (pass == null) {
            if (!LoginToPersistanceDB(username, password)) {
                return "Invalid credentials!";
            }

            dbRepository.AddElementInHash(DataBaseRepository.USERPASS_HASH_NAME, username, password);

            String token = tokenGenerator.GenerateTokenForUser();
            dbRepository.AddElementInHash(DataBaseRepository.TOKENS_HASH_NAME, username, token);
            return token;
        }

        if (!pass.equals(password)) {
            return "Invalid credentials!";
        }

        String token = dbRepository.GetValueInHash(DataBaseRepository.TOKENS_HASH_NAME, username);
        if (token == null) {
            token = tokenGenerator.GenerateTokenForUser();
            dbRepository.AddElementInHash(DataBaseRepository.TOKENS_HASH_NAME, username, token);
        }

        return token;
    }

    @GetMapping("/checkuser")
    public String checkuser(@RequestHeader(name = "username") String username, @RequestHeader(name = "password") String password) {
        String pass = dbRepository.GetValueInHash(DataBaseRepository.USERPASS_HASH_NAME, username);
        if (pass == null) {
            return "Unknown user";
        }

        if (!pass.equals(password)) {
            return "Invalid credentials! The password is " + pass;
        }

        return "User ok";
    }
}