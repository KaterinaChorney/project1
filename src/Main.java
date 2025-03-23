import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        RecipeManager recipeManager = new RecipeManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running)
        {
            System.out.println("\nМеню:");
            System.out.println("1. Створити рецепт");
            System.out.println("2. Знайти рецепт");
            System.out.println("3. Переглянути всі рецепти");
            System.out.println("4. Оновити рецепт");
            System.out.println("5. Видалити рецепт");
            System.out.println("6. Сортувати рецепти");
            System.out.println("7. Вихід");
            System.out.print("Оберіть пункт зі списку: \n");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice)
            {
                case 1:
                    System.out.print("Введіть назву рецепта: ");
                    String name = scanner.nextLine();
                    System.out.print("Введіть інгредієнти: ");
                    String ingredients = scanner.nextLine();
                    System.out.print("Введіть час приготування (хвилини): ");
                    int cookingTime = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Введіть кухню: ");
                    String cuisine = scanner.nextLine();
                    recipeManager.addRecipe(name,ingredients,cookingTime,cuisine);
                    break;
                case 2:
                    System.out.print("Введіть назву рецепта для пошуку: ");
                    String searchname = scanner.nextLine();
                    recipeManager.findRecipe(searchname);
                    break;
                case 3:
                    recipeManager.viewAllRecipes();
                    break;
                case 4:
                    System.out.print("Введіть назву рецепта для оновлення: ");
                    String updatename = scanner.nextLine();
                    System.out.print("Введіть нові інгредієнти: ");
                    String newingredients = scanner.nextLine();
                    recipeManager.updateRecipe(updatename,newingredients);
                    break;
                case 5:
                    System.out.print("Введіть назву рецепта для видалення: ");
                    String deletename = scanner.nextLine();
                    recipeManager.deleteRecipe(deletename);
                    break;
                case 6:
                    System.out.println("Сортувати:\n1. За алфавітом\n2. За часом приготування\n3. За кухнею");
                    System.out.print("Оберіть пункт зі списку: ");
                    int sortchoice = scanner.nextInt();
                    scanner.nextLine();
                    recipeManager.sortRecipes(sortchoice);
                    break;
                case 7:
                    running = false;
                    System.out.println("Завершення програми...");
                    break;
                default:
                    System.out.println("Неправильний вибір. Введіть коректне значення.");
                    choice = scanner.nextInt();
                    scanner.nextLine();
            }
        }
        scanner.close();
    }
}