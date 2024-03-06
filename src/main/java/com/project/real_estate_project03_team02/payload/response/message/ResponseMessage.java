package com.project.real_estate_project03_team02.payload.response.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * A generic response message class for representing responses from the server.
 * This class encapsulates the response object, a message, and the HTTP status.
 * @param <T> The type of the response object.
 */
@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage<T> {

	/**
	 * The response object.
	 */
	private T object;

	/**
	 * The message associated with the response.
	 */
	private String message;

	/**
	 * The HTTP status of the response.
	 */
	private HttpStatus httpStatus;
}
