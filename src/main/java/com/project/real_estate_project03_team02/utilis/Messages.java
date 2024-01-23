package com.project.real_estate_project03_team02.utilis;

public abstract class Messages {

        //REGEX
        public static final String EMAIL_REGEX = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        public static final String PASS_REGEX = "\\A(?!\\s*\\Z).+";

    //Entity's
    public static final String INVALID_SLUG_LENGTH="Invalid slug length";

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


    //LogActivity
    public static final String CREATED = "Advert is created and wait for approve";
    public static final String UPDATED = "Advert is updated";
    public static final String DELETED = "Advert is deleted";
    public static final String DECLINED = "Advert is declined by manager";
    public static final String TOUR_REQUEST_CREATED = "Tour request is created";
    public static final String TOUR_REQUEST_ACCEPTED = "Tour request is accepted";
    public static final String TOUR_REQUEST_DECLINED = "Tour request is declined";
    public static final String TOUR_REQUEST_CANCELED = "Tour request is canceled";

    //serviceHelper
    public static final String ALREADY_REGISTER_MESSAGE_EMAIL = "Email: %s is already registered";
    //services
        //UserService
    public static final String USERSAVED = "User saved";
    public static final String USERLOGIN = "User logged in";

    //Role
    public static final String ROLE_NOT_FOUND = "This role don't exist, check the database";



}

