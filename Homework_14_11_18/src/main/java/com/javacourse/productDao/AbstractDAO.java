package com.javacourse.productDao;

import java.util.List;

/**
 *Basic class, which is to be implemented by ALL DAO classes
 * @param <K> key type
 * @param <T> model type
 */
public abstract class AbstractDAO<K, T> {
    public abstract List<T> findAll();
    public abstract T findById(K id);
    public abstract boolean delete(K id);
    public abstract boolean create(T entity);
    public abstract T update(T entity);
}
