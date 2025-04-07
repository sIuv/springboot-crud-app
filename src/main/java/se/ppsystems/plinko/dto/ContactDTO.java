package se.ppsystems.plinko.dto;


import java.util.Optional;

public record ContactDTO(Long id, String firstName, String lastName, Optional<String> email, Optional<String> mobilePhone, Optional<String> companySection, Optional<String> comment, Optional<String> companyId) {}
