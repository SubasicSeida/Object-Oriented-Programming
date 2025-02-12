package Practice.AnnotationsReflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

enum Role {
    ADMIN,
    USER,
    GUEST
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Secured {
    Role role();
}

class User2 {
    private String username;
    private Role role;

    public User2(String username, Role role){
        this.username = username;
        this.role = role;
    }

    public Role getRole(){
        return role;
    }
}

class AdminService {
    @Secured(role = Role.ADMIN)
    public void deleteUser(){
        System.out.println("User deleted successfully!");
    }

    @Secured(role = Role.USER)
    public void viewProfile(){
        System.out.println("Profile viewed successfully!");
    }

    public void openWebsite(){
        System.out.println("Website opened for all users.");
    }
}

class SecurityProcessor {
    public static void checkAccess(User2 user, Object service, String methodName){
        try{
            Method method = service.getClass().getMethod(methodName);

            if(method.isAnnotationPresent(Secured.class)){
                Role requiredRole = method.getAnnotation(Secured.class).role();
                if(!user.getRole().equals(requiredRole)){
                    throw new SecurityException("Access denied.");
                }
                method.invoke(service);
            } else {
                method.invoke(service);
            }

        } catch (NoSuchMethodException e){
            System.out.println(e.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e){
            System.out.println(e.getMessage());
        }
    }
}

class RoleAccessControl {
    public static void main(String[] args){
        User2 admin = new User2("Alice", Role.ADMIN);
        User2 normalUser = new User2("Bob", Role.USER);
        User2 guest = new User2("Charlie", Role.GUEST);

        AdminService service = new AdminService();
        SecurityProcessor.checkAccess(admin, service, "deleteUser");
        SecurityProcessor.checkAccess(normalUser, service, "deleteUser");

        SecurityProcessor.checkAccess(guest, service, "openWebsite");
        SecurityProcessor.checkAccess(guest, service, "viewProfile");
    }
}