package spring.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {


    @Bean
    UserDetailsManager users(DataSource dataSource) {

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        return users;
    }

//    @Bean
//    public UserDetailsService userDetailsService() throws Exception {
//

        //        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("max").password("max").roles("EMPLOYEE").build());
//        manager.createUser(User.withDefaultPasswordEncoder().username("kate").password("kate").roles("SECRETARY").build());
//        manager.createUser(User.withDefaultPasswordEncoder().username("liza").password("liza").roles("SECRETARY","DIRECTOR").build());
//        return manager;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("EMPLOYEE", "SECRETARY", "DIRECTOR")
                .antMatchers("/secretary_info").hasRole("SECRETARY")
                .antMatchers("/director_info").hasRole("DIRECTOR")
                .and().formLogin().permitAll();
        return http.build();
    }

    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
            dataSource.setUser("root");
            dataSource.setPassword("admin");
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder.username("max").password("max").roles("employee"))
//                .withUser(userBuilder.)
//                .withUser(userBuilder.username("liza").username("liza").roles("secretary","director"));
//    }
}
