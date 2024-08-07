package cl.praxis.JdbcRental.controller;

import cl.praxis.JdbcRental.JdbcRentalApplication;
import cl.praxis.JdbcRental.model.dto.Film;
import cl.praxis.JdbcRental.model.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/films")
public class FilmController {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcRentalApplication.class);
    FilmService service;

    public FilmController(FilmService service) {
        this.service = service;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("films", service.findAll());
        return "filmsList";
    }


    @GetMapping("/{id}")
    public String findONe(@PathVariable("id") int id, Model model) {
        LOG.info("Ejecutando finOne con el id: " + id);
        model.addAttribute("film", service.findOne(id));
        return "filmEdit";
    }

    @PostMapping
    public String update(@ModelAttribute Film film) {
        LOG.info(film.toString());
        boolean result = service.updeat(film);
        if (result) {
            LOG.info("pelicula: " + film.getTitle() + " correctamente actualizada");
        } else {
            LOG.error("error al actualizar la pelicula: " + film.getTitle());
        }
        return "redirect:/films";
    }

    @GetMapping("/new")
    public String newFilm() {

        return "newFilm";
    }

    @PostMapping("/new")
    public String createNew(@ModelAttribute Film film) {
        boolean result = service.create(film);

        if (result) {
            LOG.info("Pelicula " + film.getTitle() + " agregada correctamente");
        } else {
            LOG.error("Error al agregar la pelicula " + film.getTitle());
        }

        return "redirect:/films";
    }
    @GetMapping("/del/{id}")
    public String delete (@PathVariable("id") int id){
LOG.info("Pelicula con id= "+id+" desde el controller");
        service.delete(id);
        return "redirect:/films" ;
    }

}
