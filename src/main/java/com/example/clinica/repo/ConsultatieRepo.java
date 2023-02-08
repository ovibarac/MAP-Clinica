package com.example.clinica.repo;

import com.example.clinica.domain.Consultatie;
import com.example.clinica.domain.Medic;
import com.example.clinica.domain.Sectie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsultatieRepo extends AbstractDbRepo<Integer, Consultatie> {
    public ConsultatieRepo(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    public Optional<Consultatie> findOne(Integer integer) {
        String sql = "select * from \"Consultatii\" where id=?";
        try(Connection connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword())){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, integer);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return Optional.of(new Consultatie(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5).toLocalDateTime()));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Consultatie> findAll() {
        String sql = "select * from \"Consultatii\"";
        List<Consultatie> list = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword())){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                list.add(new Consultatie(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5).toLocalDateTime()));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Optional<Consultatie> save(Consultatie entity) {
        String sql = "insert into \"Consultatii\" (\"idMedic\",\"CNPPacient\", \"NumePacient\", data)  values(?,?,?,?)";
        try(Connection connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword())){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,entity.getIdMedic());
            ps.setString(2,entity.getCNPPacient());
            ps.setString(3,entity.getNumePacient());
            ps.setTimestamp(4,Timestamp.valueOf(entity.getData()));
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Consultatie> delete(Integer integer) {
        String sql = "delete from \"Consultatii\" where id=?";
        try(Connection connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword())){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,integer);

            ps.execute();
            return findOne(integer);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<Consultatie> update(Consultatie entity) {
        return Optional.empty();
    }
}
