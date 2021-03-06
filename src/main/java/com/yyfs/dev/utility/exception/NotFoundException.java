package com.yyfs.dev.utility.exception;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/04
 */
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -585734532684629635L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}
}
