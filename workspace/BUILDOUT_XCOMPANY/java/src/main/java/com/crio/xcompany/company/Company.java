package com.crio.xcompany.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
//import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Company{
    private String companyName;
    private Employee founder;
    private Map<String,Employee> employeeBook;
    

    private Company(String companyName, Employee founder) {
        this.companyName = companyName;
        this.founder = founder;
        employeeBook = new LinkedHashMap<String,Employee>();
        employeeBook.put(founder.getName(), founder);
    }
    

    public static Company create(String companyName, Employee founder){
        return new Company(companyName,founder);
    } 


    public String getCompanyName() {
        return companyName;
    }


    public List<Employee> getDirectReports(String managerName) {
        return employeeBook.values().stream()
                .filter(employee -> employee.getManager() != null && employee.getManager().equals(managerName))
                .collect(Collectors.toList());
    }


    public Employee getEmployee(String employeeName) {
        return employeeBook.get(employeeName);
    }


    public void assignManager(String employeeName, String managerName) {
        if(employeeBook.containsKey(employeeName)){
            employeeBook.get(employeeName).setManager(managerName);
        }

    }


    public List<Employee> getTeamMates(String employeeName) {
        // Employee employee = employeeBook.get(employeeName);
        // String manager = employee.getManager();
        
        // if (manager == null) {
        //     // Handle the case where the employee doesn't have a manager.
        //     return new ArrayList<>();
        // }
        
        // List<Employee> employees = employeeBook.values()
        //         .stream()
        //         .filter(e -> manager.equals(e.getManager()))
        //         .collect(Collectors.toList());
        
        // employees.remove(employee); // Remove the employee from the list of team members.
    
        // return employees;

        String manager=employeeBook.get(employeeName).getManager();
        List<Employee> answer=new ArrayList<>();
        answer.add(employeeBook.get(manager));

        for(Employee emp:employeeBook.values()){
               if(emp.getManager()!=null && emp.getManager().equals(manager)){
                     answer.add(emp);
               }
        }

        return answer;
    }
    


    public void deleteEmployee(String employeeName) {
        if(employeeName != null && employeeBook.containsKey(employeeName)){
            employeeBook.remove(employeeName);
        }
    }

    public List<List<Employee>> getEmployeesDirect(String managerName) {
        List<List<Employee>> hierarchy = new ArrayList<>();
        List<Employee> initialDirectReports = employeeBook.values()
                .stream()
                .filter(employee -> managerName.equals(employee.getManager()))
                .collect(Collectors.toList());
    
        if (!initialDirectReports.isEmpty()) {
            hierarchy.add(initialDirectReports);
    
            for (Employee directReport : initialDirectReports) {

                List<List<Employee>> subHierarchy = getEmployeesDirect(directReport.getName());
                if (!subHierarchy.isEmpty()) {
                    hierarchy.addAll(subHierarchy);
                }
            }
        }
    
        return hierarchy;
    }
    
    public List<List<Employee>> getEmployeeHierarchy(String managerName) {
        //  List<List<Employee>> hierarchy = new ArrayList<>();
        //  Employee manager = employeeBook.get(managerName);
       
        
        // if (manager != null) {
        //     List<Employee> managerList = new ArrayList<>();
        //     managerList.add(manager);
            
        //     hierarchy.add(managerList);
    
        //     List<Employee> initialDirectReports = employeeBook.values()
        //             .stream()
        //             .filter(employee -> managerName.equals(employee.getManager()))
        //             .collect(Collectors.toList());
    
        //     if (!initialDirectReports.isEmpty()) {
        //         hierarchy.add(initialDirectReports);
    
        //         for (Employee directReport : initialDirectReports) {

        //             List<List<Employee>> subHierarchy = getEmployeesDirect(directReport.getName());
        //             if (!subHierarchy.isEmpty()) {
        //                 hierarchy.addAll(subHierarchy);
        //             }

        //         }
        //     }
        // }
    
        // return hierarchy;

         List<List<Employee>> answer=new ArrayList<>();
         Queue<String> parant=new LinkedList<>();
         parant.add(managerName);
         List<Employee> empsss=new ArrayList<>();
         empsss.add(employeeBook.get(managerName));
         answer.add(empsss);

         while(!parant.isEmpty()){
            
            List<Employee> ls=new ArrayList<>();
            Queue<String> child=new LinkedList<>();

                while(!parant.isEmpty()){
                    String man=parant.poll();
                    for(Employee emp:employeeBook.values()){
                        if(emp.getManager()!=null && emp.getManager().equals(man)){
                            child.add(emp.getName());
                            ls.add(emp);
                        }
                }
                }
                if(!ls.isEmpty()){
                    answer.add(ls);
                }
           
            parant=child;
        }

        return answer;

    }

    public void registerEmployee(String employeeName, Gender valueOf) {
        Employee employee = new Employee(employeeName,valueOf);
        employeeBook.put(employee.getName(), employee);

    }

    // TODO: CRIO_TASK_MODULE_XCOMPANY
    // Please define all the methods required here as mentioned in the XCompany BuildOut Milestone for each functionality before implementing the logic.
    // This will ensure that the project can be compiled successfully.

}
