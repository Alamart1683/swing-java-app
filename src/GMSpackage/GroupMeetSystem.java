package GMSpackage;

// Импорт классов
import java.util.ArrayList;
import java.util.List;

// Основной класс системы
public class GroupMeetSystem {
    // Поля класса
    private static List<String> places = new ArrayList<String>(); // Список мест для встречи
    private static ArrayList<User> users = new ArrayList<User>(); // Список пользователей
    private static ArrayList<Request> days = new ArrayList<Request>(); // Список дней
    private static User current_user = new User(); // Текущий пользователь
    
     // Метод получения текущего пользователя
    public static User get_current_user() {
        return current_user;
    }
    
    // Метод установления списка пользователей
    public static void set_users(ArrayList<User> list) {
        users = new ArrayList<User>(list);
    }
    
    // Метод получения списка пользователей
    public static ArrayList<User> get_users() {
        return users;
    }
    
    // Метод инициализации
    public static void initialise() {
        places = new ArrayList<String>();
        users = new ArrayList<User>();
    }
    
    // Метод входа в систему
    public static User login_user(String current_username, String current_password) {
        User temp_user = new User();
        // Проверка существования пользователя
        boolean user_exists = false;
        int size = users.size();
        for (int index = 0; index < size; index++) {
            if (users.get(index).get_username().equals(current_username)) {
                user_exists = true;
                temp_user = users.get(index);
                break;
            }
        }
        if (user_exists == false) {
            return null;
        }
        else {
            // Проверка соответствия пароля пользователя
            if (temp_user.get_password().equals(current_password)) {
                System.out.println();
                return temp_user;
            }
            else {
                return null;
            }
        }
    }
    
    // Метод проверки входа в систему
    public static boolean system_enter(String name, String password) {
        current_user = login_user(name, password);
        if (current_user == null) {
            return false;
        }
        else return true;
    }

    // Метод регистрации администратора
    public static boolean register_admin(String current_username, String current_password) {
        Admin admin = new Admin(current_username, current_password); //Создание нового администратора
        // Проверка существования пользователя
        boolean user_exists = false;
        int size = users.size();
        for (int index = 0; index < size; index++) {
            if (users.get(index).get_username().equals(current_username)) {
                user_exists = true;
                break;
            }
        }
        if (user_exists == false) {
            users.add(admin);
            return true;
        }
        else return false;
    }

    // Метод регистрации члена команды
    public static boolean register_member(String current_username, String current_password) {
        Member mem = new Member(current_username, current_password); //Создание нового фаната
        // Проверка существования пользователя
        boolean user_exists = false;
        int size = users.size();
        for (int index = 0; index < size; index++) {
            if (users.get(index).get_username().equals(current_username)) {
                user_exists = true;
                break;
            }
        }
        if (user_exists == false) {
            users.add(mem);
            return true;
        }
        else return false;
    }
    
    // Метод добавления места встречи в список мест встречи
    public static void add_place(String place) {
        places.add(place);
    }
    
    // Метод получния мест встречи
    public static List<String> get_places() {
        ArrayList<String> list = new ArrayList<String>();
        // Проверка наличия песен в списке
        if (places.size() == 0) {
            list.add("Список пуст");
        }
        else {
            int size = places.size();
            // Проход по списку песен
            for (int index = 0; index < size; index++) {
                list.add((index+1) + ". " + places.get(index));
            }
        }
        return list;
    }
    
    // Метод заполнения дня встречи для члена команды
    public static void add_day(int day, String  place, int from, int to) {
        // Проверка на небытность администратором
        if (!(current_user instanceof Admin)) {
            int index = users.indexOf(current_user);
            Request request = new Request(place, day, from, to); // Добавляемый запрос
            ArrayList<Request> current_member = ((Member) current_user).get_member(); // Имеющийся список дней
            current_member.set(day, request);
            ((Member) current_user).set_member(current_member);
             users.set(index, current_user);
        }
    }
    
    // Метод составления списка дней, когда все члены команды смогут встретиться
    public static String process_request() {
        User buffer_current_user; // Сравниваемый член команды
        User compared_user; // Сравниваемый с ним член команды
        boolean flag = true; // Логическая переменная
        // Пропуск администраторов
        ArrayList<User> buffer_list = new ArrayList<User>();
        for (int jndex = 0; jndex < users.size(); jndex++) {
            if (!(users.get(jndex) instanceof Admin)) {
                buffer_list.add(users.get(jndex));
            }
        }
        if (buffer_list.size() >= 2) {
            // Проход по всем дням недели
            for (int index = 0; index < 7; index++) {
                // Проверка всех членов команды для текущего дня недели
                for (int index_user = 0; index_user < buffer_list.size() - 1; index_user++) {
                    compared_user = buffer_list.get(index_user + 1);
                    buffer_current_user = buffer_list.get(index_user);
                    if (((Member) buffer_current_user).get_member().get(index).get_start_hour() != ((Member) compared_user).get_member().get(index).get_start_hour()
                        || ((Member) buffer_current_user).get_member().get(index).get_end_hour() != ((Member) compared_user).get_member().get(index).get_end_hour()
                        || (((Member) buffer_current_user).get_member().get(index).get_place().compareTo(((Member) compared_user).get_member().get(index).get_place())) != 0
                        || ((Member) buffer_current_user).get_member().get(index).get_start_hour() <= 0 || ((Member) buffer_current_user).get_member().get(index).get_end_hour() <= 0
                        || ((Member) buffer_current_user).get_member().get(index).get_place().equals("") || ((Member) compared_user).get_member().get(index).get_start_hour() <= 0
                        || ((Member) buffer_current_user).get_member().get(index).get_end_hour() <= 0 || ((Member) compared_user).get_member().get(index).get_place().equals("")) {
                            flag = false;
                        }
                    }
                    // Если все члены команды совпадают
                    if (flag) {
                        int final_start_hour = ((Member) buffer_list.get(0)).get_member().get(index).get_start_hour();
                        int final_end_hour = ((Member) buffer_list.get(0)).get_member().get(index).get_end_hour();
                        String final_place = ((Member) buffer_list.get(0)).get_member().get(index).get_place();
                        String buffer_string = final_place.substring(3, final_place.length());
                        String result = "В " + (index + 1) + " день недели все члены команды смогут провести встречу с " + final_start_hour + " по " + final_end_hour + " в " + buffer_string; // Строка-результат поиска дня встречи
                        return result;
                    } else flag = true;
                }
                return ("Подходящий день встречи для всех членов команды не найден");
            }
        return ("В команде слишком мало членов");
    }
    
    // Метод вывода списка зарегистрированных пользователей
    public static ArrayList<String> output_users() {
        int size = users.size();
        ArrayList<String> list = new ArrayList<String>();
        list.add("Текущий список пользователей: ");
        if (size != 0) {
            for (int index = 0; index < size; index++) {
                list.add((index+1) + ". " + users.get(index).get_username());
            }
        }
        else list.add("Список пуст");
        return list;
    }     
}