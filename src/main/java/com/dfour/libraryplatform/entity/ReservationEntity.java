package com.dfour.libraryplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean cancelled;

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
    private OffsetDateTime createdAt;

    @Column(nullable = false)
    private OffsetDateTime expiresAt;

    private OffsetDateTime expiredAt;
}