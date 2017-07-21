package com.oonurkuru.restful.services;

import com.oonurkuru.restful.domains.Employee;
import com.oonurkuru.restful.exceptions.RestFulException;
import com.oonurkuru.restful.util.QueryParamHandler;

import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Onur Kuru on 21.7.2017.
 */

/**
 * Employee modeline ait servis sınıfıdır. Employee modelin ilgili işlemlerden sorumludur.
 */
public class EmployeeService {

    /**
     * Veri tabanı bağlantısı kullanılmadığı için kayıtlar bu Map'de tutulmaktadır.
     */
    public static Map<Integer, Employee> employeeList = new HashMap<Integer, Employee>();


    static {
        Employee employee = new Employee(1, "Ahmet", 25, 5000, "AR-GE");
        employeeList.put(1, employee);

        employee = new Employee(2, "Mehmet", 22, 6000, "AR-GE");
        employeeList.put(2, employee);

        employee = new Employee(3, "Veli", 30, 5000, "AR-GE");
        employeeList.put(3, employee);

        employee = new Employee(4, "Hasan", 32, 8000, "AR-GE");
        employeeList.put(4, employee);

        employee = new Employee(5, "Kazım", 27, 6000, "AR-GE");
        employeeList.put(5, employee);
    }


    /**
     * Employee kaynağı eklemek için kullanılır. Eklenecek kaydın tüm alanlarının girilmesi zorlunludur.
     *
     * @param employee eklenecek kaynak
     */
    public void save(Employee employee) throws RestFulException {
        //Alanları Kontrol Et

        employee.setId(employeeList.size() + 1);
        employeeList.put(employeeList.size() + 1, employee);
    }


    /**
     * Kaynak güncelleme metodu
     *
     * @param newEmployee güncellenecek kaynağa ait veriler
     * @throws RestFulException alanların eksik olması durumunda hata fırlatılır.
     */
    public void update(Employee newEmployee) throws RestFulException {

        Employee oldEmployee = employeeList.get(newEmployee.getId());

        if (oldEmployee == null) {
            throw new RestFulException(101, "Illegal Access", "Geçerli Bir Nesne Gereklidir.");
        }

        employeeList.put(newEmployee.getId(), newEmployee);
    }


    /**
     * Query'lere göre kaynak bulma metodu
     *
     * @param queryList bulunması istenen kriterler
     * @return bulunan kaynkalar döndürülür
     * @throws RuntimeException
     */
    public List<Employee> findEmployeeByQuery(MultivaluedMap<String, String> queryList) throws RuntimeException {

        List<Employee> filterList = (List<Employee>) (List<?>) QueryParamHandler.findByQueryParams(new ArrayList<>(employeeList.values()), queryList);

        return filterList;
    }


    /**
     * kaynak silme metodu
     *
     * @param id silinmesi istenen id
     * @throws RestFulException
     */
    public void delete(Integer id) throws RestFulException {

        if (id == null) {
            throw new RestFulException(100, "Not Null", "Tüm alanlar gereklidir.");
        }

        Employee employee = employeeList.get(id);

        if (employee == null) {
            throw new RestFulException(101, "Illegal Access", "Geçerli Bir Nesne Gereklidir.");
        }

        employeeList.remove(id);
    }
}
