package com.project.real_estate_project03_team02.entity.enums;


/**
 * The RoleType enum represents the different roles that users can have within the real estate project.
 * It defines three types of roles: ADMIN, MANAGER, and CUSTOMER.
 *
 * - ADMIN: Represents an administrative role with full access and privileges to manage the system.
 * - MANAGER: Represents a managerial role with access to certain administrative functionalities and
 *   permissions to manage properties, users, or other aspects of the system.
 * - CUSTOMER: Represents a regular user role with limited access, primarily focused on browsing
 *   properties, making inquiries, and engaging in transactions.
 *
 * This enum is used to assign and manage user roles within the real estate project, enabling the
 * system to control access levels and permissions based on the role assigned to each user.
 */
public enum RoleType {
    ADMIN,
    MANAGER,
    CUSTOMER
}
