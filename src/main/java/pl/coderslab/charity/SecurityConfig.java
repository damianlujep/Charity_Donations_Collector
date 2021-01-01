package pl.coderslab.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;
import pl.coderslab.charity.services.MyMemberDetailsService;


@Configuration
@EnableWebSecurity
@Transactional
@ComponentScan("pl.coderslab.charity")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyMemberDetailsService myMemberDetailsService;

    @Autowired
    public SecurityConfig(MyMemberDetailsService myAdminDetailsService) {
        this.myMemberDetailsService = myAdminDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationSuccessHandler successHandler() {
//        return new MyCustomLoginSuccessHandler("/yourdefaultsuccessurl");
//    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Configuration to read credentials from DB
        auth.userDetailsService(myMemberDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        //add your custom encoding filter as the first filter in the chain
        http.addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class);

        http.authorizeRequests()
                        .antMatchers("/", "/booking/**", "/login", "/registration", "/test").permitAll() //allow public access
                        .antMatchers("/members/**", "/members/booking/**").access("hasRole('ROLE_MEMBER')") //strony, które potrzebują user, admin
                        .antMatchers("/resources/**").permitAll()
                        .antMatchers("/css/**", "/js/**", "/images/**","/fonts/**").permitAll()
                        .anyRequest().authenticated()
                .and()
                .formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateMember")
                        .defaultSuccessUrl("/form");
//                        .successHandler(successHandler());
        //tutaj można dodać log-out
    }
}
