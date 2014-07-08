package com.epam.lab.news.data.repo;

import com.epam.lab.news.data.bean.Counter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends CrudRepository<Counter, String> {
}
