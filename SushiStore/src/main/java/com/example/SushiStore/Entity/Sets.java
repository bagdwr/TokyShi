package com.example.SushiStore.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "sets")
@NoArgsConstructor
@AllArgsConstructor
public class Sets {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "set_picture")
    private String set_picture;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private Integer price;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Sushi>sushiList;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Rolls>rollsList;
}
