package edu.donstu.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.donstu.service.services.security.UserService;

@SpringBootConfiguration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String SELECT = "select username, password, enabled from s_user where username = ?";
    @Autowired
    private UserService userService;
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/registration").not().fullyAuthenticated()
            .antMatchers("/admin/**", "/*/delete", "/*/add").hasRole("ADMIN")
            .antMatchers("/*", "/*/*").permitAll()
            .and().formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/books", true)
            .and().logout().permitAll().logoutSuccessUrl("/books")
            .and().logout().deleteCookies("JSESSIONID")
            .and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(120);
        super.configure(http);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
        auth.jdbcAuthentication().dataSource(dataSource).authoritiesByUsernameQuery(SELECT).usersByUsernameQuery(SELECT);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
