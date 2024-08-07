package cl.praxis.JdbcRental.model.service;

import cl.praxis.JdbcRental.model.dto.Film;

import java.util.List;

public interface FilmService {
    List<Film> findAll();
    Film findOne(int id);
    boolean updeat(Film f);
    boolean create(Film f);
    boolean delete(int id);
}
