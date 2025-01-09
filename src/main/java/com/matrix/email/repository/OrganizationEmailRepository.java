package com.matrix.email.repository;

import com.matrix.email.entities.OrganizationEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationEmailRepository extends JpaRepository<OrganizationEmail, Long> {
    
    // You can define custom query methods if needed.
    // Example: Find by organization name
    OrganizationEmail findByOrganizationName(String organizationName);
    
    // Example: Find by email
    OrganizationEmail findByEmail(String email);
}
