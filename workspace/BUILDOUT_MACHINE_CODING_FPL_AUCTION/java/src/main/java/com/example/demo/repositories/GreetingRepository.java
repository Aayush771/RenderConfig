package com.example.demo.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.example.demo.entities.Greeting;

public class GreetingRepository implements IGreetingRepository {

    private final Map<Long,Greeting> greetingMap = new HashMap<>();;
    private AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Greeting save(Greeting entity) {
        Greeting g = new Greeting(idCounter.getAndIncrement(), entity);
        greetingMap.putIfAbsent(g.getId(),g);
        return g;
    }

    @Override
    public boolean existsById(Long id) {
        return greetingMap.containsKey(id);
    }

    @Override
    public Optional<Greeting> findById(Long id) {
        return Optional.ofNullable(greetingMap.get(id));
    }

    @Override
    public List<Greeting> findAll() {
        return greetingMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        greetingMap.remove(id);
    }

    @Override
    public long count() {
        return greetingMap.size();
    }
    
}
