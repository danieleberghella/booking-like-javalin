package com.berghella.daniele.dao;

import com.berghella.daniele.model.User;
import com.berghella.daniele.utility.database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class UserDAO implements GenericDAO<User> {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public void save(User user) {
        String insertUserSQL = "INSERT INTO public.user(id, name, surname, email, birthDate) " + "VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement psInsertUser = connection.prepareStatement(insertUserSQL);
            psInsertUser.setObject(1, user.getId());
            psInsertUser.setString(2, user.getName());
            psInsertUser.setString(3, user.getSurname());
            psInsertUser.setString(4, user.getEmail());
            psInsertUser.setObject(5, user.getBirthDate());
            psInsertUser.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String getAllUsersSQL = "SELECT * FROM public.user";
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(getAllUsersSQL);
            while (rs.next()){
                LocalDate birthDate;
                birthDate = rs.getDate("birthdate") != null
                        ? rs.getDate("birthdate").toLocalDate()
                        : null;

                users.add(
                        new User(
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("email"),
                                birthDate)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public Optional<User> findById(UUID id) {
        String selectUserByIdSQL = "SELECT * FROM public.user WHERE id = ?";

        try {
            PreparedStatement psSelectUserById = connection.prepareStatement(selectUserByIdSQL);
            psSelectUserById.setObject(1, id);
            ResultSet rs = psSelectUserById.executeQuery();
            if (rs.next()) {
                LocalDate birthDate;
                birthDate = rs.getDate("birthdate") != null
                        ? rs.getDate("birthdate").toLocalDate()
                        : null;

                User user = new User();
                user.setId(id);
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setEmail(rs.getString("email"));
                user.setBirthDate(birthDate);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public User update(User updatedUser, UUID oldUserId) {
        if (findById(oldUserId).isPresent()){
            StringBuilder sql = new StringBuilder("UPDATE public.user SET ");
            List<Object> parameters = new ArrayList<>();
            if (updatedUser.getName() != null) {
                sql.append("name = ?, ");
                parameters.add(updatedUser.getName());
            }
            if (updatedUser.getSurname() != null) {
                sql.append("surname = ?, ");
                parameters.add(updatedUser.getSurname());
            }
            if (updatedUser.getEmail() != null) {
                sql.append("email = ?, ");
                parameters.add(updatedUser.getEmail());
            }
            if (updatedUser.getBirthDate() != null) {
                sql.append("birthdate = ?, ");
                parameters.add(updatedUser.getBirthDate());
            }

            sql.setLength(sql.length() - 2);
            sql.append(" WHERE id = ?");
            parameters.add(oldUserId);

            try {
                PreparedStatement psUpdateUser = connection.prepareStatement(sql.toString());
                for (int i = 0; i < parameters.size(); i++) {
                    psUpdateUser.setObject(i + 1, parameters.get(i));
                }
                psUpdateUser.executeUpdate();
                return findById(oldUserId).orElse(null);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
//        users.remove(id);
    }

}