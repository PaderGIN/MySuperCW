package prod.mysupercw;

import Models.User;

public class UserPageController{
    static User user;

    public void getUserFrom(){
        user = RegistrationPageController.user;
    }


}
