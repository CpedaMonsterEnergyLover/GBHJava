package ru.vovac.wrappers;

import java.util.HashMap;

public interface HashMapWrapper <T, T2> {
    public T2 get(T index);
    public boolean isEmpty ();
    public boolean containsKey(T s);
    public boolean containsValue(T s);
    public HashMap<T, T2> unwrap();
}
