package agh.oop.project1;

import java.util.*;

import static java.util.Map.Entry.comparingByValue;

public class ToxicCorpsesPreferableFields implements IPreferableFields, IAnimalDeathObserver{
    HashMap<Vector2d, Integer> deathCount = new HashMap<>();
    private final int fieldsCount;
    public ToxicCorpsesPreferableFields(int width, int height){
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
    public List<Vector2d> betterFields() {
        List<Map.Entry<Vector2d, Integer>> fields = new ArrayList<>(deathCount.entrySet());
        Collections.shuffle(fields);
        return new ArrayList<>( fields
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .limit(fieldsCount/5)
                .map(x -> { return ((Vector2d)((Map.Entry<?, ?>) x).getKey()); })
                .toList() );
    }
    @Override
    public List<Vector2d> worseFields() {
        List<Map.Entry<Vector2d, Integer>> fields = new ArrayList<>(deathCount.entrySet());
        Collections.shuffle(fields);
        return new ArrayList<>( fields
                .stream()
                .sorted(comparingByValue())
                .limit(fieldsCount/5)
                .map(x -> { return ((Vector2d)((Map.Entry<?, ?>) x).getKey()); })
                .toList() );
    }
}
