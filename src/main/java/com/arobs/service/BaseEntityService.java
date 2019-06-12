package com.arobs.service;

import com.arobs.utils.StaticUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseEntityService<E, R extends JpaRepository<E, Long>> {

    public abstract R getRepository();

    public E getOne(Long id) {
        return getRepository().getOne(id);
    }

    public E findOne(Long id) {
        return getRepository().findOne(id);
    }

    public List<E> findAll(Collection<Long> ids) {
        if (StaticUtil.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return getRepository().findAll(ids);
    }

    public List<E> findAll() {
        return getRepository().findAll();
    }

    @Transactional
    public List<E> save(List<E> items) {
        return getRepository().save(items);
    }

    @Transactional
    public E save(E item) {
        return getRepository().save(item);
    }

    @Transactional
    public void delete(Long id) {
        getRepository().delete(id);
    }

    @Transactional
    public void delete(Collection<E> items) {
        getRepository().delete(items);
    }
}
