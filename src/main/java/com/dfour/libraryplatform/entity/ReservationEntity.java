package com.dfour.libraryplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false)
    @JsonIgnore
    private UserEntity user;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false)
    @JsonIgnore
    private InventoryEntity item;

    @Column(name = "item_id", nullable = false)
    private long itemId;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @Column(nullable = false)
    private OffsetDateTime expiresAt;

    private OffsetDateTime expiredAt;
}
