package com.parfumerie.catalog.service;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    public PaymentService(@Value("${stripe.api.key}") String stripeApiKey) {
        Stripe.apiKey = stripeApiKey;
    }

    public PaymentIntent createPaymentIntent(Double amount, String currency) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", Math.round(amount * 100));
        params.put("currency", currency);
        params.put("payment_method_types", java.util.List.of("card"));
        return PaymentIntent.create(params);
    }

    public Event constructEvent(String payload, String sigHeader, String webhookSecret) {
        return Webhook.constructEvent(payload, sigHeader, webhookSecret);
    }

    public boolean isPaymentSuccessful(Event event) {
        return event != null && "payment_intent.succeeded".equals(event.getType());
    }
}
