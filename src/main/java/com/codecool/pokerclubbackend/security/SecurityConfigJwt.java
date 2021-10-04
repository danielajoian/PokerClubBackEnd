package com.codecool.pokerclubbackend.security;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.http.HttpMethod;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.config.http.SessionCreationPolicy;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
This class configures Spring Security.
By default Spring Security locks down all your endpoints and one must use HTTP Basic authentication to access anything,
we need to override this with our configuration.
*/
@Configuration
@EnableWebSecurity
public class SecurityConfigJwt extends WebSecurityConfigurerAdapter {

    private final JwtTokenServices jwtTokenServices;

    @Autowired
    private CustomClubDetailsService customClubDetailsService;
    @Autowired
    private CustomPlayerDetailsService customPlayerDetailsService;

    public SecurityConfigJwt(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }

    // By overriding this function and
    // adding the @Bean annotation we can inject the AuthenticationManager into other classes.

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
// configure AuthenticationManager so that it knows from where to load
// user for matching credentials
// Use BCryptPasswordEncoder
        auth.userDetailsService(customClubDetailsService).passwordEncoder(passwordEncoder());
        auth.userDetailsService(customPlayerDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // The main point for configuring Spring Security.
    // In Spring Security every request goes trough a chain of filters; each filter checks the request for something
    // and if one fails the request will fail.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF. Leaving it enabled would ignore GET, HEAD, TRACE, OPTIONS
                // Disable Tomcat's session management. This causes HttpSession to be null and no session cookie to be created
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() // restrict access based on the config below:
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers(HttpMethod.GET, "/jpa/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/jpa/**").permitAll()
                .antMatchers(HttpMethod.GET, "/players/**").permitAll()
                .antMatchers(HttpMethod.POST, "/players/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/players/**").permitAll()
                .antMatchers(HttpMethod.GET, "/clubs/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/clubs").permitAll()
                .antMatchers(HttpMethod.POST, "/clubs/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/clubs/**").permitAll()
                .antMatchers("/authenticateClub").permitAll()
                .antMatchers( "/authenticatePlayer").permitAll()
                .antMatchers(HttpMethod.GET, "/welcome-bean").authenticated()   // allowed only when signed in
                .antMatchers(HttpMethod.PUT, "/players/**").authenticated()     // allowed only when signed in
                .antMatchers(HttpMethod.DELETE, "/players/**").authenticated()  // allowed only when signed in
                .antMatchers(HttpMethod.OPTIONS, "/players/**").authenticated()  // allowed only when signed in
                .antMatchers(HttpMethod.GET, "/clubs/**").authenticated()
                .antMatchers(HttpMethod.GET, "/allClubs/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/clubs/**").authenticated()       // allowed only when signed in
                .antMatchers(HttpMethod.DELETE, "/clubs/**").authenticated()    // allowed only when signed in
                .antMatchers(HttpMethod.OPTIONS, "/clubs/**").authenticated()
                .antMatchers(HttpMethod.POST, "/jpa/**").authenticated()        // allowed only when signed in
                .antMatchers(HttpMethod.PUT, "/jpa/**").authenticated()         // allowed only when signed in
                .antMatchers(HttpMethod.DELETE, "/jpa/**").authenticated()      // allowed only when signed in
                .antMatchers(HttpMethod.OPTIONS, "/jpa/**").authenticated()         // allowed only when signed in
                .anyRequest()
                .authenticated();

        http.addFilterBefore(new JwtTokenFilter(jwtTokenServices), UsernamePasswordAuthenticationFilter.class);

    }
}

