package med.voll.web_application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Anotação utilizada no lugar do @RestController por se tratar de uma aplicação web.
public class LoginController {

    @GetMapping("/login")
    public String carregaPaginaListagem() {
        return "autenticacao/login";
    }

    @GetMapping("/logout")
    public String carregaPaginaLogout() {
        return "autenticacao/logout";
    }
}
