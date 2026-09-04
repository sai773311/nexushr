package com.nexushr.auth.asset.controller;

import com.nexushr.auth.asset.model.Asset;
import com.nexushr.auth.asset.service.AssetService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService service;
    public AssetController(AssetService service) { this.service = service; }
    @PostMapping @PreAuthorize("hasAnyRole('HR', 'ADMIN')") public Asset create(@RequestBody Asset asset) { return service.create(asset); }
    @GetMapping @PreAuthorize("hasAnyRole('HR', 'ADMIN')") public List<Asset> all() { return service.all(); }
    @GetMapping("/mine") @PreAuthorize("hasRole('EMPLOYEE')") public List<Asset> mine(Authentication authentication) { return service.mine(authentication.getName()); }
    @PutMapping("/{id}/assign") @PreAuthorize("hasAnyRole('HR', 'ADMIN')") public Asset assign(@PathVariable Long id, @RequestParam String email) { return service.assign(id, email); }
    @PutMapping("/{id}/return") @PreAuthorize("hasAnyRole('HR', 'ADMIN')") public Asset returnAsset(@PathVariable Long id, @RequestParam(defaultValue = "") String notes) { return service.returnAsset(id, notes); }
}