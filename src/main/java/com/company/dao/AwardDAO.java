package com.company.dao;

import com.company.models.Award;
import com.company.models.Quest;

import java.sql.*;
import java.util.ArrayList;

public class AwardDAO {
    ArrayList<Award> listOfAwards;
    ConnectionFactory conFactory;

    public AwardDAO() {
        conFactory = new ConnectionFactory();
    }

    public void readAllAwards() {
        try {
            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Awards\";");
            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String imgSrc = rs.getString("image");
                Timestamp dataCreation = rs.getTimestamp("data_creation");
                int creatorId = rs.getInt("creator_id");

                String format = "|%1$-4s|%2$-25s|%3$-70s|%4$-7s|%5$-10s|%6$-25s|%7$-7s\n";
                System.out.printf(format, id, title, description, price, imgSrc, dataCreation, creatorId);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readAllAwardsOrderByData() {
        try {
            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Awards\" ORDER BY data_creation;");
            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String imgSrc = rs.getString("image");
                Timestamp dataCreation = rs.getTimestamp("data_creation");
                int creatorId = rs.getInt("creator_id");

                String format = "|%1$-4s|%2$-25s|%3$-70s|%4$-7s|%5$-10s|%6$-25s|%7$-7s\n";
                System.out.printf(format, id, title, description, price, imgSrc, dataCreation, creatorId);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readAllAwardsOrderById() {
        try {
            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Awards\" ORDER BY id;");
            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String imgSrc = rs.getString("image");
                Timestamp dataCreation = rs.getTimestamp("data_creation");
                int creatorId = rs.getInt("creator_id");

                String format = "|%1$-4s|%2$-25s|%3$-70s|%4$-7s|%5$-10s|%6$-25s|%7$-7s\n";
                System.out.printf(format, id, title, description, price, imgSrc, dataCreation, creatorId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readAllAwardsOrderByPrice(String ascOrDesc) {
        try {
            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Awards\" ORDER BY price " + ascOrDesc.toUpperCase() + ";");
            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String imgSrc = rs.getString("image");
                Timestamp dataCreation = rs.getTimestamp("data_creation");
                int creatorId = rs.getInt("creator_id");

                String format = "|%1$-4s|%2$-25s|%3$-70s|%4$-7s|%5$-10s|%6$-25s|%7$-7s\n";
                System.out.printf(format, id, title, description, price, imgSrc, dataCreation, creatorId);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Award> readAwardList() {
        listOfAwards = new ArrayList<>();
        try {
            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Quests\";");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String imageSrc = rs.getString("image");
                Timestamp dataCreation = rs.getTimestamp("data_creation");
                int creatorId = rs.getInt("creator_id");

                listOfAwards.add(new Award(id, title, description, price, imageSrc, dataCreation, creatorId));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAwards;
    }

        public void deleteAwardById(int id) {
        PreparedStatement ps = null;

        try {
            ps = conFactory.getConnection().prepareStatement("DELETE FROM \"Awards\" WHERE id =" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void addAward(Award award) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("INSERT INTO \"Awards\" (id, title, description, price, image, data_creation, creator_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);");
            ps.setInt(1, award.getId());
            ps.setString(2, award.getTitle());
            ps.setString(3, award.getDescription());
            ps.setInt(4, award.getPrice());
            ps.setString(5, award.getImageSrc());
            ps.setTimestamp(6, award.getDataCreation());
            ps.setInt(7, award.getCreatorId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void updateAwardById(Award award) {
//        conFactory.updateQuery("UPDATE Awards SET title = ?, description = ?, price = ?, image = ?, data_creation = ?, creator_id = ? " +
//                "WHERE id =" + award.getId() + ";");
//    }


}

