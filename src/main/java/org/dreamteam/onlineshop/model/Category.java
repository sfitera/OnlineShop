package org.dreamteam.onlineshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table (name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
}
