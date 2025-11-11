package med.voll.web_application.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Anotação que informa ao Spring que esse é uma classe de configuração, é ele que deve gerenciar.
@EnableWebSecurity // Anotação que ativa as configurações específicas de segurança da aplicação.
public class ConfiguracoesSeguranca {

    @Bean
    public SecurityFilterChain filtrosSegurancao(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        req -> {
                            req.requestMatchers("/css/**", "/js/**", "/assets/**").permitAll();
                            req.anyRequest().authenticated();
                        }
                )
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutSuccessUrl("/login?logout")
                                .permitAll()
                )
                .rememberMe(
                        rememberMe -> rememberMe
                                .key("lembrarDeMim")
                                .alwaysRemember(true) // Usado que o usuário seja sempre lembrado.
                                // .tokenValiditySeconds() // Usado para passar uma quantidade de segundos para que o usuário seja lembrado.
                )
                .csrf(Customizer.withDefaults())
                .build();
    }
}
