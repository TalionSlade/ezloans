package com.wellsfargo.training.ezloans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wellsfargo.training.ezloans.model.EmployeeCard;
import com.wellsfargo.training.ezloans.model.LoanCard;

public interface EmployeeCardRepository extends JpaRepository<EmployeeCard, Long>{
	
	 /*
     * This interface has save(),findAll(),findById(),deleteById(),count()
       etc.. inbuilt methods of jpa repository for various database operations.
       This interface will be implemented by class automatically
    */
	
	/*
	 * In order to define SQL to execute for a Spring Data repository method, we can annotate the method with the @Query annotation — its value attribute contains the JPQL or SQL to execute.

The @Query annotation takes precedence over named queries, which are annotated with @NamedQuery or defined in an orm.xml file.
	 */
	@Query(value = "SELECT * FROM EMPLOYEE_CARD WHERE EID = ?1 ", nativeQuery = true)
	public List<EmployeeCard>getEmployeeCardsOfEmployee(Long id);

}
