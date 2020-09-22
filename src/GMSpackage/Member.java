package GMSpackage;

// Импорт классов
import java.util.ArrayList;
import java.util.Scanner;

public class Member extends User {
    // Поля класса
    private ArrayList<Request>  member;
    private Request request = new Request();

    // Конструктор с параметрами
    Member(String username, String password)
    {
        super(username, password);
        member = new ArrayList<Request>(6);
        for (int index = 0; index < 7; index++)
            member.add(request);
    }

    // Метод получения списка дней
    public ArrayList<Request> get_member()
    {
        return member;
    }

    // Метод обновления списка дней
    public void set_member(ArrayList<Request> new_member)
    {
        member = new_member;
    }
}
