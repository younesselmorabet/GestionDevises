package ma.upf.gestiondevises;  // ton package principal

import ma.upf.gestiondevises.entities.Devise;
import ma.upf.gestiondevises.entities.Utilisateur;
import ma.upf.gestiondevises.repository.DeviseRepository;
import ma.upf.gestiondevises.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ... autres imports si tu en as

@SpringBootApplication
public class GestionDevisesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionDevisesApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(DeviseRepository deviseRepo,
                               UtilisateurRepository userRepo,
                               PasswordEncoder encoder) {
        return args -> {
            // Devises (seulement si la table est vide)
            if (deviseRepo.count() == 0) {
                deviseRepo.save(new Devise("MAD", "Dirham Marocain", 1.0));
                deviseRepo.save(new Devise("USD", "Dollar Américain", 0.10));   // ≈ 1 USD = 10 MAD
                deviseRepo.save(new Devise("EUR", "Euro", 0.092));             // ≈ 1 EUR ≈ 10.87 MAD
            }

            // Utilisateurs (seulement si vide)
            if (userRepo.count() == 0) {
                userRepo.save(new Utilisateur("admin", encoder.encode("admin123"), "ADMIN"));
                userRepo.save(new Utilisateur("user", encoder.encode("user123"), "USER"));
            }
        };
    }
}