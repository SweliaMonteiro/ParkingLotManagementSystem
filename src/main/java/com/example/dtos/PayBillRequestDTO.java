package com.example.dtos;

import com.example.enums.PaymentMode;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class PayBillRequestDTO {

    private long billId;

    private Map<PaymentMode, Integer> amountPerPaymentMode;

}
