package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredientHotSauce;

    @Mock
    private Ingredient ingredientDinosaur;

    Burger burger;

    float priceBun = 100f;
    float priceIngredientHotSauce = 100f;
    float priceIngredientDinosaur = 200f;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(bun.getName()).thenReturn("Black Bun");
        Mockito.when(bun.getPrice()).thenReturn(priceBun);

        Mockito.when(ingredientHotSauce.getType()).thenReturn(SAUCE);
        Mockito.when(ingredientHotSauce.getName()).thenReturn("hot sauce");
        Mockito.when(ingredientHotSauce.getPrice()).thenReturn(priceIngredientHotSauce);

        Mockito.when(ingredientDinosaur.getType()).thenReturn(FILLING);
        Mockito.when(ingredientDinosaur.getName()).thenReturn("dinosaur");
        Mockito.when(ingredientDinosaur.getPrice()).thenReturn(priceIngredientDinosaur);

        burger = new Burger();
        burger.setBuns(bun);
    }

    @Test
    public void setBunsWhenValidBun() {
        assertEquals(String.format("Bun должно ссылаться на переданный объект. Ожидалось: %s, фактическое значение: %s", bun, burger.getBuns()), bun, burger.getBuns());
    }

    @Test
    public void addIngredientIncreasesThePrice() {
        burger.addIngredient(ingredientHotSauce);
        float expectedPrice = (priceBun * 2) + priceIngredientHotSauce;
        assertEquals(String.format("Цена бургера должна включать две булочки и один ингредиент. Ожидалось: %.2f, получено: %.2f", expectedPrice, burger.getPrice()), expectedPrice, burger.getPrice(), 0.0001f);
    }

    @Test
    public void removeIngredientReducesThePrice() {
        burger.addIngredient(ingredientHotSauce);
        burger.removeIngredient(0);
        float expectedPrice = priceBun * 2;
        assertEquals(String.format("После удаления ингредиента цена должна равняться цене двух булочек. Ожидалось: %.2f, получено: %.2f", expectedPrice, burger.getPrice()), expectedPrice, burger.getPrice(), 0.0001f);
    }

    @Test
    public void moveIngredientChangedThePositionIndex() {
        burger.addIngredient(ingredientHotSauce);
        burger.addIngredient(ingredientDinosaur);
        burger.moveIngredient(0, 1);
        assertEquals(String.format("Ингредиент на позиции 0 должен быть '%s'. Фактический: '%s'", ingredientDinosaur.getName(), burger.getIngredients().get(0).getName()), ingredientDinosaur, burger.getIngredients().get(0));
        assertEquals(String.format("Ингредиент на позиции 1 должен быть '%s'. Фактический: '%s'", ingredientHotSauce.getName(), burger.getIngredients().get(1).getName()), ingredientHotSauce, burger.getIngredients().get(1));
    }

    @Test
    public void getReceiptReturnCorrectlyFormattedReceipt() {
        burger.addIngredient(ingredientDinosaur);
        burger.addIngredient(ingredientHotSauce);
        String receipt = burger.getReceipt();
        String expectedBunLine = "(==== Black Bun ====)\n";
        assertTrue(String.format("Чек должен начинаться с верхней булочки: ожидалось '%s', но получено начало строки", expectedBunLine), receipt.startsWith(expectedBunLine));
        assertTrue(String.format("Чек должен содержать ингредиент 'dinosaur': ожидалось вхождение '= filling dinosaur =', но не найдено"), receipt.contains("= filling dinosaur ="));
        assertTrue(String.format("Чек должен содержать ингредиент 'hot sauce': ожидалось вхождение '= sauce hot sauce =', но не найдено"), receipt.contains("= sauce hot sauce ="));
        String expectedBottomBun = "(==== Black Bun ====)\n";
        assertTrue(String.format("Чек должен содержать нижнюю булочку: ожидалось '%s', но не найдено в чеке", expectedBottomBun), receipt.contains(expectedBottomBun));
        String price = String.format("Price: %f\n", burger.getPrice());
        assertTrue(String.format("Чек должен заканчиваться на: '%s'\nФактически: '%s'", price, receipt), receipt.endsWith(price));
    }
}