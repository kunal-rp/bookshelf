package kunal.third.com.app2;

/**
 * Created by Kunal on 6/22/2016.
 */
public class interested_user_object {
    private String username;
    private String college;


    public interested_user_object(){
    }

    public interested_user_object(textbook_object textbook){
        this.username = textbook.getTextbook_user_username();
        this.college = textbook.getTextbook_college();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }



}
