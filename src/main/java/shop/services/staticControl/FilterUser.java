package shop.services.staticControl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import shop.models.entities.User;
import shop.services.sql.DAOModel;

/**
 * "thực ra viết query cũng được"<br>
 * "tôi không muốn trỏ tới database quá nhiều"<br>
 * "để hạn chế nên tôi tạo thêm filter"
 */
public interface FilterUser {

    public enum SORT {
        DEFAULT,
        USERNAME_ASCENDING, USERNAME_DESCENDING,
        NAME_ASCENDING, NAME_DESCENDING,
        EMAIL_ASCENDING, EMAIL_DESCENDING,
        ACTIVE_ASCENDING, ACTIVE_DESCENDING,
        REG_TIME_ASCENDING, REG_TIME_DESCENDING
    }

    public static User getByUserName(String username) {
        for (User e : DAOModel.USER.getMap().values()) {
            if (username.equalsIgnoreCase(e.getUsername())) {
                return e;
            }
        }
        return null;
    }

    public static User getByEmail(String email) {
        for (User e : DAOModel.USER.getMap().values()) {
            if (email.equalsIgnoreCase(e.getEmail())) {
                return e;
            }
        }
        return null;
    }

    /**
     * count active and close in list parameter input, return array has 2
     * number, first is active and seconds is close
     *
     * @param list forget count active and close
     * @return [numberOfActive, numberOfClose]
     */
    public static int[] countActive(Collection<User> list) {
        int size = list.size(), active = 0;
        for (User e : list) {
            if (e.isActive()) {
                ++active;
            }
        }
        return new int[]{active, size - active};
    }

    /**
     * "Cái này để lấy thời gian đăng ký sản phẩm sớm và muộn nhất"
     *
     * @param list to get min date and max date
     * @return [min,max] min:regTime, max:regTime<br>
     * Date[0]:minTime && Date[1]:maxTime
     */
    public static Date[] getMinMaxTime(Collection<User> list) {
        Date[] range = {new Date(), new Date()};
        list.forEach(e -> {
            long min = range[0].getTime(), max = range[1].getTime();
            long nextAt = e.getRegTime().getTime();
            if (nextAt < min) {
                range[0] = e.getRegTime();
            } else if (nextAt > max) {
                range[1] = e.getRegTime();
            }
        });

        return range;
    }

    public static List<User> getTop(List list, int top) {
        return FilterUser.getRange(list, 0, top);
    }

    public static List<User> getRange(List list, int start, int end) {
        int size = list.size();
        if (size < 1) {
            return list;
        }

        if (start < 0 || size < start) {
            start = 0;
        }

        if (end < 0 || size < end) {
            end = size;
        }

        return list.subList(start, end);
    }

    /**
     * "cái này để lấy danh sách sản phẩm trong khoảng mốc thời gian"
     *
     * @param list data input
     * @param start time register User
     * @param end time register User
     * @return list User has regTime between 'start' and 'end'
     */
    public static List<User> inTime(Collection<User> list, Date start, Date end) {
        List result = new ArrayList(list.size());
        long st = start.getTime(), en = end.getTime();

        list.forEach(e -> {
            long time = e.getRegTime().getTime();
            if (st <= time && time <= en) {
                result.add(e);
            }
        });
        return result;
    }

    /**
     * "cái này để sắp xếp dữ liệu theo filed @SORT",<br>
     * "Đâu ra cái này cay vc: Comparison method violates its general contract!"
     *
     * @param type sort by field
     * @param data to sort
     */
    public static void sort(SORT type, List<User> data) {
        switch (type) {
            case USERNAME_ASCENDING:
                data.sort((x, y) -> x.getUsername().compareTo(y.getUsername()));
                break;
            case USERNAME_DESCENDING:
                data.sort((x, y) -> -x.getUsername().compareTo(y.getUsername()));
                break;
            case NAME_ASCENDING:
                data.sort((x, y) -> x.getName().compareTo(y.getName()));
                break;
            case NAME_DESCENDING:
                data.sort((x, y) -> -x.getName().compareTo(y.getName()));
                break;
            case EMAIL_ASCENDING:
                data.sort((x, y) -> x.getEmail().compareTo(y.getEmail()));
                break;
            case EMAIL_DESCENDING:
                data.sort((x, y) -> -x.getEmail().compareTo(y.getEmail()));
                break;
            case ACTIVE_ASCENDING:
                data.sort((x, y) -> Boolean.compare(x.isActive(), y.isActive()));
                break;
            case ACTIVE_DESCENDING:
                data.sort((x, y) -> -Boolean.compare(x.isActive(), y.isActive()));
                break;
            case REG_TIME_DESCENDING:
                data.sort((x, y) -> -Long.compare(x.getRegTime().getTime(), y.getRegTime().getTime()));
                break;
            case REG_TIME_ASCENDING:
            default: // default sort by register time ascending
                data.sort((x, y) -> Long.compare(x.getRegTime().getTime(), y.getRegTime().getTime()));
        }
    }
}
