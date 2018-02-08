package com.yyfs.dev.utility.base.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.yyfs.dev.utility.base.function.filter.Filter;
import com.yyfs.dev.utility.base.validate.Assertion;

public final class Reflection {

	/**
	 *
	 * @param clazz
	 * @param type
	 * @return
	 */
	public static List<Field> getAllFields(Class<?> clazz) {

		Assertion.assertNotNull("clazz", clazz);

		return getAllFields(clazz, field -> Boolean.TRUE.booleanValue());
	}

	/**
	 *
	 * @param clazz
	 * @param type
	 * @return
	 */
	public static List<Field> getAllFields(Class<?> clazz, Filter<Field> filter) {

		Assertion.assertNotNull("clazz", clazz);
		Assertion.assertNotNull("filter", filter);
		List<Class<?>> classList = new ArrayList<>();
		classList.add(clazz);
		classList.addAll(getAllSupperClassAndInterfaces(clazz));

		return classList.stream().flatMap(c -> getDeclaredFields(c, filter).stream()).collect(Collectors.toList());

	}

	public static List<Field> getDeclaredFields(Class<?> clazz) {

		Assertion.assertNotNull("clazz", clazz);

		return getDeclaredFields(clazz, field -> Boolean.TRUE.booleanValue());
	}

	public static List<Field> getDeclaredFields(Class<?> clazz, Filter<Field> filter) {

		Assertion.assertNotNull("clazz", clazz);
		Assertion.assertNotNull("filter", filter);

		Field[] fields = clazz.getDeclaredFields();

		return Arrays.stream(fields).filter(field -> filter.accept(field)).collect(Collectors.toList());

	}

	private static List<Class<?>> getAllSupperClassAndInterfaces(Class<?> clazz) {

		Assertion.assertNotNull("clazz", clazz);

		List<Class<?>> resultList = new ArrayList<>();
		List<Class<?>> tempList = getSupperClassAndInterfaces(clazz);

		while (!tempList.isEmpty()) {
			tempList.stream().filter(c -> !resultList.contains(c)).forEach(c -> resultList.add(c));

			tempList = tempList.stream().flatMap(c -> getSupperClassAndInterfaces(c).stream()).collect(Collectors.toList());
		}

		return resultList;

	}

	private static List<Class<?>> getSupperClassAndInterfaces(Class<?> clazz) {

		Assertion.assertNotNull("clazz", clazz);

		List<Class<?>> classList = new ArrayList<>();
		Class<?> supperClass = clazz.getSuperclass();
		if (supperClass != null) {
			classList.add(supperClass);
		}

		Class<?>[] interfaces = clazz.getInterfaces();
		if (interfaces.length > 0) {
			classList.addAll(Arrays.asList(interfaces));
		}

		return classList;
	}

}
