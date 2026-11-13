package com.example.taskapi.service;
import com.example.taskapi.model.Task;
import java.util.*; import java.util.concurrent.*; import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;
@Service
public class TaskService {
    private final Map<Long, Task> store = new ConcurrentHashMap<>(); private final AtomicLong idSeq = new AtomicLong(0);
    public List<Task> findAll() { return store.values().stream().sorted(Comparator.comparing(Task::getId)).toList(); }
    public Task create(String title) { long id = idSeq.incrementAndGet(); Task t = new Task(id, title); store.put(id, t); return t; }
    public Optional<Task> findById(Long id) { return Optional.ofNullable(store.get(id)); }
}
