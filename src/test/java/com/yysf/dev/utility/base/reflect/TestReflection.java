package com.yysf.dev.utility.base.reflect;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.junit.Test;

import com.yyfs.dev.utility.base.reflect.Reflection;

public class TestReflection {

	@Test
	public void testGetAllFieldByType() {


		List<Field> fields = Reflection.getAllFields(ConcurrentLinkedDeque.class);
		for (Field field : fields) {
			System.out.println(field.getType().getName()+" "+ field.getName());
		}
	}
}
