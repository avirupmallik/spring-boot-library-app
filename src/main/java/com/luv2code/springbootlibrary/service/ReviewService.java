package com.luv2code.springbootlibrary.service;

import com.luv2code.springbootlibrary.dao.BookRepository;
import com.luv2code.springbootlibrary.dao.ReviewRepository;
import com.luv2code.springbootlibrary.entity.Review;
import com.luv2code.springbootlibrary.requestmodels.ReviewRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Service
@Transactional
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws  Exception {

        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail,reviewRequest.getBookId());

        if(validateReview !=null){
            throw  new Exception("Review already created");
        }

        Review review = new Review();
        review.setBookId(reviewRequest.getBookId());
        review.setRating(reviewRequest.getRating());
        review.setUserEmail(userEmail);

        if(reviewRequest.getReviewDescription().isPresent()) {
            review.setReviewDescription(reviewRequest.getReviewDescription().map(Object :: toString).orElse(null));
        }
        review.setDate(new Date());
        reviewRepository.save(review);

    }

    public Boolean userReviewListed(String userEmail,Long bookId) {
       Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail,bookId);
       if(validateReview != null){
           return true;
       }
       else{
           return  false;
       }
    }



}
