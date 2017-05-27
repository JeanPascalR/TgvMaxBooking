package com.rambaud.train.train_booking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rambaud.train.train_booking.engine.Context;
import com.rambaud.train.train_booking.engine.steps.EngineRoutineStep;
import com.rambaud.train.train_booking.exception.FunctionalException;
import com.rambaud.train.train_booking.exception.TechnicalException;
import com.rambaud.train.train_booking.model.Customer;
import com.rambaud.train.train_booking.model.TravelDetails;

public class App 
{
	private static final Logger LOGGER = LogManager.getLogger(App.class);
	
    public static void main( String[] args ) throws InterruptedException
    {
        LOGGER.info("###########     Starting routine       ###########");
        LOGGER.info("departureCity={};arrivalCity={};date={};hour={};minute={};customerId={}",
        		args[0], args[1], args[2], args[3], args[4], args[5]);
        int code = run(args[0], args[1], args[2], Integer.valueOf(args[3]), Integer.valueOf(args[4]), Integer.valueOf(args[5]));
        LOGGER.info("########### Exiting with status code "+ code + " ###########");
        System.exit(code);
    }
    
    public static int run(String departureCity, String arrivalCity, String date, int hour, int minute, int customerId) {
    	TravelDetails travel = new TravelDetails(departureCity, arrivalCity, date, hour, minute);
    	Customer customer = Customer.getById(customerId);
    	int codeReturn = 0;
    	try {
        	new EngineRoutineStep(travel, customer).run();
    	} catch (TechnicalException e) {
        	LOGGER.error("Unable to perform routine due to technical error", e);
        	codeReturn = 1;
        } catch (FunctionalException e) {
        	LOGGER.error("Unable to perform routine due to functional error", e);
        	codeReturn = 2;
        } catch (Throwable t) {
        	LOGGER.error("Unable to perform routine due to unknown error", t);
        	codeReturn = 3;
        } finally {
        	Context.getInstance().getDriver().quit();
        }
    	return codeReturn;
    }
}
