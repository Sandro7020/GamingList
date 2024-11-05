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

    public Review agregarReview(Review review) throws Exception {
        repositorioReview.agregar(review);
        return review;
    }
}
