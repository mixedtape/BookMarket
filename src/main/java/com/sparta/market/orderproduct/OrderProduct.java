package com.sparta.market.orderproduct;

import com.sparta.market.order.Order;
import com.sparta.market.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderProduct {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private Long quantity;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(nullable = false, name = "customer_order_id")
    private Order order;

    @Column(nullable = false)
    private Long price;
    public OrderProduct(Order order, Product product, Long quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        calculatePrice();
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public void calculatePrice() {
        if (product != null && quantity != null) {
            this.price = product.getPrice() * quantity;
        }
    }

}
