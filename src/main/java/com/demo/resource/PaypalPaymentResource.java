package com.demo.resource;

import com.demo.dao.PaymentDao;
import com.demo.entity.PaypalPayment;
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
@Path("paypal-payments")
public class PaypalPaymentResource {
    @Inject
    private PaymentDao dao;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response addPaypalPayment(@FormParam("amount") Double amount, @FormParam("paypalId") String paypalId) {
        PaypalPayment payment = new PaypalPayment(amount, paypalId);

        dao.createPaypalPayment(payment);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public JsonArray getPaypalPayments() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder finalArray = Json.createArrayBuilder();
        for (PaypalPayment payment : dao.readAllPaypalPayments()) {
            builder.add("amount", payment.getAmount()).add("cardNumber", payment.getPaypalId());
            finalArray.add(builder.build());
        }

        return finalArray.build();
    }
}
