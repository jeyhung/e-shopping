package az.feedy.configs;

import az.feedy.helpers.JwtHelper;
import az.feedy.users.domain.constant.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import az.feedy.users.application.service.UserService;

@EnableWebSecurity
class WebSecurity extends WebSecurityConfigurerAdapter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtHelper jwtHelper;

    public WebSecurity(BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserService userService,
                       UserDetailsService userDetailsService,
                       JwtHelper jwtHelper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()

                //make swagger endpoints public
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources/configuration/security").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()

                //Settings
                .antMatchers("/settings/environment").permitAll()
                .antMatchers("/settings/android-version").permitAll()
                .antMatchers("/settings/ios-version").permitAll()

                //Faqs
                .antMatchers("/faqs/find-all").permitAll()

                //users
                .antMatchers(HttpMethod.POST, "/users/sign-in").permitAll()
                .antMatchers(HttpMethod.POST, "/users/refresh-sign-in").permitAll()
                .antMatchers(HttpMethod.POST, "/users/sign-up").permitAll()
                .antMatchers(HttpMethod.GET, "/users/genders").permitAll()
                .antMatchers(HttpMethod.POST, "/users/confirm/email/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users/confirm/mobile/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users/update/password/**").permitAll()

                .antMatchers(HttpMethod.POST, "/users/block/**").hasAuthority(Role.Administrator.name())
                .antMatchers(HttpMethod.POST, "/users/unblock/**").hasAuthority(Role.Administrator.name())

                .antMatchers(HttpMethod.POST, "/users/otps/resend/mobile/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users/otps/check-expire/mobile/**").permitAll()

                .antMatchers(HttpMethod.POST, "/users/otps/resend/email/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users/otps/check-expire/email/**").permitAll()

                .antMatchers(HttpMethod.POST, "/feedbacks/approve/**").hasAuthority(Role.Administrator.name())
                .antMatchers(HttpMethod.POST, "/feedbacks/decline/**").hasAuthority(Role.Administrator.name())

                //PRIVATE ENDPOINTS
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(jwtAuthenticationFilter())
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager(), userService, jwtHelper);
        jwtAuthenticationFilter.setFilterProcessesUrl("/users/sign-in");
        return jwtAuthenticationFilter;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}