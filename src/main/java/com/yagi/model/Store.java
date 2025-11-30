package com.yagi.model;

import java.time.LocalDateTime;

import com.yagi.domain.StoreStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "storeAdmin")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @ManyToOne(cascade = jakarta.persistence.CascadeType.MERGE)
    private User storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    @jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
    private StoreStatus status;

    @Embedded
    @jakarta.persistence.AttributeOverrides({
            @jakarta.persistence.AttributeOverride(name = "address", column = @Column(nullable = true)),
            @jakarta.persistence.AttributeOverride(name = "email", column = @Column(nullable = true)),
            @jakarta.persistence.AttributeOverride(name = "phone", column = @Column(nullable = true))
    })
    private StoreContact contact;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        status = StoreStatus.PENDING;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
