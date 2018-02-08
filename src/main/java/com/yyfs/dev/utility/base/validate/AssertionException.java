package com.yyfs.dev.utility.base.validate;

import com.yyfs.dev.utility.exception.InvalidException;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/03
 */
public class AssertionException extends InvalidException {

	private static final long serialVersionUID = 82933669978800111L;

	public AssertionException(String message, Throwable cause) {

		super(message, cause);
	}

	public AssertionException(String message) {

		super(message);
	}

	public AssertionException(Throwable cause) {

		super(cause);
	}

}
