public interface Dictionary<Key, T> {
    public void clear();

    /**
     * Insert a record
     * @param k the key for the record being inserted
     * @param t the record being inserted
     */
    public void insert(Key k, T t);
}
