package se.ppsystems.plinko.service;

import org.springframework.stereotype.Service;
import se.ppsystems.plinko.dto.ContactDTO;
import se.ppsystems.plinko.model.ContactEntity;
import se.ppsystems.plinko.repository.ContactRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImp implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImp(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactDTO> getAllContacts() {
        return contactRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ContactDTO> getContactById(Long id) {
        return contactRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public ContactDTO saveContact(ContactDTO contactDTO) {
        ContactEntity contactEntity = convertToEntity(contactDTO);
        ContactEntity savedContactEntity = contactRepository.save(contactEntity);
        return convertToDTO(savedContactEntity);
    }

    @Override
    public ContactDTO updateContact(Long id, ContactDTO contactDTO) {
        ContactEntity contactEntity = contactRepository.findById(id).orElseThrow();
        contactEntity.setFirstName(contactDTO.firstName());
        contactEntity.setLastName(contactDTO.lastName());
        if (contactDTO.email().isPresent()) {
            contactEntity.setEmail(String.valueOf(contactDTO.email()));
        }
        if (contactDTO.mobilePhone().isPresent()) {
            contactEntity.setMobilePhone(String.valueOf(contactDTO.mobilePhone()));
        }
        if (contactDTO.companySection().isPresent()) {
            contactEntity.setCompanySection(String.valueOf(contactDTO.companySection()));
        }
        if (contactDTO.comment().isPresent()) {
            contactEntity.setComment(String.valueOf(contactDTO.comment()));
        }
        if (contactDTO.companyId().isPresent()) {
            contactEntity.setCompanyId(String.valueOf(contactDTO.companyId()));
        }
        ContactEntity updatedContactEntity = contactRepository.save(contactEntity);
        return convertToDTO(updatedContactEntity);
    }

    @Override
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }

    private ContactDTO convertToDTO(ContactEntity contactEntity) {
        return new ContactDTO(
                contactEntity.getId(),
                contactEntity.getFirstName(),
                contactEntity.getLastName(),
                Optional.ofNullable(contactEntity.getEmail()),
                Optional.ofNullable(contactEntity.getMobilePhone()),
                Optional.ofNullable(contactEntity.getCompanySection()),
                Optional.ofNullable(contactEntity.getComment()),
                Optional.ofNullable(contactEntity.getCompanyId())
        );
    }

    private ContactEntity convertToEntity(ContactDTO contactDTO) {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setId(contactDTO.id());
        contactEntity.setFirstName(contactDTO.firstName());
        contactEntity.setLastName(contactDTO.lastName());
        if (contactDTO.email().isPresent()) {
            contactEntity.setEmail(String.valueOf(contactDTO.email()));
        }
        if (contactDTO.mobilePhone().isPresent()) {
            contactEntity.setMobilePhone(String.valueOf(contactDTO.mobilePhone()));
        }
        if (contactDTO.companySection().isPresent()) {
            contactEntity.setCompanySection(String.valueOf(contactDTO.companySection()));
        }
        if (contactDTO.comment().isPresent()) {
            contactEntity.setComment(String.valueOf(contactDTO.comment()));
        }
        if (contactDTO.companyId().isPresent()) {
            contactEntity.setCompanyId(String.valueOf(contactDTO.companyId()));
        }
        return contactEntity;
    }
}
