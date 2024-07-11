package com.j0k3r.andreanamaste.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "profile_items")
public class ProfileItem {

    @Id
    @UuidGenerator
    private String id;

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String body;

}