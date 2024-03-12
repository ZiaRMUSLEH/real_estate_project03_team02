package com.project.real_estate_project03_team02.entity.enums;

/**
 * Enumeration representing the status of a tour request in a real estate project.
 * <p>
 * The status can be one of the following:
 * <ul>
 *     <li>{@code PENDING}: The tour request is awaiting approval.</li>
 *     <li>{@code APPROVED}: The tour request has been approved by the relevant authorities.</li>
 *     <li>{@code DECLINED}: The tour request has been declined by the relevant authorities.</li>
 *     <li>{@code CANCELED}: The tour request has been canceled, possibly by the requester.</li>
 * </ul>
 * <p>
 * This enum provides a set of predefined statuses that can be assigned to tour requests,
 * allowing for clear communication and tracking of the request's progress.
 */
public enum TourRequestStatus {
    PENDING, APPROVED, DECLINED, CANCELED,
}
