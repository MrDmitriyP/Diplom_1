package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;


@RunWith(Parameterized.class)
public class DatabaseIngredientTest {

    private final int index;
    private final IngredientType type;
    private final String name;
    private final float price;

    Database database;
    List<Ingredient> ingredients;

    public DatabaseIngredientTest(int index, IngredientType type, String name, float price) {
        this.name = name;
        this.price = price;
        this.index = index;
        this.type = type;
    }

    @Before
    public void setUp() {
        database = new Database();
        ingredients = database.availableIngredients();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, SAUCE, "hot sauce", 100.0f},
                {1, SAUCE, "sour cream", 200.0f},
                {2, SAUCE, "chili sauce", 300.0f},
                {3, FILLING, "cutlet", 100.0f},
                {4, FILLING, "dinosaur", 200.0f},
                {5, FILLING, "sausage", 300.0f}
        });
    }

    @Test
    public void availableIngredientsReturnListOfIngredients() {
        Ingredient ingredient = ingredients.get(index);
        assertNotNull("Список ингредиентов не должен быть null", ingredients);
        assertEquals("Должно быть 6 видов ингредиентов", 6, ingredients.size());
        assertEquals(String.format("Тип ингредиента под индексом %d", index), type,ingredient.getType());
        assertEquals(String.format("Имя ингредиента под индексом %d", index), name, ingredient.getName());
        assertEquals(String.format("Цена ингредиента под индексом %d", index), price, ingredient.getPrice(), 0.0001f);
    }
}
