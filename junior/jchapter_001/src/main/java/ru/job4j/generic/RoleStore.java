package ru.job4j.generic;

abstract class RoleStore<T extends Role> extends AbstractStore{

    public RoleStore(int size) {
        super(size);
    }
}
