package com.j0k3r.andreanamaste.repositories;

import com.j0k3r.andreanamaste.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, String> {
}
