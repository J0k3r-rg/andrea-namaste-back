package com.j0k3r.andreanamaste.models;

import com.j0k3r.andreanamaste.security.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @UuidGenerator
    private String id;

    @OneToOne
    private Shift shift;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder.Default
    private String urlMeet = "";

    @Builder.Default
    private Boolean isPaid = false;
    
}
