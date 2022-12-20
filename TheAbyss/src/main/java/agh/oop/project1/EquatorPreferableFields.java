package agh.oop.project1;

import java.util.*;

import static java.lang.Math.ceil;

public class EquatorPreferableFields implements IPreferableFields {
    Set<Vector2d> betterFields = new TreeSet<>();
    Set<Vector2d> worseFields = new TreeSet<>();

    public EquatorPreferableFields(int width, int height){
        Random rng = new Random();
        if(height % 2 == 1){
            int preferableFieldsCount = (width * height)/5;
            int fullRowsCount = preferableFieldsCount/width;
            for(int i=0; i<fullRowsCount/2+1;i++){
                for(int j=0; j<width; j++){
                    betterFields.add(new Vector2d(j,height/2+i));
                    betterFields.add(new Vector2d(j,height/2-i));
                }
            }
            for(int i=0; i<width; i++){
                if(rng.nextDouble(0,1) <= (preferableFieldsCount - fullRowsCount*width)/preferableFieldsCount){
                    betterFields.add(new Vector2d(i, (int) (height/2 + ceil(fullRowsCount/2))));
                    betterFields.add(new Vector2d(i, (int) (height/2 - ceil(fullRowsCount/2))));
                }
            }
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if(!betterFields.contains(new Vector2d(i,j))){
                        worseFields.add(new Vector2d(i,j));
                    }
                }
            }
        } else {
            int preferableFieldsCount = (width * height)/5;
            int fullRowsCount = (int) ((height / 2) * 0.2);
            for(int i=0; i<fullRowsCount/2;i++){
                for(int j=0; j<width; j++){
                    betterFields.add(new Vector2d(j,(height-1)/2+i));
                    betterFields.add(new Vector2d(j,(height-1)/2-i));
                }
            }
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if(!betterFields.contains(new Vector2d(i,j))){
                        worseFields.add(new Vector2d(i,j));
                    }
                }
            }
        }
    }

    @Override
    public Set<Vector2d> betterFields() {
        return betterFields;
    }

    @Override
    public Set<Vector2d> worseFields() {
        return worseFields;
    }
}