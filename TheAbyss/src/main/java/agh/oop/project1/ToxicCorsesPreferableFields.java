package agh.oop.project1;

import java.util.*;

import static java.util.Map.Entry.comparingByValue;

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
    public List<Vector2d> betterFields() { // To może w przyszłości generować brzydkie rzeczy, trzebaby kiedyś napisać lepszą wersję
        return new ArrayList<>( deathCount.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .limit(fieldsCount/5)
                .map(x -> { return ((Vector2d)((Map.Entry<?, ?>) x).getKey()) ;})
                .toList() );
    }

    @Override
    public List<Vector2d> worseFields() {
        return new ArrayList<>( deathCount.entrySet()
                .stream()
                .sorted(comparingByValue())
                .limit((fieldsCount/5)*4)
                .map(x -> { return ((Vector2d)((Map.Entry<?, ?>) x).getKey()) ;})
                .toList() );
    }
}
