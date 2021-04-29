package com.daynilgroup.DaynilGroupAssignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.daynilgroup.DaynilGroupAssignment.model.Entries;

@Repository
public interface DaynilGroupRepository extends JpaRepository<Entries, Long>
{
	@Query(value="select distinct a.category from Entries a order by a.category ASC",nativeQuery = true)
	List getEntityByCategory();

	@Query(value="select * from Entries a where a.category=:animals group by a.category",nativeQuery = true)
	Entries getEntityByAnimals(@Param("animals") String animals);

}
