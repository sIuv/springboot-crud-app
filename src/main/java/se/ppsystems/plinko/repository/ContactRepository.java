package se.ppsystems.plinko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ppsystems.plinko.model.ContactEntity;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
