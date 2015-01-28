package fr.ensimag.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ExistingUserException extends RuntimeException {

}
