package agh.oop.project1;

import java.util.*;

import static java.lang.Math.abs;

public class EquatorPreferableFields implements IPreferableFields {
    Set<Vector2d> betterFields = new HashSet<>();
    Set<Vector2d> worseFields = new HashSet<>();
    public EquatorPreferableFields(int width, int height){
        Random rng = new Random();
        List<Vector2d> fields = new ArrayList<>(width*height);
        for(int i = 0; i<width; i++){
            for (int j = 0; j < height; j++) {
                fields.add(new Vector2d(i,j));
            }
        }
        Collections.shuffle(fields);
        fields.sort(Comparator.comparingInt(o -> abs(o.y() - height / 2)));
        betterFields.addAll(fields.subList(0, (int) (width*height*0.2)));
        worseFields.addAll(fields.subList((int) (width*height*0.2), width*height));
    }
    @Override
    public List<Vector2d> betterFields() {
        return new ArrayList<>( betterFields.stream().toList() );
    }
    @Override
    public List<Vector2d> worseFields() {
        return new ArrayList<>( worseFields.stream().toList() );
    }
}
