package med.voll.web_application.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public UserDetailsService dadosUsuariosCadastrados() {
        UserDetails usuario1 = User.builder()
                .username("joao@email.com")
                .password("{noop}joao123")
                .build();
        UserDetails usuario2 = User.builder()
                .username("maria@email.com")
                .password("{noop}maria123")
                .build();
        UserDetails usuario3 = User.builder()
                .username("gustavo@email.com")
                .password("{noop}gustavo123")
                .build();

        return new InMemoryUserDetailsManager(usuario1, usuario2, usuario3);
    }

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
                        form ->
                                form.loginPage("/login").defaultSuccessUrl("/").permitAll()
                )
                .logout(
                        logout ->
                                logout.logoutSuccessUrl("/logout").permitAll()
                )
                .build();
    }
}
