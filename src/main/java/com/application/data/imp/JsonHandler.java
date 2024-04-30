/**
 * This class is an implementation of the IJsonHandler<> interface and handles
 * of serialization and deserialization of instances based on a generic.
 * <p>
 * Use the GSon library for this purpose.
 */
package com.application.data.imp;

import com.application.data.out.IJsonHandler;
import com.google.gson.Gson;

public class JsonHandler<E> implements IJsonHandler<E> {

    /**
     * Serializes an object using a GSon instance.
     * @param entity It's the entity to be serialized, it can only be of the generic type
     *               indicated in the instantiation or derivatives.
     * @return Returns a string with the content of the object serialized in JSon.
     */
    @Override
    public String serialize(E entity) {
        Gson gSon = new Gson();
        return gSon.toJson(entity);
    }

    /**
     * Deserializes an object using a GSon instance.
     * @param json Is the JSon string to deserialize.
     * @param entityClass Is the class to which the object to be deserialized belongs,
     *                    can only be of the generic type indicated in the
     *                    instantiation or derivatives.
     * @return Returns an instance of the generic type indicated in the instantiation
     * with the deserialized data.
     */
    @Override
    public E deserialize(String json, Class<? extends E> entityClass) {
        Gson gson = new Gson();
        return gson.fromJson(json, entityClass);
    }
}
