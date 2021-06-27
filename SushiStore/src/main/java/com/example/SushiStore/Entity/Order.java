package com.example.SushiStore.Entity;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@Table(name = "t_order")
@AllArgsConstructor
@NoArgsConstructor
public class Order  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "entrance")
    @Nullable
    private String entrance;

    @Column(name = "floor")
    private String floor;

    @Column(name = "flat")
    private String flat;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "price")
    private Integer overallPrice;

//    @Column(name = "client")
//    private Users user;
//
//    @Column(name = "order")
//    private Basket basket;
}
