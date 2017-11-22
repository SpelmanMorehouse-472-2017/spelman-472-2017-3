import static org.junit.Assert.*;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class RecipeClassTest {
	public String message = "Hello World";
	public String name = "name";
	public String recipeName = "This is a recipe";
	public ArrayList<String> recipeList = {"cake", "brownie"};

	public RecipeClass recipeClass;
	public RecipeClass recipeClass1;
	public RecipeClass recipeClass2;
	
	@Before
	public void setup() throws Exception{
		recipeClass = new RecipeClass(name);
		recipeClass1 = new RecipeClass(recipeList);
		recipeClass2 = new RecipeClass(recipeName, recipeList);
	}
	
	@Test
	public void TestReturnRecipeName(){
		assertEquals(recipeName, recipeClass2.returnRecipeName());
	}
	@Test
	public void TestReturnRecipes(){
		assertEquals(recipeList, recipeClass2.returnRecipeName());
		assertEquals(recipeList, recipeClass1.returnRecipeName());
	}
	@Test
	public void TestGetMessage(){
		assertEquals(message, recipeClass.getMessage());
	}
}
