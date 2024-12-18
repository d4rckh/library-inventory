package com.dfour.libraryplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;

@Entity
@Data
@Builder
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false)
    @JsonIgnore
    private UserEntity user;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @JoinColumn(name = "book_id", insertable = false, updatable = false, nullable = false)
    @ManyToOne(targetEntity = BookEntity.class, fetch = FetchType.EAGER)
    private BookEntity book;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean deleted;

    @CreatedDate
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
