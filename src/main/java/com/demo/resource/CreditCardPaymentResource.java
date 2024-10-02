package com.demo.resource;

import com.demo.dao.PaymentDao;
import com.demo.entity.CreditCardPayment;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Path("credit-card-payments")
public class CreditCardPaymentResource {
    @Inject
    private PaymentDao dao;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response addCreditCardPayment(@FormParam("amount") Double amount, @FormParam("cardNumber") String cardNumber) {
        CreditCardPayment payment = new CreditCardPayment(amount, cardNumber);

        dao.createCreditCardPayment(payment);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public JsonArray getCreditCardPayments() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder finalArray = Json.createArrayBuilder();
        for (CreditCardPayment payment : dao.readAllCreditCardPayments()) {
            builder.add("amount", payment.getAmount()).add("cardNumber", payment.getCardNumber());
            finalArray.add(builder.build());
        }

        return finalArray.build();
    }
}
