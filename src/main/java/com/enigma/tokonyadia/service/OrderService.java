package com.enigma.tokonyadia.service;

import com.enigma.tokonyadia.model.request.OrderRequest;
import com.enigma.tokonyadia.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createNewTransaction(OrderRequest orderRequest);
    OrderResponse getOrderById(String id);
    List<OrderResponse> getAllTransaction();
    //update
    //delete
}
