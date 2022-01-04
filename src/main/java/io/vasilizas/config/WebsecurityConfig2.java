//package io.vasilizas.config;
//
//import io.vasilizas.bean.db.User;
//import io.vasilizas.repositories.jpa.UserRepository;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import java.time.LocalDateTime;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class WebsecurityConfig2 extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .anyRequest().authenticated()
//                )
//                .oauth2Login(withDefaults());
//    }
//
//    @Bean
//    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
//        return map -> {
//            String id = (String) map.get("sub");
//
//            User user = userRepository.findById(id).orElseGet(() -> {
//                User newUser = new User();
//                newUser.setId(id);
//                newUser.setName((String) map.get("name"));
//                newUser.setEmail((String) map.get("email"));
//                newUser.setGender((String) map.get("gender"));
//                newUser.setLocale((String) map.get("locale"));
//                newUser.setUserpic((String) map.get("picture"));
//                return newUser;
//            });
//            user.setLastVisit(LocalDateTime.now());
//            return userRepository.save(user);
//        };
//    }
//}
