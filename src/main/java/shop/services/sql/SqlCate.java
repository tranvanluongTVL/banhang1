package shop.services.sql;

import shop.services.staticControl.Query;
import shop.models.entities.Product;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SqlCate {

    String TABLE = "CATEGORIES";
    String KEY = "cgid";
    String[] FIELDS = {KEY, "name", "image"};
    String SELECT = Query.select(TABLE);
    String INSERT = Query.insert(TABLE, FIELDS);
    String UPDATE = Query.update(TABLE, KEY, FIELDS);
    String DELETE = Query.delete(TABLE, KEY);

    default String queryByIds(int size) {
        String[] fieldIds = new String[size];
        for (int i = 0; i < size; i++) {
            fieldIds[i] = KEY;
        }

        return Query.concat(SELECT, "WHERE",
                Query.conditions(" OR ", fieldIds)
        );
    }

    // get all product with category id
    ArrayList<Product> getProducts(Integer... cgids) throws SQLException;
}
