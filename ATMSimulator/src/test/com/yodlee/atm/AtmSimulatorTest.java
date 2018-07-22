package test.com.yodlee.atm;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import com.yodlee.atm.AtmSimulator;

/**
 * Test class AtmSimulatorTest to test various methods for AtmSimulator.java
 * This class will use reflection to test private methods.
 * 
 * @author Rameshwar Singh
 */
public class AtmSimulatorTest {
	Method[] methods;
	Field[] fields;
	Object atmSimulator;
	Stack<Integer> availableCurrecy;

	@SuppressWarnings("unchecked")
	@Before
	public void startUp() throws SecurityException, ClassNotFoundException {
		atmSimulator = Class.forName("com.yodlee.atm.AtmSimulator");
		availableCurrecy = new Stack<Integer>();
		methods = ((Class<AtmSimulator>) atmSimulator).getDeclaredMethods();
		fields = ((Class<AtmSimulator>) atmSimulator).getFields();
	}

	@Test
	public void testMethods() {
		System.out.println("Printing all the methods :");
		for (Method method : methods) {
			if (Modifier.isPrivate(method.getModifiers())) {
				System.out.println(method);
			}
		}
	}

	@Test
	public void testFields() {
		System.out.println("Printing all the fields :");
		for (Field field : fields) {
			if (Modifier.isPrivate(field.getModifiers())) {
				System.out.println(field.getName());
			}
		}
	}

	@Test
	public void testStartMenuOptions() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method startMenuOptions = Class.forName("com.yodlee.atm.AtmSimulator").getDeclaredMethod("startMenuOptions");
		startMenuOptions.setAccessible(true);
		assert (startMenuOptions.invoke(atmSimulator)) != null;
		System.exit(1);
	}

	@Test
	public void testLoadAtmMachine() throws NoSuchMethodException, SecurityException, ClassNotFoundException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method loadAtmMachine = Class.forName("com.yodlee.atm.AtmSimulator").getDeclaredMethod("loadAtmMachine");
		loadAtmMachine.setAccessible(true);
		loadAtmMachine.invoke(atmSimulator, availableCurrecy);
		assert (availableCurrecy) != null;
		assertEquals(availableCurrecy.size(), 3);
	}
}
