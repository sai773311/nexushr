package com.nexushr.auth.asset.service;

import com.nexushr.auth.asset.model.Asset;
import com.nexushr.auth.asset.repository.AssetRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AssetService {
    private final AssetRepository repository;
    public AssetService(AssetRepository repository) { this.repository = repository; }
    public Asset create(Asset asset) { return repository.save(asset); }
    public List<Asset> all() { return repository.findAll(); }
    public List<Asset> mine(String email) { return repository.findByAssignedEmployeeEmail(email); }
    public Asset assign(Long id, String email) { Asset asset = get(id); if (!"AVAILABLE".equals(asset.getStatus())) throw new RuntimeException("Asset is not available"); asset.setAssignedEmployeeEmail(email); asset.setAssignedDate(LocalDate.now()); asset.setStatus("ASSIGNED"); return repository.save(asset); }
    public Asset returnAsset(Long id, String notes) { Asset asset = get(id); asset.setAssignedEmployeeEmail(null); asset.setReturnedDate(LocalDate.now()); asset.setConditionNotes(notes); asset.setStatus("AVAILABLE"); return repository.save(asset); }
    private Asset get(Long id) { return repository.findById(id).orElseThrow(() -> new RuntimeException("Asset not found")); }
}