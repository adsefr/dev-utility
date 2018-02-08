package com.yyfs.dev.utility.base.logging;

import org.slf4j.LoggerFactory;

public class Logger {

	private org.slf4j.Logger logger;

	private Logger(org.slf4j.Logger logger) {

		this.logger = logger;
	}

	public static Logger getLogger(String name) {

		return new Logger(LoggerFactory.getLogger(name));
	}

	public static Logger getLogger(Class<?> clazz) {

		return new Logger(LoggerFactory.getLogger(clazz));
	}

	public void error(String message, Object... parameters) {

		logger.error(message, parameters);
	}

	public void warn(String message, Object... parameters) {

		logger.warn(message, parameters);
	}

	public void info(String message, Object... parameters) {

		logger.info(message, parameters);
	}

	public void debug(String message, Object... parameters) {

		logger.debug(message, parameters);
	}

	public void trace(String message, Object... parameters) {

		logger.trace(message, parameters);
	}

	public void error(Throwable throwable) {

		logger.error("", throwable);
	}

	public void warn(Throwable throwable) {

		logger.warn("", throwable);
	}

	public void info(Throwable throwable) {

		logger.info("", throwable);
	}

	public void debug(Throwable throwable) {

		logger.debug("", throwable);
	}

	public void trace(Throwable throwable) {

		logger.trace("", throwable);
	}

	public void error(String message, Throwable throwable) {

		logger.error(message, throwable);
	}

	public void warn(String message, Throwable throwable) {

		logger.warn(message, throwable);
	}

	public void info(String message, Throwable throwable) {

		logger.info(message, throwable);
	}

	public void debug(String message, Throwable throwable) {

		logger.debug(message, throwable);
	}

	public void trace(String message, Throwable throwable) {

		logger.trace(message, throwable);
	}

}
