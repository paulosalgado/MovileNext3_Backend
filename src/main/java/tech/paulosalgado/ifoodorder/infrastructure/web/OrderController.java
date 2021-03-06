package tech.paulosalgado.ifoodorder.infrastructure.web;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.paulosalgado.ifoodorder.application.order.OrderDTO;
import tech.paulosalgado.ifoodorder.application.order.OrderFactory;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerCreationException;
import tech.paulosalgado.ifoodorder.domain.order.Order;
import tech.paulosalgado.ifoodorder.domain.order.OrderService;
import tech.paulosalgado.ifoodorder.domain.order.exception.OrderCreationException;
import tech.paulosalgado.ifoodorder.domain.order.exception.OrderNotFoundException;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductCreationException;

import java.util.UUID;

@RestController
@Api(tags = "Order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable("orderId") UUID orderId) throws OrderNotFoundException {

        Order order = service.findById(orderId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(OrderFactory.getDTO(order));
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> postOrder(@RequestBody OrderDTO orderDTO) throws OrderCreationException, ProductCreationException, CustomerCreationException {

        Order order = OrderFactory.getOrder(orderDTO);
        order = service.save(order);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OrderFactory.getSimpleDTO(order));
    }

}
