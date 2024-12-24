package com.info.GamingList.controller;

import com.info.GamingList.model.Review;
import com.info.GamingList.service.ServicioReview;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private static final ServicioReview servicioReview = new ServicioReview();

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> consultarReviews(@PathVariable int id) {
        try {
            List<Review> reviewsId = servicioReview.obtenerReviewsId(id);
            return new ResponseEntity<>(reviewsId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("validar/{usuario}/{id}")
    public ResponseEntity<?> verificarReviewByUsuario(@PathVariable int id, @PathVariable String usuario) {
        try {
            boolean existe = servicioReview.validarReviewUsuario(id, usuario);
            return new ResponseEntity<>(existe, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("consultar/{usuario}/{id}")
    public ResponseEntity<?> consultarReviewByUsuario(@PathVariable int id, @PathVariable String usuario) {
        try {
            Review review = servicioReview.obtenerReviewUsuario(id, usuario);
            if (review != null) {
                return new ResponseEntity<>(review, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/consultar/calificacion/{id}")
    public ResponseEntity<?> consultarCalificacion(@PathVariable int id) {
        try {
            float calificacion = servicioReview.calcularCalificacionJuego(id);
            double calificacionRedondeada = Math.round(calificacion * 100.0) / 100.0;
            return new ResponseEntity<>(calificacionRedondeada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarReview(@RequestBody Review review) {
        try {
            Review nuevaReview = servicioReview.agregarReview(review);
            return new ResponseEntity<>(nuevaReview, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarReviewsId(@PathVariable int id) {
        try {
            boolean eliminado = servicioReview.eliminarReviewPorId(id);
            if (eliminado) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{usuario}/{id}")
    public ResponseEntity<?> eliminarReviewUsuario(@PathVariable String usuario, @PathVariable int id) {
        try {
            boolean eliminado = servicioReview.eliminarReviewPorUsuario(usuario, id);
            if (eliminado) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarReview(@RequestBody Review review) {
        try {
            servicioReview.modificarReview(review);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
