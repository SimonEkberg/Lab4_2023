package service;

import domainModell.person.Person;

import java.util.List;

@FunctionalInterface
public interface CommandService<T> {
    public T execute();
}
