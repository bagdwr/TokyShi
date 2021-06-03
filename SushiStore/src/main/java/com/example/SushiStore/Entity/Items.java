package com.example.SushiStore.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_items")
@AllArgsConstructor
@NoArgsConstructor
public class Items {
    @Id
    @Column(name = "id")
    private Long id;
}
