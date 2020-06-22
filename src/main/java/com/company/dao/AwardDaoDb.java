package com.company.dao;

import com.company.models.Award;
import com.company.models.users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AwardDaoDb implements AwardDao {
    private List<Award> listOfAwards;
    private final ConnectionFactory conFactory;

    public AwardDaoDb() {
        conFactory = new ConnectionFactory();
    }

    @Override
    public List<Award> readAwardListWithMentors() {
        listOfAwards = new ArrayList<>();
        try {
            ResultSet rs = conFactory.executeQuery("SELECT \"Awards\".id, title, description, price, image, data_creation, (CONCAT(m.name, ' ', m.surname)) AS mentor FROM \"Awards\"\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM users WHERE role_id = 2\n" +
                    "    ) m\n" +
                    "ON \"Awards\".creator_id = m.id\n" +
                    "ORDER BY \"Awards\".id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String imageSrc = rs.getString("image");
                Timestamp dataCreation = rs.getTimestamp("data_creation");
                String mentor = rs.getString("mentor");

                listOfAwards.add(new Award(id, title, description, price, imageSrc, dataCreation, mentor));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAwards;
    }

    @Override
    public List<Award> readAwardListByMentor(User user) {
        listOfAwards = new ArrayList<>();
        String userIdStr = String.valueOf(user.getId());
        try {
            ResultSet rs = conFactory.executeQuery("SELECT \"Awards\".id, title, description, price, image, data_creation, (CONCAT(m.name, ' ', m.surname)) AS mentor FROM \"Awards\"\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM users WHERE role_id = 2\n" +
                    "    ) m\n" +
                    "ON \"Awards\".creator_id = m.id\n" +
                    "WHERE \"Quests\".mentor_id = " +
                    userIdStr +
                    "ORDER BY \"Awards\".id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String imageSrc = rs.getString("image");
                Timestamp dataCreation = rs.getTimestamp("data_creation");
                String mentorDetails = rs.getString("mentor");

                listOfAwards.add(new Award(id, title, description, price, imageSrc, dataCreation, mentorDetails));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAwards;
    }

    @Override
    public void deleteAwardById(int id) {
        PreparedStatement ps = null;

        try {
            ps = conFactory.getConnection().prepareStatement("DELETE FROM \"Awards\" WHERE id =" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAward(Award award) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("INSERT INTO \"Awards\" (id, title, description, price, image, data_creation, creator_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);");
            // TODO: remove id inserting!
            ps.setInt(1, award.getId());
            ps.setString(2, award.getTitle());
            ps.setString(3, award.getDescription());
            ps.setInt(4, award.getPrice());
            ps.setString(5, award.getImageSrc());
            ps.setTimestamp(6, award.getDataCreation());
            ps.setInt(7, award.getMentorId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAwardTitleById(int id, String title) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Awards\" SET surname = '" + title + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateAwardDescriptionById(int id, String description) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Awards\" SET surname = '" + description + "' " +
                    "WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAwardPriceById(int id, int price) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Awards\" SET surname = " + price +
                    " WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAwardCreatorIdById(int id, int creatorId) {
        PreparedStatement ps = null;
        try {
            ps = conFactory.getConnection().prepareStatement("UPDATE \"Awards\" SET surname = " + creatorId +
                    " WHERE id=" + id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List getAllElements() {
        listOfAwards = new ArrayList<>();
//        String userIdStr = String.valueOf(user.getId());
        try {
            ResultSet rs = conFactory.executeQuery("SELECT \"Awards\".id, title, description, price, image, data_creation, (CONCAT(m.name, ' ', m.surname)) AS mentor FROM \"Awards\"\n" +
                    "INNER JOIN (\n" +
                    "    SELECT * FROM users WHERE role_id = 2\n" +
                    "    ) m\n" +
                    "ON \"Awards\".creator_id = m.id\n" +
//                    "WHERE \"Quests\".mentor_id = " +
//                    userIdStr +
                    "ORDER BY \"Awards\".id;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                String imageSrc = rs.getString("image");
                Timestamp dataCreation = rs.getTimestamp("data_creation");
                String mentorDetails = rs.getString("mentor");

                listOfAwards.add(new Award(id, title, description, price, imageSrc, dataCreation, mentorDetails));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAwards;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean insert(Object o) {
        return false;
    }

    @Override
    public boolean edit(Object o) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }


//    public void readAllAwards() {
//        try {
//            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Awards\";");
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String title = rs.getString("title");
//                String description = rs.getString("description");
//                int price = rs.getInt("price");
//                String imgSrc = rs.getString("image");
//                Timestamp dataCreation = rs.getTimestamp("data_creation");
//                int creatorId = rs.getInt("creator_id");
//
//                String format = "|%1$-4s|%2$-25s|%3$-70s|%4$-7s|%5$-10s|%6$-25s|%7$-7s\n";
//                System.out.printf(format, id, title, description, price, imgSrc, dataCreation, creatorId);
//            }
//            rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public void readAllAwardsOrderByData() {
//        try {
//            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Awards\" ORDER BY data_creation;");
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String title = rs.getString("title");
//                String description = rs.getString("description");
//                int price = rs.getInt("price");
//                String imgSrc = rs.getString("image");
//                Timestamp dataCreation = rs.getTimestamp("data_creation");
//                int creatorId = rs.getInt("creator_id");
//
//                String format = "|%1$-4s|%2$-25s|%3$-70s|%4$-7s|%5$-10s|%6$-25s|%7$-7s\n";
//                System.out.printf(format, id, title, description, price, imgSrc, dataCreation, creatorId);
//            }
//            rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public void readAllAwardsOrderById() {
//        try {
//            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Awards\" ORDER BY id;");
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String title = rs.getString("title");
//                String description = rs.getString("description");
//                int price = rs.getInt("price");
//                String imgSrc = rs.getString("image");
//                Timestamp dataCreation = rs.getTimestamp("data_creation");
//                int creatorId = rs.getInt("creator_id");
//
//                String format = "|%1$-4s|%2$-25s|%3$-70s|%4$-7s|%5$-10s|%6$-25s|%7$-7s\n";
//                System.out.printf(format, id, title, description, price, imgSrc, dataCreation, creatorId);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public void readAllAwardsOrderByPrice(String ascOrDesc) {
//        try {
//            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Awards\" ORDER BY price " + ascOrDesc.toUpperCase() + ";");
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String title = rs.getString("title");
//                String description = rs.getString("description");
//                int price = rs.getInt("price");
//                String imgSrc = rs.getString("image");
//                Timestamp dataCreation = rs.getTimestamp("data_creation");
//                int creatorId = rs.getInt("creator_id");
//
//                String format = "|%1$-4s|%2$-25s|%3$-70s|%4$-7s|%5$-10s|%6$-25s|%7$-7s\n";
//                System.out.printf(format, id, title, description, price, imgSrc, dataCreation, creatorId);
//            }
//            rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


//    public List<Award> readAwardList() {
//        listOfAwards = new ArrayList<>();
//        try {
//            ResultSet rs = conFactory.executeQuery("SELECT * FROM \"Awards\" ORDER BY id;");
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String title = rs.getString("title");
//                String description = rs.getString("description");
//                int price = rs.getInt("price");
//                String imageSrc = rs.getString("image");
//                Timestamp dataCreation = rs.getTimestamp("data_creation");
//                int creatorId = rs.getInt("creator_id");
//
//                listOfAwards.add(new Award(id, title, description, price, imageSrc, dataCreation, creatorId));
//            }
//            rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return listOfAwards;
//    }
}
