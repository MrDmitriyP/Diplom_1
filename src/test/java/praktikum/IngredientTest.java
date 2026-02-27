package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientTest {

    @Parameterized.Parameter(0)
    public IngredientType type;
    @Parameterized.Parameter(1)
    public String name;
    @Parameterized.Parameter(2)
    public float price;

    private Ingredient ingredient;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {SAUCE, "hot sauce", 100},
                {FILLING, "cutlet", 100.0f}
        });
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void getNameReturnsExpectedName() {
        assertEquals(String.format("Имя не совпадает: ожидалось \"%s\", получено \"%s\"", name, ingredient.getName()), name, ingredient.getName());
    }

    @Test
    public void getPriceReturnsExpectedPrice() {
        assertEquals(String.format("Цена не совпадает: ожидалось %.2f, получено %.2f", price, ingredient.getPrice()), price, ingredient.getPrice(), 0.0001f);
    }

    @Test
    public void getTypeReturnsExpectedType() {
        assertEquals(String.format("Тип не совпадает: ожидалось \"%s\", получено \"%s\"", type, ingredient.getType()), type, ingredient.getType());
    }
}