package com.example.data.webservices;

/**
 * Created by e-Conception on 21/04/2017.
 */

public class MyWSHelper {

    /** Variable for request **/
    private int CONNECT_TIMEOUT = 20;
    private int READ_TIMEOUT = 30;
    private int WRITE_TIMEOUT = 20;
    private int CONNECT_UPLOAD_TIMEOUT = 60; //min
    private int READ_UPLOAD_TIMEOUT = 60; //min
    private int WRITE_UPLOAD_TIMEOUT = 60; //min

    /** Variable for response **/
    public static final int RESPONSE_OK = 200;
    public static final int RESPONSE_PARTIAL_OK = 206;
    public static final int RESPONSE_MAINTENANCE = 503;
    public static final int RESPONSE_EXPIRED_UNAUTHORIZED = 401;
    public static final int RESPONSE_FORBIDDEN = 403;
    public static final int RESPONSE_ERROR = 400;
    public static final int RESPONSE_NOT_FOUND = 404;
    public static final int RESPONSE_TIMEOUT = 1000;
    public static final int RESPONSE_GETWAY_TIMEOUT = 504;

    /** Variable for server **/
    private final String COOKIE_KEY = "PHPSESSID";



}
