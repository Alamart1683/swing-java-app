package GMSpackage;

// Импорт классов
import java.io.Serializable;

public class Request implements Serializable {
    private String place; // Место встречи
    private int day; // День недели
    private int start_hour; // Время время начала
    private int end_hour; // Время окончания

    // Констурктор по умолчанию
    public Request()
    {
        this.place = "";
        this.day = 0;
        this.start_hour = 0;
        this.end_hour = 0;
    }

    // Конструктор с параметрами
    public Request(String place, int day, int from, int to)
    {
        this.place = place;
        this.day = day;
        this.start_hour = from;
        this.end_hour = to;
    }

    // Метод возвращения места встречи
    public String get_place()
    {
        return place;
    }

    // Метод возвращения деня недели встречи
    public int get_day()
    {
        return day;
    }

    // Метод возвращения времени начала встречи
    public int get_start_hour()
    {
        return start_hour;
    }

    // Метод возвращения времени завершения встречи
    public int get_end_hour()
    {
        return end_hour;
    }
    
     // Метод сериализации объекта класса
    @Override
    public String toString() {
        return this.place + '\0' + this.day + '\0' + this.start_hour + '\0' + this.end_hour;
    }
}
