package com.project.real_estate_project03_team02.payload.messages;

public abstract class SuccessMessages {

	//Entity's
	public static final String INVALID_SLUG_LENGTH="Invalid slug length";
	//REGEX
	public static final String PASS_REGEX = "\\A(?!\\s*\\Z).+";

	//LogActivity
	public static final String CREATED = "Advert is created and wait for approve";
	public static final String UPDATED = "Advert is updated";
	public static final String DELETED = "Advert is deleted";
	public static final String DECLINED = "Advert is declined by manager";

	//TourRequest
	public static final String TOUR_REQUEST_CREATED = "Tour request is created";
	public static final String TOUR_REQUEST_ACCEPTED = "Tour request is accepted";
	public static final String TOUR_REQUEST_DECLINED = "Tour request is declined";
	public static final String TOUR_REQUEST_CANCELED = "Tour request is canceled";
	public static final String TOUR_REQUEST_UPDATED = "Tour request is updated";
	public static final String TOUR_REQUEST_DELETED = "Tour request is deleted";

	//serviceHelper

	//services

	//UserService
	public static final String USER_SAVED = "User saved";
	public static final String USER_LOGIN = "User logged in";
	public static final String USER_UPDATED = "User updated successfully";

	//User
	public static final String USER_CREATE = "User is created successfully";


	//AdvertTypes
	public static final String ADVERT_TYPES_CREATED = "Advert types is created";


    public static final String ADVERT_TYPE_UPDATE ="Advert Types is updated successfully";
    public static final String ADVERT_TYPES_DELETED ="Advert Types is deleted successfully" ;
    ;

	//Adverts
	public static final String ADVERT_CREATED = "Advert is created successfully";


}
