package algorithm.refresh;

import algorithm.Population;

/**
 * Обновление популяции; удаление лишних особей
 */
public interface Refresh {
    Population refresh(Population population);
}
