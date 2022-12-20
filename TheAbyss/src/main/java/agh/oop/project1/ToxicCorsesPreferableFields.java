package agh.oop.project1;

import com.google.common.collect.HashBasedTable;

import java.util.*;
import java.util.stream.Collectors;

public class ToxicCorsesPreferableFields implements IPreferableFields, IAnimalDeathObserver{
    HashMap<Vector2d, Integer> deathCount = new HashMap<>();

    private int fieldsCount;

    public ToxicCorsesPreferableFields(int width, int height){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                deathCount.put(new Vector2d(i,j),0);
            }
        }
        fieldsCount = width * height;
    }

    @Override
    public void animalDied(Animal animal) {
        deathCount.put(animal.getPosition(), deathCount.get(animal.getPosition())+1);
    }

    @Override
    public Set<Vector2d> betterFields() { // To może w przyszłości generować brzydkie rzeczy, trzebaby kiedyś napisać lepszą wersję
        return new HashSet<>(
                deathCount.entrySet().stream()
                .sorted(new Comparator(){
                    public int compare(Object o1, Object o2)
                    {
                        return ((Map.Entry<Vector2d, Integer>)o1).getValue().compareTo(((Map.Entry<Vector2d, Integer>)o2).getValue());
                    }
                })
                .limit(fieldsCount/5)
                .map(x -> { return ((Map.Entry<?, ?>) x).getKey() ;})
                .toList()
        );
    }

    @Override
    public Set<Vector2d> worseFields() {
        return new HashSet<>(
                deathCount.entrySet().stream()
                        .sorted(new Comparator(){
                            public int compare(Object o1, Object o2)
                            {
                                return ((Map.Entry<Vector2d, Integer>)o2).getValue().compareTo(((Map.Entry<Vector2d, Integer>)o1).getValue());
                            }
                        })
                        .limit(fieldsCount/5)
                        .map(x -> { return ((Map.Entry<?, ?>) x).getKey() ;})
                        .toList()
        );
    }
}
