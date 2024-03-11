/**
 * Controller class for managing settings related to the real estate project.
 * This class handles HTTP requests related to settings.
 */
package com.project.real_estate_project03_team02.controller;

import com.project.real_estate_project03_team02.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling HTTP requests related to managing settings
 * within the real estate project. This includes functionality such as resetting the
 * database.
 */
@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;

    /**
     * Endpoint for resetting the database.
     * This endpoint triggers a reset of the entire database.
     * Access to this endpoint is restricted to users with 'ADMIN' authority.
     */
    @DeleteMapping("/db-reset")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void resetDatabase() {
        settingService.resetDatabase();
    }
}
