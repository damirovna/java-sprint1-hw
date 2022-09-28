import java.util.Locale;

public enum Month {
    JANUARY("Январь"),
    FEBRUARY("Февраль"),
    MARCH("Март"),
    APRIL("Апрель"),
    MAY("Май"),
    JUNE("Июнь"),
    JULY("Июль"),
    AUGUST("Август"),
    SEPTEMBER("Сентябрь"),
    OCTOBER("Октябрь"),
    NOVEMBER("Ноябрь"),
    DECEMBER("Декабрь");

    String name;

    Month(String name) {
        this.name = name;
    }
    public static Integer getIndex(String monthName){
        for (Month month: values()){
            if (month.name.toUpperCase(Locale.ROOT).equals(monthName.toUpperCase())){
                return month.ordinal();
            }
        }
        return null;
    }

}
