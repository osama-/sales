package com.maids.sales.dao;

import com.maids.sales.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientJPARepository extends JpaRepository<Client,Integer> {
}
