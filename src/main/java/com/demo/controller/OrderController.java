package com.demo.controller;

import com.demo.entity.Order;
import com.demo.entity.Payment;
import com.demo.entity.PaypalPayment;
import com.demo.repository.OrderRepository;
import com.demo.repository.PaymentRepository;
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

import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Path("orders")
public class OrderController {
    @Inject
    private OrderRepository dao;

    @Inject
    private PaymentRepository paymentRepository;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response addOrder(@FormParam("payments") List<Long> paymentIds, @FormParam("orderName") String name) {
        Order order = new Order(name);
        order.setPayment(new ArrayList<>());

        for (Long paymentId : paymentIds) {
            Payment payment = paymentRepository.findPaypalPayment(paymentId);
            order.getPayment().add(payment);
        }

        dao.createOrder(order);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public JsonArray getOrders() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder finalArray = Json.createArrayBuilder();

        for (Order order : dao.readAllOrders()) {
            builder
                    .add("id", order.getId())
                    .add("name", order.getOrderName());

            JsonArrayBuilder paymentsArray = Json.createArrayBuilder();

            for (Payment payment : order.getPayment()) {
                JsonObjectBuilder builderPayments = Json.createObjectBuilder();
                builderPayments
                        .add("id", payment.getId())
                        .add("paypalId", payment.getAmount());

                if (payment instanceof PaypalPayment) {
                    builderPayments
                            .add("paypalId", ((PaypalPayment) payment).getPaypalId());
                }

                paymentsArray.add(builderPayments);
            }

            builder.add("payments", paymentsArray);
            finalArray.add(builder.build());
        }

        return finalArray.build();
    }
}
