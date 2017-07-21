package com.oonurkuru.restful.resources;

import com.oonurkuru.restful.domains.Employee;
import com.oonurkuru.restful.exceptions.RestFulException;
import com.oonurkuru.restful.services.EmployeeService;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by Onur Kuru on 21.7.2017.
 */

/**
 * Employee kaynaklarını sunan servis
 */
@Path("employees")
public class EmployeeResource {

    /**
     * Projenin amacı restful'un temel özellikleri olduğundan dolayı böyle bir yöntem kullanılmıştır. Bağımlılık Spring core
     * kullanılarak da yüklenebilirdi.
     */
    private EmployeeService employeeService;

    @PostConstruct
    public void init() {
        employeeService = new EmployeeService();
    }


    /**
     * Aldığı query parametrelerine göre uygun Employee kaynakları döndürülür.
     * Örn: api/employees?age=25&departmentName=AR-GE
     * TODO sayfa, kayıt sayısı, liste içeren alan, or ya da and'e göre arama desteği eklenecek
     *
     * @param info javax.ws.rs. sınıfı olan UriInfo ile Uri bilgilerine ulaşılabilir.
     * @return Response içerisinde hata modeli ya da bulunan Employeeler döndürülür.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findEmployeeByQuery(@Context UriInfo info) {

        List<Employee> employeeList;
        try {
            employeeList = employeeService.findEmployeeByQuery(info.getQueryParameters());
        } catch (RestFulException e) {
            return Response.status(500).entity(e.getRestFulExceptionModel()).build();
        }

        return Response.status(200).entity(employeeList).build();
    }


    /**
     * Yeni bir Employee kaynağı eklemek için kullanılır.
     *
     * @param employee eklenecek yeni kaynak verileri
     * @return Hata oluşması durumunda hata modeli oluşmazsa eklenen yeni kayıt gönderilir.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveEmployee(Employee employee) {

        try {
            employeeService.save(employee);
        } catch (RestFulException e) {
            return Response.status(500).entity(e.getRestFulExceptionModel()).build();
        }

        return Response.status(201).entity(employee).build();
    }


    /**
     * Var olan bir employee kaynağını güncellemek için kullanılır
     *
     * @param employee güncellenecek alanları içerir.
     * @return güncellenen yeni kaynağı döndürür.
     */
    @PUT
    @Path("/{id: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee) {

        try {
            employeeService.update(employee);
        } catch (RestFulException e) {
            return Response.status(500).entity(e.getRestFulExceptionModel()).build();
        }

        return Response.status(201).entity(employee).build();
    }


    /**
     * Employee kaynağı silmek için kullanılır.
     *
     * @param id silinecek kaynağın id si.
     * @return hata oluşması durumunda hata modeli, oluşmazsa boş içerik döndürülür.
     */
    @DELETE
    @Path("/{id: \\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("id") Integer id) {

        try {
            employeeService.delete(id);
        } catch (RestFulException e) {
            return Response.status(500).entity(e.getRestFulExceptionModel()).build();
        }

        return Response.status(204).build();
    }

}
