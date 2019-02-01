package org.connectorio.helenus.astyanax.model;

/**
 * Representation on data type which is directly linked with java class.
 * 
 * @author dl02
 *
 * @param <T> Class type.
 */
public interface JavaType<T> extends Type<T> {

    /**
     * Returns java class which is representation on this type.
     * 
     * @return Java class.
     */
    Class<T> getJavaType();

}
