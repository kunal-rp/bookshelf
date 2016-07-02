package kunal.third.com.app2;

/**
 * Class used to define the PROFILE object
 */
class profile_object {
    String full_name;
    String username;
    String current_college;
    String email;
    int profile_id;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setCurrent_college(String current_college) {
        this.current_college = current_college;
    }


    public void setId(int id) {
        this.profile_id = id;
    }


    public String getEmail() {
        return email;
    }

    public String getCurrent_college() {
        return current_college;
    }

    public int getProfile_id() {
        return profile_id;
    }


}
