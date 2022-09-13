package com.example.chat.dao;

import com.example.chat.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends CrudRepository<Message, Long> {
    @Query(value = "select * from (select * from message order by date_time desc limit 50) order by date_time", nativeQuery = true)
    List<Message> findLast50();
}
