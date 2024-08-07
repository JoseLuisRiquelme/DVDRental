package cl.praxis.JdbcRental.model.repository;

import cl.praxis.JdbcRental.JdbcRentalApplication;
import cl.praxis.JdbcRental.model.dto.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FilmRepositoryImp implements FilmRepository {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcRentalApplication.class);

    JdbcTemplate template;

    public FilmRepositoryImp(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Film> findAll() {
        String sql = "select * from film order by film_id asc";
        return template.query(sql, new BeanPropertyRowMapper<>(Film.class));
    }

    @Override
    public Film findOne(int id) {
        String sql = "select * from film where film_id=?";
        LOG.info("id= "+id);
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Film.class));
    }

    @Override
    public boolean updeat(Film f) {
        Film filmToUpdate = findOne(f.getFilmId());
        if (filmToUpdate != null) {
            String sql = "update film set title=?, description=?, release_year=?, language_id=?, rental_duration=?, rental_rate=?, length=?, replacement_cost=?, rating=?::mpaa_rating, special_features=?::text[] where film_id=?";
            int rowsAffected = template.update(sql, ps -> {
                ps.setString(1, f.getTitle());
                ps.setString(2, f.getDescription());
                ps.setInt(3, f.getReleaseYear());
                ps.setInt(4, f.getLanguageId());
                ps.setFloat(5, f.getRentalDuration());
                ps.setFloat(6, f.getRentalRate());
                ps.setInt(7, f.getLength());
                ps.setFloat(8, f.getReplacementCost());
                ps.setString(9, f.getRating());
                ps.setString(10, f.getSpecialFeatures());
                ps.setInt(11, f.getFilmId());
            });
            return rowsAffected > 0;
        } else {
            LOG.info("Pelicula no encontrada con el id: " + f.getFilmId());
            return false;
        }
    }

    @Override
    public boolean create(Film f) {
        LOG.info(f.toString());
        String sql="insert into film(film_id,title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) values (?,?,?,?,?,?,?,?,?,?::mpaa_rating,?::text[])";
        int rowsAffected = template.update(sql, ps -> {
            ps.setInt(1, f.getFilmId());
            ps.setString(2, f.getTitle());
            ps.setString(3, f.getDescription());
            ps.setInt(4, f.getReleaseYear());
            ps.setInt(5, f.getLanguageId());
            ps.setFloat(6, f.getRentalDuration());
            ps.setFloat(7, f.getRentalRate());
            ps.setInt(8, f.getLength());
            ps.setFloat(9, f.getReplacementCost());
            ps.setString(10, f.getRating());
            ps.setString(11, f.getSpecialFeatures());

        });
        return rowsAffected > 0;
    }


    @Override
    public boolean delete(int id) {

        LOG.info("Borrando la pelicula con el id= "+id);
        String sql="delete from film where film_id=?";
        int rowAffected=template.update(sql,id);
        return rowAffected>0;
    }
}

