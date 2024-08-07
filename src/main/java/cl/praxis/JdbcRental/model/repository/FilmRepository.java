package cl.praxis.JdbcRental.model.repository;

import cl.praxis.JdbcRental.model.dto.Film;

import java.util.List;

public interface FilmRepository {

    List<Film> findAll();
    Film findOne(int id);
    boolean updeat(Film f);
    boolean create(Film f);
    boolean delete(int id);
}
