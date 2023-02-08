package com.example.clinica.repo;

import com.example.clinica.domain.Sectie;
import java.sql.*;
import java.util.*;

public class SectieRepo extends AbstractDbRepo<Integer, Sectie>{
    public SectieRepo(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public Optional<Sectie> findOne(Integer integer) {
        String sql = "select * from \"Sectii\" where id=?";
        try(Connection connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword())){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, integer);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return Optional.of(new Sectie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Sectie> findOneFromSef(Integer sefId) {
        String sql = "select * from \"Sectii\" where \"idSefDeSectie\"=?";
        try(Connection connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword())){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sefId);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return Optional.of(new Sectie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Sectie> findAll() {
        String sql = "select * from \"Sectii\"";
        List<Sectie> list = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword())){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                list.add(new Sectie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Optional<Sectie> save(Sectie entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Sectie> delete(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<Sectie> update(Sectie entity) {
        return Optional.empty();
    }
}
