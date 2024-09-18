package org.example.socialmedia.authentication.service;

import org.example.socialmedia.common.entities.Address;
import org.example.socialmedia.common.entities.User;

public interface AddressService {
    Address findAddressByUser(User user);
}
