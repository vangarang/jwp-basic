package next;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import next.model.User;

public class UserValidatorTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    public void userIdIsNull() {
        User user = new User(null, "passowrd", "choinw", "email@email.com");

        Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );

        assertEquals( 1, constraintViolations.size() );
        System.out.println(constraintViolations.iterator().next().getMessage());
        //assertEquals( "must not be null", constraintViolations.iterator().next().getMessage() );
    }
    
    @Test
    public void userIdLength() {
        User user = new User("id1", "passowrd", "choinw", "email@email.com");

        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate( user );

        assertEquals( 1, constraintViolations.size() );
        System.out.println(constraintViolations.iterator().next().getMessage());
        //assertEquals( "must not be null", constraintViolations.iterator().next().getMessage() );
        
        
        user = new User("ididid1234567890", "passowrd", "choinw", "email@email.com");

        constraintViolations = validator.validate( user );

        assertEquals( 1, constraintViolations.size() );
        System.out.println(constraintViolations.iterator().next().getMessage());
    }
    
    @Test
    public void invalidUser() {
        User user = new User("us", "passowrd", "choinw", "email");

        Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );
        assertEquals( 2, constraintViolations.size() );
        Iterator<ConstraintViolation<User>> violations = constraintViolations.iterator();
        while (violations.hasNext()) {
			ConstraintViolation<User> each = violations.next();
			System.out.println(each.getPropertyPath()+" : "+each.getMessage());	
		}
        
    }
}
