import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class RecipeManager {
    private List<Recipe> recipes ;
    private final String FILE_PATH;
  private final Gson gson;
    public RecipeManager(String filePath, Gson gson) {
        this.FILE_PATH = filePath;
        this.gson = gson;
        this.recipes = new ArrayList<>();
        loadFromFile();
    }
    public void addRecipe(String name,String ingredients,int cookingTime,String cuisine) {
        recipes.add(new Recipe(name, ingredients, cookingTime, cuisine));
        saveToFile();
        System.out.println("Рецепт успішно додано!");
    }

    public void viewAllRecipes() {
        if (recipes.isEmpty()) {
            System.out.println("Список рецептів порожній.");
        } else {
            for (Recipe recipe : recipes) {
                System.out.println(recipe);
                System.out.println("-------------------------------------------------");
            }
        }
    }

    public void findRecipe(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                System.out.println(recipe);
                return;
            }
        }
        System.out.println("Рецепт не знайдено.");
    }

    public void updateRecipe(String name, String ingredients) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                recipe.setIngredients(ingredients);
                saveToFile();
                System.out.println("Рецепт оновлено!");
                return;
            }
        }
        System.out.println("Рецепт не знайдено.");
    }

    public void deleteRecipe(String name) {
        recipes.removeIf(recipe -> recipe.getName().equalsIgnoreCase(name));
        saveToFile();
        System.out.println("Рецепт видалено.");
    }

    public void sortRecipes(int choice) {
        if (recipes.isEmpty()) {
            System.out.println("Список рецептів порожній.");
            return;
        }
        switch (choice) {
            case 1:
                recipes.sort(Comparator.comparing(Recipe::getName));
                System.out.println("Рецепти відсортовані за алфавітом!");
                break;
            case 2:
                recipes.sort(Comparator.comparingInt(Recipe::getCookingTime));
                System.out.println("Рецепти відсортовані за часом приготування!");
                break;
            case 3:
                recipes.sort(Comparator.comparing(Recipe::getCuisine));
                System.out.println("Рецепти відсортовані за кухнею!");
                break;
            default:
                System.out.println("Невірний вибір.");
                return;
        }
        viewAllRecipes();
    }

    private void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH, StandardCharsets.UTF_8)) {
            gson.toJson(recipes, writer);
        } catch (IOException e) {
            System.out.println("Помилка збереження у файл: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                String json = new String (Files.readAllBytes(Paths.get(FILE_PATH)), StandardCharsets.UTF_8);
                recipes = gson.fromJson(json, new TypeToken<List<Recipe>>() {}.getType());
                if (recipes == null) recipes = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Помилка зчитування з файлу: " + e.getMessage());
        }
    }
}