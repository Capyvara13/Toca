# Things To Do

- JpaClassRepository (Query)
- ClassSpecification
- ClassPersistenceMapper (Impl)
- ClassPersistenceAdapter (repository pub)
- ClassUsecase
- ClassController


// Anotação de Uso de Segurança
@Configuration
@EnableWebSecurity // Habilita a segurança web
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/home").permitAll() // URLs públicas
                .anyRequest().authenticated() // Resto precisa de auth
            )
            .formLogin((form) -> form.loginPage("/login").permitAll()); // Login customizado

        return http.build();
    }
}
