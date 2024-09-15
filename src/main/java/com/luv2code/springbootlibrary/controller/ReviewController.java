package com.luv2code.springbootlibrary.controller;


import com.luv2code.springbootlibrary.requestmodels.ReviewRequest;
import com.luv2code.springbootlibrary.service.ReviewService;
import com.luv2code.springbootlibrary.utils.ExtractJWT;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@AllArgsConstructor
@CrossOrigin("https://localhost:3000")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/secure")
    public void postReview(@RequestHeader(value = "Authorization") String token,
                           @RequestBody ReviewRequest reviewRequest) throws  Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if(userEmail == null){
            throw  new Exception("User email is missing ");
        }
        reviewService.postReview(userEmail,reviewRequest);
    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(value = "Authorization") String token,
                                    @RequestParam Long bookId) throws  Exception{

        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");

        if(userEmail == null){
            throw  new Exception("User email is missing ");
        }
       return reviewService.userReviewListed(userEmail,bookId);
    }


}
