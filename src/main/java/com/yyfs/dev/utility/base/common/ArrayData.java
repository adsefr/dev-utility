package com.yyfs.dev.utility.base.common;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.yyfs.dev.utility.base.common.ArrayData;
import com.yyfs.dev.utility.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/16
 * @param <T>
 */
public class ArrayData<T> {

	private static int DEFAULT_ARRAY_LENGTH = 100;

	private final ArrayList<T[]> storage = new ArrayList<>();

	private int nextIndex = 0;

	private T[] data;

	private Class<T> clazz;

	public ArrayData(Class<T> clazz) {

		Assertion.assertNotNull("clazz", clazz);

		this.clazz = clazz;
	}

	public ArrayData<T> append(T... array) {

		int inputLength = array.length;

		array = getArray();

		return this;
	}

	@SuppressWarnings("unchecked")
	private T[] getArray() {

		T[] array = null;
		if (storage.isEmpty()) {
			array = (T[]) Array.newInstance(clazz, DEFAULT_ARRAY_LENGTH);
			storage.add(array);
		} else {
			array = storage.get(storage.size() - 1);
		}

		return array;
	}

	private T[] getArray(int index) {

		while (index <= storage.size() - 1) {
			return storage.get(index);
		}

		return null;
	}
}
