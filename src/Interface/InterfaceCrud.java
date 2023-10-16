package Interface;

import java.util.List;

/**
 * Interface générique pour le calcul de l'IMC d'une entité de type T.
 *
 * @param <T> Le type d'entité pour lequel l'IMC doit être calculé.
 */
public interface InterfaceCrud<T> {
    /**
     * Calcule l'IMC de l'entité de type T et le renvoie.
     *
     * @param t L'entité pour laquelle l'IMC doit être calculé.
     * @return L'IMC calculé.
     */
    public void calculer(T t);
}
