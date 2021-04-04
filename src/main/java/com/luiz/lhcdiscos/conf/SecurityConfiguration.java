package com.luiz.lhcdiscos.conf;

import com.luiz.lhcdiscos.security.local.LoginSuccessHandler;
import com.luiz.lhcdiscos.security.local.UserDetailsServiceImpl;
import com.luiz.lhcdiscos.security.oauth.CustomOAuth2UserService;
import com.luiz.lhcdiscos.security.oauth.OAuth2LoginSuccessHandler;
import com.luiz.lhcdiscos.services.UsuarioService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    private CustomOAuth2UserService oAuth2UserService;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/crud", "/crud/**").hasRole("ADMIN")
                .antMatchers("/data", "/data/**").hasRole("ADMIN")
                .antMatchers("/estatisticas").hasRole("ADMIN")
                .antMatchers("/charge", "/charge/**").hasRole("USER")
                .antMatchers("/albuns", "/albuns/**").permitAll()
                .antMatchers("/merchandise", "/merchandise/**").permitAll()
                .antMatchers("/lancamentos", "/lancamentos/**").permitAll()
                .antMatchers("/livros", "/livros/**").permitAll()
                .antMatchers("/contato", "/contato/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/busca", "/busca/**").permitAll()
                .antMatchers("/produto", "/produto/**").permitAll()
                .antMatchers("/carrinho/**").permitAll()
                .antMatchers("/usuario/create").permitAll()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/oauth2/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/usuario/login")
                    .loginProcessingUrl("/usuario/login")
                    .failureUrl("/usuario/login?error")
                    .successHandler(successHandler()).permitAll()
                .and()
                .oauth2Login()
                    .loginPage("/usuario/login")
                    .userInfoEndpoint().userService(oAuth2UserService)
                    .and()
                    .successHandler(oAuth2LoginSuccessHandler)
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/usuario/logout")).permitAll()
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID");


//        Desabilitar proteção a CSRF para o H2 funcionar
        if (activeProfile.trim().equalsIgnoreCase("test")) {
            http.csrf().disable();
            http.headers().frameOptions().disable();
        }

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new LoginSuccessHandler("/");
    }

    // Forma recomendada de ignorar no filtro de segurança as requisições para recursos estáticos
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/webjars/**", "/css/**", "/js/**", "/img/**");
    }



}
