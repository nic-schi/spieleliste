package data.dao;

/**
 *
 * Implementierung des DAO-Patterns.
 *
 * @author Nichlas
 * @author Jan-Tilo
 * @author Steeve
 */
public interface DAO<E> {

    // Speichert das mitgegebene Objekt
    public void write(E e);

    // Liest das mitgegebene Objekt ein
    public E read(E e);

}
