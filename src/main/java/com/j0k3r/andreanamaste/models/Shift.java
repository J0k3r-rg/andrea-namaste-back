package com.j0k3r.andreanamaste.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shifts")
public class Shift {

    @Id
    @UuidGenerator
    private String id;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Temporal(TemporalType.TIME)
    private LocalTime hour;

    @ManyToOne
    private Product product;

    @Builder.Default
    private Boolean isBooked = false;

}
