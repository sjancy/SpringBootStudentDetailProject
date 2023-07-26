package com.jancy.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jancy.springboot.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	@Query("select s from Student s where name = ?1")
	Student findByName(String name);
	
	@Query("select s from Student s where id=?1")
	Student findByIdAll(Long id);
	
	//@Query("select * from student where student_name = ?1", nativeQuery = true)
	//Student findByNameNative(String name);

}
