package com.dfour.libraryplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@Table(name = "borrowings")
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false)
    private UserEntity user;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false)
    private InventoryEntity item;

    @Column(name = "item_id", nullable = false)
    private long itemId;

    @Column(nullable = false)
    @CreationTimestamp
    private OffsetDateTime borrowDate;

    @Column(nullable = false)
    private OffsetDateTime returnDate;

    @Column
    private OffsetDateTime returnedDate;
}
