package com.luv2code.springdemo.service;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);
	
	public void saveCustomerList(List<Customer> customerList);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
	
}
