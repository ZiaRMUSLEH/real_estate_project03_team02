package com.project.real_estate_project03_team02.payload.messages;

public abstract class ErrorMessages {



	//Images
	public static final String NOT_DELETED_IMAGES = "Error: Images not deleted";

	//Logs
	public static final String NOT_DELETED_LOGS = "Error: Logs not deleted";

	//CategoryPropertyKey
	public static final String NOT_DELETED_CATEGORY_PROPERTY_KEYS = "Error: Category Property Keys not deleted";

	//CategoryPropertyValue
	public static final String NOT_DELETED_CATEGORY_PROPERTY_VALUES = "Error: Category Property Values not deleted";

	//Role
	public static final String ROLE_NOT_FOUND = "There is no role like that, check the database";
	public static final String ROLE_ALREADY_EXIST = "Role already exist in DB";
	public static final String NOT_DELETED_ROLES = "Error: Roles not deleted";


	//User
	public static final String ALREADY_REGISTER_MESSAGE_EMAIL = "Error: User with email %s is already registered";
	public static final String NOT_FOUND_USER_MESSAGE = "Error: User not found with id %s";
	public static final String NOT_FOUND_USER_MESSAGE_BY_EMAIL = "Error: User not found with email %s";
	public static final String INVALID_RESET_CODE ="Invalid reset code";
	public static final String USER_IS_BUILT_IN = "The user is Build in and cannot be updated";

	public static final String USER_NOT_FOUND = "User not found";
	public static final String USER_CANNOT_BE_DELETED = "User cannot be deleted";
	public static final String WRONG_PASSWORD = "Incorrect password";
	public static final String NOT_DELETED_USERS = "Error: Users not deleted";
	public static final String NO_AUTHORITY = "Do not have enough permissions to access this";


	//Advert
	public static final String NOT_FOUND_ADVERT_MESSAGE = "Error: Advert not found with id %s";
	public static final String NOT_DELETED_ADVERTS = "Error: Adverts not deleted";
	public static final String ADVERT_IS_BUILT_IN = "Advert with id %d is Built In and cannot be modified or deleted" ;


	//Tour Request
	public static final String NO_TOUR_REQUEST_SAVED = "Error: No tour request is currently saved.";
	public static final String NOT_FOUND_TOUR_REQUEST = "Error: Tour Request not found with id %s";
	public static final String NOT_UPDATABLE_TOUR_REQUEST = "Error: Tour Request is not updatable with id %s";
	public static final String INVALID_TOUR_REQUEST_DATE = "Error: Cannot schedule tours for past dates.";
	public static final String INVALID_TOUR_REQUEST_ID = "Error: Cannot cancel Tour Request with id %s.";
	public static final String NOT_DELETED_TOUR_REQUESTS = "Error: Tour Requests not deleted";


	//Favorite
	public static final String NOT_FOUND_FAVORITE_MESSAGE = "Error: Favorite not found with id %s";
	public static final String NOT_DELETED_FAVORITES = "Error: Favorites not deleted";





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
	public static final String Advert_Type_NOT_FOUND_MESSAGE = "Error: Advert Type with id %s not found" ;
	public static final String NOT_DELETED_ADVERT_TYPES = "Error: Advert Types not deleted";

	public static final String ALREADY_TITLE = "Error: Advert Type with title %s is already";

	//Reports
	public static final String REPORT_WRONG_FORMAT_MESSAGE = "Error: Wrong Format" ;
	public static final String REPORT_WRONG_AMOUNT = "Error: Wrong Amount" ;


	//Category
    public static final String NO_CATEGORY_WITH_ID = "Category with id %d not found";
	public static final String CATEGORY_MAX_LENGTH_TITLE = "Maximum 150 Characters";
	public static final String CATEGORY_MAX_LENGTH_ICON = "Maximum 50 Characters";
	public static final String CATEGORY_IS_BUILT_IN = "Category with id %d is Built In and cannot be modified or deleted" ;
	public static final String CATEGORY_WITH_ID_HAVE_ADVERT ="Category with id %d have records in Advert and cannot be deleted";
	public static final String NO_CATEGORY_PROPERTY_KEY_WITH_ID = "Category property key with id %d not found";
	public static final String CATEGORY_PROPERTY_KEY_IS_BUILT_IN = "Category property key with id %d is Built In and cannot be modified or deleted" ;

	public static final String NOT_DELETED_CATEGORIES = "Error: Categories not deleted";

	//Slug Generator
	public static final String ERROR_ENCODING_SLUG = "Error encoding slug";

	//Country
	public static final String NO_COUNTRY_WITH_ID = "Country with id %S not found";
	public static final String NOT_DELETED_COUNTRIES = "Error: Countries not deleted";
	//City
	public static final String NO_CITY_WITH_ID = "City with id %S not found";
	public static final String NOT_DELETED_CITIES = "Error: Cities not deleted";
	//District
	public static final String NO_DISTRICT_WITH_ID = "District with id %S not found";
	public static final String NOT_DELETED_DISTRICTS = "Error: Districts not deleted";

	//ContactMessages
	public static final String NOT_DELETED_CONTACT_MESSAGES = "Error: Contact Messages not deleted";

	public static final String ADVERT_TYPE_NOT_VALID ="Error: Invalid advert type.";
}
