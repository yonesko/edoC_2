package gleb;

/**
 * Created by gleb on 08.06.16.
 */
public interface MenuComponent {
    default String getName() {
        throw new UnsupportedOperationException();
    }

    default String getDescription() {
        throw new UnsupportedOperationException();
    }

    default double getPrice() {
        throw new UnsupportedOperationException();
    }

    default boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }

    String toString();

    default void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    default void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    default MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }
}
