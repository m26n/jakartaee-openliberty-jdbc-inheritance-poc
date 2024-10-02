package com.demo.resource;

import com.demo.dao.OrderDao;
import com.demo.dao.PaymentDao;
import com.demo.entity.Order;
import com.demo.entity.Payment;
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

import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Path("orders")
public class OrderResource {
    @Inject
    private OrderDao dao;

    @Inject
    private PaymentDao paymentDao;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response addPaypalPayment(@FormParam("payments") List<Long> paymentIds, @FormParam("orderName") String name) {
        Order order = new Order(name);
        order.setPayment(new ArrayList<>());
        for (Long paymentId : paymentIds) {
            Payment payment = paymentDao.findPaypalPayment(paymentId);
            order.getPayment().add(payment);
        }
        dao.createOrder(order);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public JsonArray getPaypalPayments() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder finalArray = Json.createArrayBuilder();
        for (Order payment : dao.readAllOrders()) {
            builder.add("amount", payment.getId());
            finalArray.add(builder.build());
        }

        return finalArray.build();
    }
}
