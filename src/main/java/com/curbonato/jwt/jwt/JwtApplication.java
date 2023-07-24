package com.curbonato.jwt.jwt;

import com.curbonato.jwt.jwt.entity.Role;
import com.curbonato.jwt.jwt.entity.User;
import com.curbonato.jwt.jwt.repository.RoleRepository;
import com.curbonato.jwt.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class JwtApplication implements ApplicationRunner {
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		System.out.println("Main");
		SpringApplication.run(JwtApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {

		Role admin = roleRepository.save(new Role().builder().name("ROLE_ADMIN").build());
		System.out.println(admin.getId());
		Role client = roleRepository.save(new Role().builder().name("ROLE_CLIENT").build());

		Set<Role> superRol  = new HashSet<Role>();
		superRol.add(admin);

		Set<Role> clientRol  = new HashSet<Role>();
		clientRol.add(client);

		userRepository.save(new User().builder().
																firstName("Admin")
																.email("admin@example.com")
																.password(passwordEncoder.encode("123"))
																.enabled(true).roles(superRol).build());
		 userRepository.save(new User().builder().
				firstName("Client")
				.email("client@example.com")
				.password(passwordEncoder.encode("123"))
				.enabled(true).roles(clientRol).build());



	}
}
