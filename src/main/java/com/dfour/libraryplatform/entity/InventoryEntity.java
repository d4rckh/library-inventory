package com.dfour.libraryplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "book_id", insertable = false, updatable = false, nullable = false)
    @ManyToOne(targetEntity = BookEntity.class, fetch = FetchType.EAGER)
    private BookEntity book;

}
