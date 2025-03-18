import java.io.Serializable;
import java.time.LocalDateTime;

public class Recipe implements Serializable {
    private String name;
    private String ingredients;
    private int cookingTime;
    private String cuisine;
    private LocalDateTime creationDate;
    public Recipe(String name, String ingredients, int cookingTime, String cuisine) {
        this.name = name;
        this.ingredients = ingredients;
        this.cookingTime=cookingTime;
        this.cuisine = cuisine;
        this.creationDate = LocalDateTime.now();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIngredients() {
        return ingredients;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public int getCookingTime() {
        return cookingTime;
    }
    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }
    public String getCuisine() {
        return cuisine;
    }
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    @Override
    public String toString() {
        return "Назва: " + name + "\nІнгредієнти: " + ingredients +
                "\nЧас приготування: " + cookingTime + " хв." +
                "\nКухня: " + cuisine +
                "\nДата створення: " + creationDate;
    }
}