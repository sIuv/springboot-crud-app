package se.ppsystems.plinko.service;

import se.ppsystems.plinko.dto.ContactDTO;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    List<ContactDTO> getAllContacts();
    Optional<ContactDTO> getContactById(Long id);
    ContactDTO saveContact(ContactDTO contactDTO);
    ContactDTO updateContact(Long id, ContactDTO contactDTO);
    void deleteContactById(Long id);
}


