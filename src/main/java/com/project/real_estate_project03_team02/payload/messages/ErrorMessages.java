package com.project.real_estate_project03_team02.payload.messages;

public abstract class

ErrorMessages {


	//Role
	public static final String ROLE_NOT_FOUND = "There is no role like that, check the database";
	public static final String ROLE_ALREADY_EXIST = "Role already exist in DB";

	//User
	public static final String ALREADY_REGISTER_MESSAGE_EMAIL = "Error: User with email %s is already registered";
	public static final String NOT_FOUND_USER_MESSAGE = "Error: User not found with id %s";
	public static final String NOT_FOUND_USER_MESSAGE_BY_EMAIL = "Error: User not found with email %s";



	//Advert
	public static final String NOT_FOUND_ADVERT_MESSAGE = "Error: Advert not found with id %s";

	//Tour Request
	public static final String NO_TOUR_REQUEST_SAVED = "Error: No tour request is currently saved.";
	public static final String NOT_FOUND_TOUR_REQUEST = "Error: Tour Request not found with id %s";
	public static final String NOT_UPDATABLE_TOUR_REQUEST = "Error: Tour Request is not updatable with id %s";
	public static final String INVALID_TOUR_REQUEST_DATE = "Error: Cannot schedule tours for past dates.";
	public static final String INVALID_TOUR_REQUEST_ID = "Error: Cannot cancel Tour Request with id %s.";




	//Request
	public static final String EMAIL_NOT_EMPTY ="please enter your email";
	public static final String PASS_NOT_EMPTY = "please enter your password";
	public static final String ENTER_FIRST_NAME = "please enter your first name";
	public static final String MIN_LENGTH_FIRST_NAME = "Your first name should be at least 2 characters";
	public static final String MAX_LENGTH_FIRST_NAME = "Your first name should be at most 30 characters";
	public static final String ENTER_LAST_NAME = "please enter your last name";
	public static final String MIN_LENGTH_LAST_NAME = "Your last name should be at least 2 characters";
	public static final String MAX_LENGTH_LAST_NAME = "Your last name should be at most 30 characters";
	public static final String MIN_LENGTH_EMAIL = "Your email should be at least 10 characters";
	public static final String MAX_LENGTH_EMAIL = "Your email should be at most 80 characters";
	public static final String INVALID_EMAIL_FORMAT =  "Invalid email format";
	public static final String ENTER_PHONE = "please enter your phone number";
	public static final String MIN_LENGTH_PASS = "Your password should be at least 8 characters";
	public static final String MAX_LENGTH_PASS = "Your password should be max 60 characters";
	public static final String INVALID_PASS_FORMAT = "Your password must consist of the characters";

	//Advert Type
	public static final String Advert_Type_NOT_FOUND_MESSAGE = "Error: Advert Type with id %d not found" ;

	public static final String ALREADY_TITLE = "Error: Advert Type with title %s is already";

	//public static final String REPORT_WRONG_DATE_FORMAT_MESSAGE = "Error: Wrong Date Format" ;



}
