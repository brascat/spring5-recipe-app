package guru.springframework.spring5recipeapp.commands;

public class CategoryCommand {
    private Long id;
    private String categoryName;

    public CategoryCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String description) {
        this.categoryName = description;
    }
}
