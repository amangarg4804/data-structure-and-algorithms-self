package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileCreator {



    public static void main(String[] args) throws IOException {
//        SELECT JSON_OBJECT ('id2' VALUE cust_id,
//        'name' VALUE (first ||' '|| last),
//                'joined' VALUE join_date) FROM customers;
        Path newFilePath = Paths.get("jsontest.json");
        Path createdFile= Files.createFile(newFilePath);

        List<Employee> employees = new ArrayList<>();
        for(int i=0; i< 9999; i++) {
            Employee employee = new Employee();
            employee.setCity("citycity" +i);
            employee.setDepartment("departtment" +1);
            employee.setId("ididididid" +i);
            employee.setName("namename"+1);
            employee.setMobileNo("99999"+i);
            employees.add(employee);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Files.write(createdFile, objectMapper.writeValueAsString(employees).getBytes(StandardCharsets.UTF_8));
    }


}

class Employee {
    private String name;
    private String mobileNo;
    private String city;
    private String id;
    private String department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
