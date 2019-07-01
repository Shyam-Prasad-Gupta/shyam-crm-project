package com.shyam.aspects;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Aspect
@Component
public class LoggingAspect {

	@Autowired
	CustomerDAO customerDao;

	@Pointcut("execution(public * com.luv2code.springdemo.dao.CustomerDAOImpl.saveCustomer(*))")
	private void aroundAddUpdateUser() {
	}

	@Around("aroundAddUpdateUser()")
	public void executeBeforeAndAfterAddingOrUpdatingCustomer(ProceedingJoinPoint pJoinPoint) {
		// System.out.println("<<<<<<<<<<Hey, I am here in the around advice>>>>>>.");
		Logger log = Logger.getLogger(pJoinPoint.getSignature().getClass().getSimpleName());
		log.log(Level.INFO, "Method signature from joinpoint is: " + pJoinPoint.getSignature());
		boolean toUpdate = false;
		Customer customerToInsertOrUpdate = (Customer) pJoinPoint.getArgs()[0];
		Customer customerPreviousValue = null;
		customerPreviousValue = new Customer();
		BeanUtils.copyProperties(customerToInsertOrUpdate, customerPreviousValue);;
		//Customer customerPreviousValue = null;
		if (pJoinPoint.getArgs() != null) {
			if (customerToInsertOrUpdate.getId() > 0) {
				toUpdate = true;
				customerPreviousValue = customerDao.getCustomer(customerToInsertOrUpdate.getId());
				log.log(Level.INFO, "----------Updating customer with previous value------------\n"
						+ customerPreviousValue.toString());
				customerDao.getCurrentSession().detach(customerPreviousValue);
			} else {
				log.log(Level.INFO, "---------Inserting customer--------\n" + customerToInsertOrUpdate.toString());
			}
		}

		try {
			customerToInsertOrUpdate = (Customer) pJoinPoint.proceed();
			if (customerToInsertOrUpdate != null) {
				if (toUpdate) {
					log.log(Level.INFO, "Customer is updated: " + customerToInsertOrUpdate.toString());
					log.log(Level.INFO, "---------Customer is updated successfully----------");
				} else {
					log.log(Level.INFO, "Customer is inserted: " + customerToInsertOrUpdate.toString());
					log.log(Level.INFO, "---------Customer is inserted successfully----------");
				}
			}
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	@AfterReturning(pointcut = "execution(* com.luv2code.springdemo.dao.CustomerDAOImpl.deleteCustomer(int))", returning = "result")
	public void executeAfterCustomerDelete(JoinPoint jPoint, int result) {
		Logger log = Logger.getLogger(jPoint.getSignature().getClass().getSimpleName());
		log.log(Level.INFO, "Customer is deleted successfully with id: " + result);
	}
}
