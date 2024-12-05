package Week10;

abstract class User {
    private String username;

    public User(String username){
        this.username = username;
    }
}

@UserPermission(permission = "ADMIN")
class AdminUser extends User {
    public AdminUser(String username){
        super(username);
    }
}

@RequiresPermission
class RegularUser extends User {
    public RegularUser(String username){
        super(username);
    }
}

class Message {
    @CanSendMessage
    public static void sendMessage(User user, String message){
        Class<?> userClass = user.getClass();

        if(userClass.isAnnotationPresent(UserPermission.class)){
            UserPermission userPermission = userClass.getAnnotation(UserPermission.class);
            if("ADMIN".equals(userPermission.permission())){
                System.out.println(message);
            }
        } else if (userClass.isAnnotationPresent(RequiresPermission.class)){
            System.out.println("This user does not have permission.");
        } else {
            System.out.println("Unknown role.");
        }
    }
}

class MessageSystem {
    public static void main(String[] args){
        AdminUser admin = new AdminUser("Admin 111");
        RegularUser user = new RegularUser("Regular 222");
        AdminUser admin2 = new AdminUser("Admin 333");
        Message.sendMessage(admin, "First Message");
        Message.sendMessage(user, "Second Message");
        Message.sendMessage(admin2, "Third Message");
    }
}
