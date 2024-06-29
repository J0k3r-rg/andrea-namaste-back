package com.j0k3r.andreanamaste.models;

import com.j0k3r.andreanamaste.security.models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    private User user;

    @OneToOne
    private Shift shift;

    private Boolean isConfirmed;

    private Boolean isCancelled;

    private Boolean isAttended;

    private Boolean isPaid;
    
}
