//package com.GeJunKang.dao;
//
//import java.io.InputStream;
//
//public class Product {
//    private int  productId;
//    private int categoryId;
//    private String productName;
//    private String productDescription;
//    private InputStream picture;
//    private double price;
//
//    public Product(){
//
//    }
//
//    public Product(int productId, int categoryId, String productName, String productDescription, InputStream picture, double price) {
//        this.productId = productId;
//        this.categoryId = categoryId;
//        this.productName = productName;
//        this.productDescription = productDescription;
//        this.picture = picture;
//        this.price = price;
//    }
//
//    public int getProductId() {
//        return productId;
//    }
//
//    public void setProductId(int productId) {
//        this.productId = productId;
//    }
//
//    public int getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(int categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public String getProductDescription() {
//        return productDescription;
//    }
//
//    public void setProductDescription(String productDescription) {
//        this.productDescription = productDescription;
//    }
//
//    public InputStream getPicture() {
//        return picture;
//    }
//
//    public void setPicture(InputStream picture) {
//        this.picture = picture;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    @Override
//    public String toString() {
//        return "Product{" +
//                "productId=" + productId +
//                ", categoryId=" + categoryId +
//                ", productName='" + productName + '\'' +
//                ", productDescription='" + productDescription + '\'' +
//                ", picture=" + picture +
//                ", price=" + price +
//                '}';
//    }
//}


package com.GeJunKang.dao;

import com.GeJunKang.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements  IProductDao{
    @Override
    public int save(Product product, Connection con) throws SQLException {
        int n = 0;
        String sql = "insert into Product(ProductName,ProductDescription,picture,price,CategoryId) values(?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescription());
        if(product.getPicture()!=null) {
            //for sql server
            pt.setBinaryStream(3, product.getPicture());
            //for mysql
            //   pt.setBlob(3, product.getPicture());
        }
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryId());
        n = pt.executeUpdate();
        if (n > 0) {
            return n;
        }
        return 0;
    }//end save

    @Override
    public int delete(Integer productId, Connection con) throws SQLException {
        String sql="delete from Product where ProductId=?";
        PreparedStatement pstmt= con.prepareStatement(sql);
        pstmt.setInt(1,productId);
        return pstmt.executeUpdate();
    }

    @Override
    public int update(Product instance, Connection con) throws SQLException{
        String sql="update Product set ProductName=?,ProductDescription=?,picture=?,price=?,CategoryId=? where ProductId=?";
        PreparedStatement pstmt= con.prepareStatement(sql);
        pstmt.setString(1,instance.getProductName());
        pstmt.setString(2,instance.getProductDescription());
        if(instance.getPicture()!=null) {
            //for sql server
            pstmt.setBinaryStream(3, instance.getPicture());
            //for mysql
            //   pt.setBlob(3, product.getPicture());
        }
        pstmt.setDouble(4, instance.getPrice());
        pstmt.setInt(5, instance.getCategoryId());
        pstmt.setInt(6,instance.getProductId());
        return pstmt.executeUpdate();
    }

    @Override
    public Product findById(Integer productId, Connection con) throws SQLException {
        String sql="select * from Product where ProductId=?";
        PreparedStatement pstmt= con.prepareStatement(sql);
        pstmt.setInt(1,productId);
        ResultSet rs= pstmt.executeQuery();
        Product product=null;
        if(rs.next()){
            product=new Product();
            product.setProductId(rs.getInt("ProductId"));
            product.setProductName(rs.getString("ProductName"));
            product.setProductDescription(rs.getString("ProductDescription"));
            product.setPicture(rs.getBinaryStream("picture"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryId(rs.getInt("CategoryId"));
        }
        return product;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con)  throws SQLException{
        String sql="select * from Product where CategoryId=?";
        PreparedStatement pstmt= con.prepareStatement(sql);
        pstmt.setInt(1,categoryId);
        ResultSet rs= pstmt.executeQuery();
        List<Product> pro=new ArrayList<Product>();
        while(rs.next()){
            Product product=new Product();
            product.setProductId(rs.getInt("ProductId"));
            product.setProductName(rs.getString("ProductName"));
            product.setProductDescription(rs.getString("ProductDescription"));
            product.setPicture(rs.getBinaryStream("picture"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryId(rs.getInt("CategoryId"));
            pro.add(product);
        }
        return pro;
    }

    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        String sql="select * from Product where price between ? and ?";
        PreparedStatement pstmt= con.prepareStatement(sql);
        pstmt.setDouble(1,minPrice);
        pstmt.setDouble(2,maxPrice);
        ResultSet rs= pstmt.executeQuery();
        List<Product> pro=new ArrayList<Product>();
        while(rs.next()){
            Product product=new Product();
            product.setProductId(rs.getInt("ProductId"));
            product.setProductName(rs.getString("ProductName"));
            product.setProductDescription(rs.getString("ProductDescription"));
            product.setPicture(rs.getBinaryStream("picture"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryId(rs.getInt("CategoryId"));
            pro.add(product);
        }
        return pro;
    }

    @Override
    public List<Product> findAll(Connection con) throws SQLException {
        String sql="select * from Product";
        PreparedStatement pstmt= con.prepareStatement(sql);
        ResultSet rs= pstmt.executeQuery();
        List<Product> pro=new ArrayList<Product>();
        while(rs.next()){
            Product product=new Product();
            product.setProductId(rs.getInt("ProductId"));
            product.setProductName(rs.getString("ProductName"));
            product.setProductDescription(rs.getString("ProductDescription"));
            product.setPicture(rs.getBinaryStream("picture"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryId(rs.getInt("CategoryId"));
            pro.add(product);
        }
        return pro;
    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        String sql="select * from Product where ProductName=?";
        PreparedStatement pstmt= con.prepareStatement(sql);
        pstmt.setString(1,productName);
        ResultSet rs= pstmt.executeQuery();
        List<Product> pro=new ArrayList<Product>();
        while(rs.next()){
            Product product=new Product();
            product.setProductId(rs.getInt("ProductId"));
            product.setProductName(rs.getString("ProductName"));
            product.setProductDescription(rs.getString("ProductDescription"));
            product.setPicture(rs.getBinaryStream("picture"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryId(rs.getInt("CategoryId"));
            pro.add(product);
        }
        return pro;
    }

    @Override
    public List<Product> getPicture(Integer productId, Connection con) throws SQLException {
        String sql="select * from Product where ProductId=?";
        PreparedStatement pstmt= con.prepareStatement(sql);
        pstmt.setInt(1,productId);
        ResultSet rs= pstmt.executeQuery();
        List<Product> pro=new ArrayList<Product>();
        while(rs.next()){
            Product product=new Product();
            product.setProductId(rs.getInt("ProductId"));
            product.setProductName(rs.getString("ProductName"));
            product.setProductDescription(rs.getString("ProductDescription"));
            product.setPicture(rs.getBinaryStream("picture"));
            product.setPrice(rs.getDouble("price"));
            product.setCategoryId(rs.getInt("CategoryId"));
            pro.add(product);
        }
        return pro;
    }
    public byte[] getPictureById(Integer productId,Connection con) throws SQLException{
        byte[] imgByte=null;
        String sql="select picture from Product where ProductId=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,productId);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            Blob blob=rs.getBlob("picture");
            imgByte=blob.getBytes(1,(int)blob.length());
        }
        return imgByte;
    }
}