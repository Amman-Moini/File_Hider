package model;

public class User
{
    private String name;
    private String email;

    public User(String name, String email)
    {
        this.name = name;
        this.email = email;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()

    {
        return email;
    }

    public String getName()

    {
        return name;
    }

}
