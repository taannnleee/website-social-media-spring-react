package org.example.socialmedia.authentication.repositories;

import org.example.socialmedia.common.entities.Address;
import org.example.socialmedia.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findAddressByUser(User user);
}
