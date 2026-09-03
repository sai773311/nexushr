package com.nexushr.auth.asset.repository;

import com.nexushr.auth.asset.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByAssignedEmployeeEmail(String email);
}