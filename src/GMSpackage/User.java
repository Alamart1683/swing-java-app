package GMSpackage;

// Импорт классов
import java.io.Serializable;

public class User implements Serializable{
      // Имя пользователя
    private String username;
    // Пароль пользователя
    private String password;

    // Конструктор по-умолчанию
    User()
    {
        this.username = null;
        this.password = null;
    }

    // Конструктор с параметрами
    User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    // Метод установки значения имени пользователя
    public void set_username(String string)
    {
        username = string;
    }

    // Метод установки значения пароля пользователя
    public void set_password(String string)
    {
        password = string;
    }

    // Метод получения значения имени пользователя
    public String get_username()
    {
        return username;
    }

    // Метод получения значения пароля пользователя
    public String get_password()
    {
        return password;
    } 
    
     // Метод сериализации объекта класса
    @Override
    public String toString() 
    {
        return this.username + '\0'+ this.password;
    }
}
