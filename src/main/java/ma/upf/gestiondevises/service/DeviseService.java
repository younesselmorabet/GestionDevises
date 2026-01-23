package ma.upf.gestiondevises.service;

import ma.upf.gestiondevises.entities.Devise;
import ma.upf.gestiondevises.repository.DeviseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviseService {

    @Autowired
    private DeviseRepository deviseRepository;

    public List<Devise> findAll() {
        return deviseRepository.findAll();
    }

    public Optional<Devise> findById(Long id) {
        return deviseRepository.findById(id);
    }

    public Devise findByCode(String code) {
        return deviseRepository.findByCode(code);
    }

    public Devise save(Devise devise) {
        return deviseRepository.save(devise);
    }

    public void deleteById(Long id) {
        deviseRepository.deleteById(id);
    }
}