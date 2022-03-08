package com.ite.libreria.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration  
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {

@Autowired
private DataSource dataSource;

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	auth.jdbcAuthentication().dataSource(dataSource)
	.usersByUsernameQuery("select username, password, enabled from Usuarios where username=?")
	.authoritiesByUsernameQuery(
		"select u.username, p.descripcion from Usuario_Perfiles up " +  
			"inner join Usuarios u on u.username = up.username " +
			"inner join Perfiles p on p.id_perfil = up.id_Perfil " +  
		"where u.username = ?");

}
@Override
protected void configure(HttpSecurity http) throws Exception {

		http
		.csrf().disable()
		.authorizeRequests()
//		 Los recursos estáticos no requieren autenticación
		.antMatchers(
		"/bootstrap/**",  "/img/**", "/css/**", "/js/**", "/db/**").permitAll()
		
		// Las vistas públicas no requieren autenticación
		.antMatchers("/",
		"/login",
		"/cliente/tema/**",
		"/registro",
		"/cliente/buscar",
		"/cliente/verDetalle/**").permitAll()
		
		// Asignar permisos a URLs por ROLES
		.antMatchers("/app/producto/**").hasAnyAuthority("GESTOR","ADMINISTRADOR")
		.antMatchers("/app/usuarios/**").hasAnyAuthority("GESTOR","ADMINISTRADOR")
		.antMatchers("/app/perfiles/**").hasAnyAuthority("ADMINISTRADOR")
		.antMatchers("/app/tipos/**").hasAnyAuthority("GESTOR")
		
		// Todas las demás URLs de la Aplicación requieren autenticación
		.anyRequest().authenticated()
		
		// El formulario de Login no requiere autenticacion
		.and().formLogin().permitAll();
		
		

}

}
