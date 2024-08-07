package cl.praxis.JdbcRental.model.service;

import cl.praxis.JdbcRental.model.dto.Film;
import cl.praxis.JdbcRental.model.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImp implements FilmService{

    FilmRepository repository;

    public FilmServiceImp(FilmRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Film> findAll() {
        return repository.findAll();
    }

    @Override
    public Film findOne(int id) {
        return repository.findOne(id);
    }

    @Override
    public boolean updeat(Film f) {

        return repository.updeat(f);
    }

    @Override
    public boolean create(Film f) {

        return repository.create(f);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }
}
