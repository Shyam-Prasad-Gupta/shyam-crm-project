package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import com.luv2code.springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public Object saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public int deleteCustomer(int theId);

	public Session getCurrentSession();

}
