package com.info.GamingList.service;

import com.info.GamingList.model.Review;
import com.info.GamingList.repository.RepositorioReview;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioReview {

    private static final RepositorioReview repositorioReview = new RepositorioReview();

    public List<Review> obtenerReviewsId(int id) {
        return repositorioReview.obtenerPorId(id);
    }

    public boolean validarReviewUsuario(int id, String usuario) {
        List<Review> reviews = obtenerReviewsId(id);
        for (Review review : reviews) {
            if (review.getNomUsuario().equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    public Review agregarReview(Review review) {
        repositorioReview.agregar(review);
        return review;
    }

    public void eliminarReview(String usuario, int id) {
        repositorioReview.eliminar(usuario ,id);
    }

    public float calcularCalificacionJuego(int id) {
        List<Review> reviews = repositorioReview.obtenerPorId(id);
        float calificacionPromedio = 0;
        if (!reviews.isEmpty()) {
            for (Review review : reviews) {
                calificacionPromedio += review.getCalificacion();
            }
            return calificacionPromedio / reviews.size();
        }
        return 0;
    }
}
