package com.example.SushiStore.Repositories;

import com.example.SushiStore.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Roles,Long> {
       Roles findByRole(String role);
       List<Roles> findAll();
}
