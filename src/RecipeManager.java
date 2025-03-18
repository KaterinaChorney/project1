import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class RecipeManager {
    private List<Recipe> recipes = new ArrayList<>();
    private final String FILE_PATH = "recipes.json";
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();
    private final Scanner scanner = new Scanner(System.in);
    public RecipeManager() {
        loadFromFile();
    }
    public void addRecipe() {
        System.out.print("Введіть назву рецепта: ");
        String name = scanner.nextLine();
        System.out.print("Введіть інгредієнти: ");
        String ingredients = scanner.nextLine();
        System.out.print("Введіть час приготування (хвилини): ");
        int cookingTime = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введіть кухню: ");
        String cuisine = scanner.nextLine();
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

    public void findRecipe() {
        System.out.print("Введіть назву рецепта для пошуку: ");
        String name = scanner.nextLine();
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                System.out.println(recipe);
                return;
            }
        }
        System.out.println("Рецепт не знайдено.");
    }

    public void updateRecipe() {
        System.out.print("Введіть назву рецепта для оновлення: ");
        String name = scanner.nextLine();
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                System.out.print("Введіть нові інгредієнти: ");
                recipe.setIngredients(scanner.nextLine());
                saveToFile();
                System.out.println("Рецепт оновлено!");
                return;
            }
        }
        System.out.println("Рецепт не знайдено.");
    }

    public void deleteRecipe() {
        System.out.print("Введіть назву рецепта для видалення: ");
        String name = scanner.nextLine();
        recipes.removeIf(recipe -> recipe.getName().equalsIgnoreCase(name));
        saveToFile();
        System.out.println("Рецепт видалено");
    }

    public void sortRecipes() {
        if (recipes.isEmpty()) {
            System.out.println("Список рецептів порожній.");
            return;
        }
        System.out.println("Сортувати:\n1. За алфавітом\n2. За часом приготування\n3. За кухнею");
        System.out.print("Оберіть пункт зі списку: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
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
                String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)), StandardCharsets.UTF_8);
                recipes = gson.fromJson(json, new TypeToken<List<Recipe>>() {}.getType());
                if (recipes == null) recipes = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Помилка зчитування з файлу: " + e.getMessage());
        }
    }
}