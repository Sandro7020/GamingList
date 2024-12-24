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

    public Review obtenerReviewUsuario(int id, String usuario) {
        return repositorioReview.obtenerEspecifica(usuario, id);
    }

    public Review agregarReview(Review review) {
        repositorioReview.agregar(review);
        return review;
    }

    public boolean eliminarReviewPorId(int id) {
        return repositorioReview.eliminarPorId(id);
    }

    public boolean eliminarReviewPorUsuario(String usuario, int id) {
        return repositorioReview.eliminarPorUsuario(usuario ,id);
    }

    public void modificarReview(Review reviewModificado) {
        Review reviewActual = repositorioReview.obtenerEspecifica(reviewModificado.getNomUsuario(), reviewModificado.getIdJuego());
        repositorioReview.actualizar(reviewActual, reviewModificado);
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
