//package com.luv2code.springbootlibrary.controller;
//
//import com.luv2code.springbootlibrary.requestmodels.PaymentInfoRequest;
//import com.luv2code.springbootlibrary.service.PaymentService;
//import com.luv2code.springbootlibrary.utils.ExtractJWT;
//import com.stripe.exception.StripeException;
//import com.stripe.model.PaymentIntent;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin("https://localhost:3000")
//@RestController
//@RequestMapping("/api/payment/secure")
//@AllArgsConstructor
//public class PaymentController {
//
//    private final PaymentService paymentService;
//
//    @PostMapping("/payment-intent")
//    public ResponseEntity<String> createPaymentIntent(PaymentInfoRequest paymentInfoRequest ) throws StripeException {
//
//        PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentInfoRequest);
//        String paymentStr = paymentIntent.toJson();
//        return  new ResponseEntity<>(paymentStr, HttpStatusCode.valueOf(200));
//
//
//    }
//
//    @PutMapping("/payment-complete")
//    public ResponseEntity<String> stripePaymentComplete(@RequestHeader(value = "Authorization") String token) throws Exception{
//
//        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
//        if(userEmail == null){
//            throw new Exception("User email is missing");
//        }
//        return paymentService.stripePayment(userEmail);
//    }
//
//
//
//}
