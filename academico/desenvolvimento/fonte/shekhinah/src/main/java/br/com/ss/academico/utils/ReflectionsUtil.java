package br.com.ss.academico.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import br.com.ss.academico.dominio.Curso;

public class ReflectionsUtil {

	public static Field[] findAnnotatedFields(Class<?> clazz, Class<? extends Annotation> annotationClass) {
	    Field[] declaredFields = clazz.getDeclaredFields();
	    List<Field> annotatedFields = new ArrayList<Field>(declaredFields.length);
	    for (Field field : declaredFields) {
	      if( field.isAnnotationPresent(annotationClass)){
	        annotatedFields.add(field);
	      }
	    }
	    return annotatedFields.toArray(new Field[annotatedFields.size()]);    
	  }
	  
	  public static Annotation[] findFieldAnnotations(Class<?> clazz,String fieldName) throws NoSuchFieldException {
	    Field field = clazz.getDeclaredField(fieldName);
	    return field.getAnnotations();
	  }
	  
	  public static <T extends Annotation> T findFieldAnnotation(Class<?> clazz, String fieldName, Class<T> annotationClass) throws NoSuchFieldException {
	    Field field = clazz.getDeclaredField(fieldName);
	    return field.getAnnotation(annotationClass);
	  }
	  
	  
	  public static Object callConstructor(Class<?> clazz ) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		  return clazz.getConstructor().newInstance();
	  }
	  
	  public static void main(String[] args) {
		  
		  try {
			  
			  Object obj = callConstructor(Curso.class );
			  System.out.println(obj);
			  
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  
}
