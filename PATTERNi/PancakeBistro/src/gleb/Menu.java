package gleb;

import java.util.ArrayList;

/**
 * Created by gleb on 08.06.16.
 */
public class Menu implements MenuComponent {
    private String description;
    private String name;
    private ArrayList<MenuComponent> components;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
        components = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        components.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        components.add(menuComponent);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name);
        sb.append(", ");
        sb.append(description);
        sb.append("\n");
        sb.append("------------------------------------------");
        sb.append("\n");

        for (MenuComponent component : components) {
            sb.append("\t");
            sb.append(component);
            sb.append("\n");
        }

        return sb.toString();
    }
}
