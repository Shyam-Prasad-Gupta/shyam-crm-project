package com.luv2code.springdemo.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import com.opencsv.CSVWriter;
import com.shyam.commonutils.CommonFileUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject our customer service
	@Autowired
	private CustomerService customerService;
	private String errorMessage;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		// get customers from the service
		List<Customer> theCustomers = customerService.getCustomers();

		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);

		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Customer theCustomer = new Customer();

		theModel.addAttribute("customer", theCustomer);

		return "customer-form";
	}

	@GetMapping("/downloadCSV")
	public void downloadRecordsInCSVFormat(Model theModel, HttpServletResponse resp, HttpServletRequest req) {

		List<Customer> customers = customerService.getCustomers();
		// creating the csv file
		if (!CollectionUtils.isEmpty(customers)) {
			try {
				//////////another way of doing it/////////
				File file = File.createTempFile("customers", ".csv"); 
				CSVWriter csvWriter = new CSVWriter(new FileWriter(file)); 
				int sNo = 0; 
				for(Customer cust :	customers) { 
					csvWriter.writeNext(new String[] {Integer.toString(++sNo), cust.getFirstName(), cust.getLastName(), cust.getEmail()}); 
				}
				csvWriter.close();
				//this header value tells the browser to this is attachment and must be downloaded with the given file name
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=Customers" + System.currentTimeMillis() + ".csv");
				resp.setHeader(headerKey, headerValue);
				resp.setContentType("application/csv");
				//now write file to the response resp.setContentType("application/csv");
				OutputStream os = resp.getOutputStream(); 
				Files.copy(file.toPath(), os);
				
				os.flush();
				os.close();
				///////////////////////////

				/////////one way of doing it/////////////
				/*String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=Customers.csv");
				resp.setContentType("text/csv");
				resp.setHeader(headerKey, headerValue);

				try (final CSVWriter writer = new CSVWriter(resp.getWriter())) {

					writer.writeNext(new String[] { "S No.", "First Name", "Last Name", "Email ID" });
					int counter = 0;
					for (Customer cust : customers) {
						// cast/convert to String where needed
						writer.writeNext(new String[] { ++counter + "", cust.getFirstName(), cust.getLastName(),
								cust.getEmail() });
					}
					writer.close();
				}*/
				//////////////////////

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@GetMapping("/downloadPDF")
	public void downloadPDF(HttpServletResponse resp, HttpServletRequest req) {
		
		
		
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

		// save the customer using our service
		customerService.saveCustomer(theCustomer);

		return "redirect:/customer/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

		// get the customer from our service
		Customer theCustomer = customerService.getCustomer(theId);

		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);

		// send over to our form
		return "customer-form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {

		// delete the customer
		customerService.deleteCustomer(theId);

		return "redirect:/customer/list";
	}
<<<<<<< HEAD
}
=======

	@RequestMapping(value = "/processCSV", method = RequestMethod.POST)
	public String parseAndAddCustomerDataFromCSV(@RequestParam("uploadedCSV") MultipartFile csvFile) {

		// checking if the file is empty
		if (!csvFile.isEmpty()) {
			// allowing only txt and csv format
			if (CommonFileUtils.isValidFileFormat(csvFile.getOriginalFilename())) {

				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
					String line = null;
					List<Customer> customerListFromCSV = new ArrayList<Customer>();
					Customer customer = null;
					while ((line = br.readLine()) != null) {
						String[] recordDataAsArray = line.split(",");
						customer = new Customer();
						customer.setFirstName(recordDataAsArray[0].trim());
						customer.setLastName(recordDataAsArray[1].trim());
						customer.setEmail(recordDataAsArray[2].trim());

						if (customer.completeData()) {
							customerListFromCSV.add(customer);
						}
					}
					if (!customerListFromCSV.isEmpty()) {
						customerService.saveCustomerList(customerListFromCSV);
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return "redirect:/customer/list";
	}
}
>>>>>>> dev
