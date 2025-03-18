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
                    recipeManager.addRecipe();
                    break;
                case 2:
                    recipeManager.findRecipe();
                    break;
                case 3:
                    recipeManager.viewAllRecipes();
                    break;
                case 4:
                    recipeManager.updateRecipe();
                    break;
                case 5:
                    recipeManager.deleteRecipe();
                    break;
                case 6:
                    recipeManager.sortRecipes();
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