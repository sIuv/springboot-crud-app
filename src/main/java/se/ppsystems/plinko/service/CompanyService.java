package se.ppsystems.plinko.service;

import se.ppsystems.plinko.dto.CompanyDTO;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<CompanyDTO> getAllCompanies();
    Optional<CompanyDTO> getCompanyById(Long id);
    CompanyDTO saveCompany(CompanyDTO companyDTO);
    CompanyDTO updateCompany(Long id, CompanyDTO companyDTO);
    void deleteCompanyById(Long id);
}

