package br.com.ss.academico.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.context.FacesContext;

import br.com.ss.academico.dominio.Curso;

public class ReflectionsUtil {

	public static Field[] findAnnotatedFields(Class<?> clazz, Class<? extends Annotation> annotationClass) {
		Field[] declaredFields = clazz.getDeclaredFields();
		List<Field> annotatedFields = new ArrayList<Field>( declaredFields.length);
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(annotationClass)) {
				annotatedFields.add(field);
			}
		}
		return annotatedFields.toArray(new Field[annotatedFields.size()]);
	}

	public static Annotation[] findFieldAnnotations(Class<?> clazz, String fieldName) throws NoSuchFieldException {
		Field field = clazz.getDeclaredField(fieldName);
		return field.getAnnotations();
	}

	public static <T extends Annotation> T findFieldAnnotation(Class<?> clazz, String fieldName, Class<T> annotationClass)
								throws NoSuchFieldException {
		Field field = clazz.getDeclaredField(fieldName);
		return field.getAnnotation(annotationClass);
	}

	public static Object callConstructor(Class<?> clazz) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		return clazz.getConstructor().newInstance();
	}

	/**
	 * Test
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Object obj = callConstructor(Curso.class);
			System.out.println(obj);
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void invokeBean(String bean, String method) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elContext = facesContext.getELContext();
		ExpressionFactory factory = facesContext.getApplication().getExpressionFactory();

		Object meuBean = factory.createValueExpression(elContext,
				"#{" + bean + "}", Object.class).getValue(elContext);

		try {
			Method mtd = meuBean.getClass().getMethod(method, new Class[] {});
			mtd.invoke(meuBean, new Object[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
