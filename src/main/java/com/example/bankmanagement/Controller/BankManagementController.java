package com.example.bankmanagement.Controller;

import com.example.bankmanagement.ApiResponse.ApiResponse;
import com.example.bankmanagement.Model.Customers;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/Bank-management")
public class BankManagementController {

    ArrayList<Customers> customers = new ArrayList<>();
    @GetMapping("/get")
    public ArrayList<Customers> getBankManagements(){
        return customers;
    }
    @PostMapping("/add")
    public ApiResponse add(@RequestBody Customers customer){
    customers.add(customer);
    return new ApiResponse("customers added",200);
    }
    @PutMapping("/update/{index}")
    public ApiResponse update(@PathVariable int index,@RequestBody Customers customer){
        customers.set(index,customer);
        return new ApiResponse("customers updated",200);
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse delete(@PathVariable int index){
        customers.remove(index);
        return new ApiResponse("customers removed",200);
    }
    @PutMapping("/deposit/{index}")
    public ApiResponse deposit(@PathVariable int index,@RequestBody Customers customer){
        double balance = customers.get(index).getBalance();
        customer.setBalance(balance+customer.getBalance());
        customers.set(index,customer);
        return new ApiResponse("Successfully done",200);
    }
    @PutMapping("/withdraw/{index}")
    public ApiResponse withdraw(@PathVariable int index,@RequestBody Customers customer) throws Exception {
        double balance = customers.get(index).getBalance();
        if(balance>=customer.getBalance()) {
            customer.setBalance(balance-customer.getBalance());
            customers.set(index,customer);
            return new ApiResponse("Successfully done",200);
        }
        else return new ApiResponse("Did not complete successfully",400);



    }

}
