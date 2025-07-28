package com.piyush.projectManagmentSystem.controller;

import com.piyush.projectManagmentSystem.entity.PlanType;
import com.piyush.projectManagmentSystem.entity.User;
import com.piyush.projectManagmentSystem.responce.PaymentLinkResponse;
import com.piyush.projectManagmentSystem.service.UserService;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Value("${razorpay.api.key}")
    private String apiKey;
    @Value("${razorpay.api.secret}")
    private String apiSecret;

    @Autowired
    private UserService userService;

    @PostMapping("/{planType}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @RequestHeader("Authorization") String jwt,
            @PathVariable PlanType planType
            ) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);

        int amount = 799*100;

        if(planType.equals(PlanType.ANNUALY)){
            amount = amount*12;
            amount =(int)(amount * 0.7);
        }


            RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount" , amount);
            paymentLinkRequest.put("currency" , "INR");

            JSONObject customer = new JSONObject();
            customer.put("name" , user.getFullName());
            customer.put("email" ,user.getEmail());

            paymentLinkRequest.put("customer" , customer);
            JSONObject notify = new JSONObject();
            notify.put("email",true);
            paymentLinkRequest.put("notify" , notify);

            paymentLinkRequest.put("callbackUrl" , "http://localhost:5173/upgrade_plan/success?planType"+planType);

            PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);
            String paymentLinkId = paymentLink.get("id");
            String paymentLinkUrl = paymentLink.get("short_url");

            PaymentLinkResponse res = new PaymentLinkResponse();
            res.setPaymentLinkUrl(paymentLinkUrl);
            res.setPaymentLinkId(paymentLinkId);


            return new ResponseEntity<>(res, HttpStatus.CREATED);

    }

}
