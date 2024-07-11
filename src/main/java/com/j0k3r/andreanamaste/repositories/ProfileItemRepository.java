package com.j0k3r.andreanamaste.repositories;

import com.j0k3r.andreanamaste.models.ProfileItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileItemRepository extends JpaRepository<ProfileItem, String> {

    Optional<ProfileItem> findByName(String name);

    boolean existsByName(String name);
}