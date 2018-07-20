package test.com.yodlee.atm;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class AtmSimulatorTest to test various methods for AtmSimulator.java
 * This class will use reflection to test private methods.
 * 
 * @author Rameshwar Singh
 */
public class AtmSimulatorTest {
	Method[] methods;
	Field[] fields;

	@Before
	public void startUp() throws SecurityException, ClassNotFoundException {
		methods = Class.forName("com.yodlee.atm.AtmSimulator").getDeclaredMethods();
		fields = Class.forName("com.yodlee.atm.AtmSimulator").getFields();
	}

	@Test
	public void testMethods() {
		System.out.println("Printing all the methods :");
		for (Method method : methods) {
			if (Modifier.isPrivate(method.getModifiers())) {
				method.setAccessible(true);
				System.out.println(method);
			}
		}
	}

	@Test
	public void testFields() {
		System.out.println("Printing all the fields :");
		for (Field field : fields) {
			if (Modifier.isPrivate(field.getModifiers())) {
				field.setAccessible(true);
				System.out.println(field.getName());
			}
		}
	}
}
