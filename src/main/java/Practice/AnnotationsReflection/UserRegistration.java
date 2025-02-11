package Practice.AnnotationsReflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.regex.Pattern;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotNull {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface EmailFormat {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface PasswordStrength {
    int minLength() default 5;
    boolean requiresSpecialCharacter() default false;
}

class User1 {
    @NotNull
    private String username;
    @EmailFormat
    private String email;
    @PasswordStrength
    private String password;

    public User1(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

class Validator {
    public static void validate(Object obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        for(Field field : objClass.getDeclaredFields()){

            if(field.isAnnotationPresent(NotNull.class)){
                field.setAccessible(true);
                if(field.get(obj) == null){
                    System.out.println("Error : " + field.getName() + " cannot be null.");
                }
            }

            if(field.isAnnotationPresent(EmailFormat.class)){
                field.setAccessible(true);
                if(field.get(obj) == null | !isValidEmail(field.get(obj).toString())){
                    System.out.println("Error : " + field.getName() + " not of correct format.");
                }
            }

            if(field.isAnnotationPresent(PasswordStrength.class)){
                field.setAccessible(true);
                PasswordStrength passwordStrength = field.getAnnotation(PasswordStrength.class);
                if(field.get(obj) == null | !isValidPassword(field.get(obj).toString(), passwordStrength)){
                    System.out.println("Error : " + field.getName() + " not of correct format.");
                }
            }
        }
    }

    private static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(regex, email);
    }

    private static boolean isValidPassword(String password, PasswordStrength constraint) {
        if (password.length() < constraint.minLength()) {
            return false;
        }
        if (constraint.requiresSpecialCharacter() && !password.matches(".*[!@#$%^&*()].*")) {
            return false;
        }
        return true;
    }
}

class UserRegistrationSystem {
    public static void main(String[] args) throws IllegalAccessException {
        User1 validUser = new User1("username","test@example.com", "Pass@word1");
        User1 invalidUser = new User1("","invalid-email", "weakpass");

        System.out.println("--- Validating Users ---");
        Validator.validate(validUser); // No output means valid
        Validator.validate(invalidUser);
    }
}
