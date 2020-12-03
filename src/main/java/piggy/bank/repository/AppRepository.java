package piggy.bank.repository;

import piggy.bank.entity.Application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

abstract public class AppRepository<E>  {
    protected List<E> collection;

    public AppRepository() {

        collection = new ArrayList<>();
    }

    public List<E> getAll() {
        return collection;
    }

    abstract public E getById(Long id);

    public void update(E element) {
        this.collection.add(element);
    }

    public void insert(E element) {
        this.collection.add(element);
    }

    public int size(){
        return collection.size();
    }
}
