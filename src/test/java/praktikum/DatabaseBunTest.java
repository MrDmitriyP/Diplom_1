package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Database;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class DatabaseBunTest {

    private final String name;
    private final float price;
    private final int index;

    Database database;
    List<Bun> buns;

    public DatabaseBunTest(int index, String name, float price) {
        this.index = index;
        this.name = name;
        this.price = price;
    }

    @Before
    public void setUp() {
        database = new Database();
        buns = database.availableBuns();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, "black bun", 100},
                {1, "white bun", 200},
                {2, "red bun", 300}
        });
    }

    @Test
    public void availableBunsReturnListOfBuns() {
        Bun bun = buns.get(index);
        assertNotNull("Список булочек не должен быть null", buns);
        assertEquals("Должно быть 3 вида булочек", 3, buns.size());
        assertEquals(String.format("Имя булочки под индексом %d", index), name, bun.getName());
        assertEquals(String.format("Цена булочки под индексом %d", index), price, bun.getPrice(), 0.0001f);
    }
}
