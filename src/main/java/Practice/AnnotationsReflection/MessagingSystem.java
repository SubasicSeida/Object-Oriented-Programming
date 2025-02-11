package Practice.AnnotationsReflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CanSendMessage {}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface RequiresPermission {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface UserPermission {
    String level();
}

abstract class User {
    private String username;

    public User(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }
}

@UserPermission(level = "regular")
class RegularUser extends User {
    public RegularUser(String username){
        super(username);
    }
}

@UserPermission(level = "admin")
class AdminUser extends User {
    public AdminUser(String username){
        super(username);
    }
}

class MessagingSystem {

    @CanSendMessage
    @RequiresPermission
    public static void sendMessage(User user){
        Class<?> userClass = user.getClass();
        UserPermission permission = userClass.getAnnotation(UserPermission.class);

        if(permission.level().equals("admin")){
            System.out.println("User " + user.getUsername() + " (admin) sent a message.");
        } else {
            System.out.println("user " + user.getUsername() + " is restricted from sending messages.");
        }
    }

    public static void main(String[] args) {
        User admin = new AdminUser("Alice");
        User regularUser = new RegularUser("Bob");

        System.out.println("--- Message Sending Test ---");
        sendMessage(admin);
        sendMessage(regularUser);
    }
}
