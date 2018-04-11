package com.el.concurrent;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockProvider {

    private final Map<Mutex, WeakReference<Mutex>> mutexMap = new WeakHashMap<>();

    public ReentrantReadWriteLock getLock(Object object, Object... objects) {
        StringBuilder lockKey = new StringBuilder(String.valueOf(object));
        for (Object o : objects) {
            lockKey.append(",").append(o);
        }
        return getMutex(lockKey.toString()).getLock();
    }

    /**
     * Get a mutex object for the given (non-null) id.
     */
    public Mutex getMutex(String id) {
        Objects.requireNonNull(id);

        Mutex key = new Mutex(id);
        synchronized (mutexMap) {
            WeakReference ref = (WeakReference) mutexMap.get(key);
            if (ref == null) {
                mutexMap.put(key, new WeakReference<>(key));
                return key;
            }

            Mutex mutex = (Mutex) ref.get();
            if (mutex == null) {
                mutexMap.put(key, new WeakReference<>(key));
                return key;
            }
            return mutex;
        }
    }

    /**
     * Get the number of mutex objects being held
     */
    public int getMutexCount() {
        synchronized (mutexMap) {
            return mutexMap.size();
        }
    }

    private static class Mutex {
        private final String id;
        private final ReentrantReadWriteLock lock;

        protected Mutex(String id) {
            this.id = Objects.requireNonNull(id);
            this.lock = new ReentrantReadWriteLock();
        }

        public ReentrantReadWriteLock getLock() {
            return lock;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Mutex)) return false;
            Mutex mutex = (Mutex) o;
            return id.equals(mutex.id);
        }

        public int hashCode() {
            return id.hashCode();
        }

        public String toString() {
            return id;
        }
    }
}
