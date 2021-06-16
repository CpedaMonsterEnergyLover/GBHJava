package ru.vovac.wrappers;

import java.util.List;

public interface ArrayWrapper <T>{
    public T get(int index);
    public boolean isEmpty ();
    public T getRandom();
    public boolean contains(T s);
    public List<T> unwrap();
}
