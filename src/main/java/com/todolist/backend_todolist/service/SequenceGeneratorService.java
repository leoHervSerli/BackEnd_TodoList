package com.todolist.backend_todolist.service;

public interface SequenceGeneratorService
{
    public int getSequenceNumber(String sequenceName);

    public void setStartSequenceNumber(String sequenceName);
}
