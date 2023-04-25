package com.todolist.backend_todolist.service.implementation;

import com.todolist.backend_todolist.entity.DbSequence;
import com.todolist.backend_todolist.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService
{
    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public int getSequenceNumber(String sequenceName)
    {
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq", 1);
        DbSequence counter = mongoOperation.findAndModify(
                query,
                update,
                options().returnNew(true).upsert(true),
                DbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    @Override
    public void setStartSequenceNumber(String sequenceName)
    {
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().set("seq", 0);
        DbSequence counter = mongoOperation.findAndModify(
                query,
                update,
                options().returnNew(true).upsert(true),
                DbSequence.class);
    }
}
