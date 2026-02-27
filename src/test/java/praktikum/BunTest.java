package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {

    @Parameterized.Parameter(0)
    public String name;
    @Parameterized.Parameter(1)
    public float price;

    private Bun bun;

    @Parameterized.Parameters(name = "{index}: bun({0}, {1}) => name={2}, price={3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Black Bun", 100},
                {"White Bun", 200.0f}
        });
    }

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Test
    public void getNameReturnsCorrectName() {
        assertEquals(String.format("Не совпадает имя: ожидалось \"%s\", получено \"%s\"", name, bun.getName()), name, bun.getName());
    }

    @Test
    public void getPriceReturnsCorrectPrice() {
        assertEquals(String.format("[Не совпадает цена : ожидалось %.2f, получено %.2f", price, bun.getPrice()), price, bun.getPrice(), 0.0001f);
    }
}