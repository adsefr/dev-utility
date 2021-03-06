package com.yyfs.dev.utility.exception;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/03
 */
public class InvalidException extends RuntimeException {

	private static final long serialVersionUID = 82933669978800111L;

	public InvalidException() {
		super();
	}

	public InvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidException(String message) {
		super(message);
	}

	public InvalidException(Throwable cause) {
		super(cause);
	}
}
