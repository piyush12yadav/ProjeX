package com.piyush.projectManagmentSystem.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentLinkResponse {
    private String paymentLinkId;
    private String paymentLinkUrl;
}
