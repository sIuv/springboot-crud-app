package se.ppsystems.plinko.service;

import org.springframework.stereotype.Service;
import se.ppsystems.plinko.dto.CompanyDTO;
import se.ppsystems.plinko.model.CompanyEntity;
import se.ppsystems.plinko.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImp implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImp(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CompanyDTO> getCompanyById(Long id) {
        return companyRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        CompanyEntity company = convertToEntity(companyDTO);
        CompanyEntity savedCompany = companyRepository.save(company);
        return convertToDTO(savedCompany);
    }

    @Override
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) {
        CompanyEntity companyEntity = companyRepository.findById(id).orElseThrow();
        companyEntity.setName(companyDTO.name());
        CompanyEntity updatedCompany = companyRepository.save(companyEntity);
        return convertToDTO(updatedCompany);
    }

    @Override
    public void deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
    }

    private CompanyDTO convertToDTO(CompanyEntity companyEntity) {
        return new CompanyDTO(companyEntity.getId(), companyEntity.getName());
    }

    private CompanyEntity convertToEntity(CompanyDTO companyDTO) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(companyDTO.id());
        companyEntity.setName(companyDTO.name());
        return companyEntity;
    }
}
