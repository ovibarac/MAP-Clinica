package com.example.clinica.repo;

import com.example.clinica.domain.Entity;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDbRepo<Id, E extends Entity<Id>> implements Repository<Id, E>{
    private String url;
    private String username;
    private String password;
    private String tableName;

    public AbstractDbRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    abstract public Optional<E> findOne(Id id);

    @Override
    public abstract List<E> findAll();

    @Override
    public abstract Optional<E> save(E entity);

    @Override
    public abstract Optional<E> delete(Id id);

    @Override
    public abstract Optional<E> update(E entity);
}
