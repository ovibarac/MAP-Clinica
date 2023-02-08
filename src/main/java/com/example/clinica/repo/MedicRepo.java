package com.example.clinica.repo;

import com.example.clinica.domain.Consultatie;
import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Sectie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicRepo extends AbstractDbRepo<Integer, Medic>{
    public MedicRepo(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public Optional<Medic> findOne(Integer integer) {
        String sql = "select * from \"Medici\" where id=?";
        try(Connection connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword())){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, integer);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return Optional.of(new Medic(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getBoolean(5)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Medic> findByNume(String nume){
        String sql = "select * from \"Medici\" where nume=?";
        try(Connection connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword())){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, nume);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return Optional.of(new Medic(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getBoolean(5)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Medic> findAll() {
        String sql = "select * from \"Medici\"";
        List<Medic> list = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword())){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                list.add(new Medic(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getBoolean(5)));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Optional<Medic> save(Medic entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Medic> delete(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<Medic> update(Medic entity) {
        return Optional.empty();
    }
}
