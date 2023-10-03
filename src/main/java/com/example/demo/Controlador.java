package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controlador {
    @PostMapping("/register_product")
    public String register_product(@RequestBody Products products) throws SQLException, ClassNotFoundException {
        String answer = "";

        String code = products.getCode();
        String name = products.getName();
        String amount = products.getAmount();
        String base_value = products.getBase_value();
        String base_public = products.getBase_public();
        String supplier = products.getSupplier();

        if (code==null || code.equals("") || code.length() < 0 || name==null ||name.equals("") || name.length() < 0 ||
                amount==null || amount.equals("") || amount.length() < 0 || base_value==null || base_value.equals("") ||
                base_value.length() < 0 || base_public==null || base_public.equals("") || base_public.length() < 0 ||
                supplier==null || supplier.equals("") || supplier.length() < 0){

            answer = "No se admiten campos vacios o nulos,";
            answer += " no se puedo registrar el producto";
        } else {
            Register(code, name, amount, base_value, base_public, supplier);

            answer = " Producto registrado de manera exitosa";
        }

        return answer;
    }

    private void Register(String code, String name, String amount, String base_value, String base_public, String supplier) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/proveedores";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM productos");


            // Sentencia INSERT
            String sql = "INSERT INTO productos (code, name, amount, basevalue, basepublic, supplier) VALUES (?, ?, ?, ?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, amount);
            preparedStatement.setString(4, base_value);
            preparedStatement.setString(5, base_public);
            preparedStatement.setString(6, supplier);

            // Ejecutar la sentencia
            int files = preparedStatement.executeUpdate();

            if (files > 0) {
                System.out.println("Producto registrado de manera exitosa.");
            } else {
                System.out.println("No se pudo registrar el producto");
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/edit_product")
    public String edit_product(@RequestBody Products products) throws SQLException, ClassNotFoundException {
        String answer = "";

        String code = products.getCode();
        String name = products.getName();
        String amount = products.getAmount();
        String base_value = products.getBase_value();
        String base_public = products.getBase_public();
        String supplier = products.getSupplier();

        if (code==null || code.equals("") || code.length() < 0 || name==null ||name.equals("") || name.length() < 0 ||
                amount==null || amount.equals("") || amount.length() < 0 || base_value==null || base_value.equals("") ||
                base_value.length() < 0 || base_public==null || base_public.equals("") || base_public.length() < 0 ||
                supplier==null || supplier.equals("") || supplier.length() < 0){

            answer = "No se admiten campos vacios o nulos,";
            answer += " no se puedo editar o actualizar el producto";
        } else {

            Edit(code, name, amount, base_value, base_public, supplier);

            answer = "Producto actualizado o editado de manera exitosa";
        }

        return answer;
    }

    private void Edit(String code, String name, String amount, String base_value, String base_public, String supplier) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/proveedores";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consult = "UPDATE productos SET name = ?, amount = ?, basevalue = ?, basepublic = ?, supplier = ? WHERE code = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consult);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, amount);
        preparedStatement.setString(3, base_value);
        preparedStatement.setString(4, base_public);
        preparedStatement.setString(5, supplier);
        preparedStatement.setString(6, code);

        int files = preparedStatement.executeUpdate();
        if (files > 0) {
            System.out.println("Producto actualizado de manera exitosa");
        } else {
            System.out.println("No se encontro el producto para actualizar");
        }

        preparedStatement.close();
        connection2.close();
    }

    @GetMapping("/search_products")
    public List<Products> search_products() throws SQLException, ClassNotFoundException {

        List<Products> list = Select_consul();

        String answer = "Estoy en el metodo de buscar";


        return list;
    }

    private List<Products> Select_consul() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/proveedores";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM productos");

        List<Products> list = new ArrayList<>();

        while (resultSet2.next()) {
            String code = resultSet2.getString("code");
            String name = resultSet2.getString("name");
            String amount = resultSet2.getString("amount");
            String base_value = resultSet2.getString("basevalue");
            String base_public = resultSet2.getString("basepublic");
            String supplier = resultSet2.getString("supplier");

            Products products = new Products(code, name, amount, base_value, base_public, supplier);

            list.add(products);

        }
        return list;
    }

    @GetMapping("/search_products_costume")
    public List<Products> search_products_custome() throws SQLException, ClassNotFoundException {

        List<Products> list = Select_consul_costume();

        String answer = "Estoy en el metodo de buscar";


        return list;
    }

    private List<Products> Select_consul_costume() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/proveedores";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM productos");

        List<Products> list = new ArrayList<>();

        while (resultSet2.next()) {
            String code = resultSet2.getString("code");
            String name = resultSet2.getString("name");
            String amount = resultSet2.getString("amount");
            String base_value = resultSet2.getString("basevalue");
            String base_public = resultSet2.getString("basepublic");


            Products products = new Products(code, name, amount, base_value, base_public, null);

            list.add(products);

        }
        return list;
    }
    @GetMapping("/costume/search_product")
    public Products costume_search_product(@RequestBody Products products) throws SQLException, ClassNotFoundException {

        String code = products.getCode();

        if (products.getCode()==null || products.getCode().equals("") || products.getCode().length()<0) {

            products.setCode("No se encuentra un producto registrado con el codigo ingresado o los campos están vacios/nulos");

        }else {

            products = costume_Select_consul(code);
        }

        String answer = "Estoy en el metodo de buscar un solo producto";


        return products;
    }

    private Products costume_Select_consul(String code) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/proveedores";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consult_SQL = "SELECT * FROM productos WHERE code = ?";

        PreparedStatement statement = connection.prepareStatement(consult_SQL);
        statement.setString(1, code); // Establecer el valor del parámetro

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        // Procesar el resultado si existe
        if (resultSet.next()) {

            code = resultSet.getString("code");
            String name = resultSet.getString("name");
            String amount = resultSet.getString("amount");
            String base_value = resultSet.getString("basevalue");
            String base_public = resultSet.getString("basepublic");

            Products products = new Products(code, name, amount, base_value, base_public, null);

            return products;
        }
        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();

        return null;
    }
}