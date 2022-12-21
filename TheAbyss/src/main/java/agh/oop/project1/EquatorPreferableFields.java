package agh.oop.project1;

import java.util.*;

import static java.lang.Math.ceil;

public class EquatorPreferableFields implements IPreferableFields {
    Set<Vector2d> betterFields = new HashSet<>();
    Set<Vector2d> worseFields = new HashSet<>();

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
                if(rng.nextDouble(0,1) <= (preferableFieldsCount - (fullRowsCount*width))/(float)(2*width)){
                    betterFields.add(new Vector2d(i, (int) ceil((height/2 + fullRowsCount/2 +1))));
                }
                if(rng.nextDouble(0,1) <= (preferableFieldsCount - (fullRowsCount*width))/(float)(2*width)){
                    betterFields.add(new Vector2d(i, (int) ceil((height/2 - fullRowsCount/2 -1))));
                }
            }
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if(!betterFields.contains(new Vector2d(i,j))){
                        worseFields.add(new Vector2d(i,j));
                    }
                }
            }
        } else { // Do poprawy wszystko w dół
            int preferableFieldsCount = (width * height)/5;
            int fullRowsCount = (int) ((height / 2) * 0.2);
            for(int i=0; i<fullRowsCount/2;i++){
                for(int j=0; j<width; j++){
                    betterFields.add(new Vector2d(j,(height-1)/2+i));
                    betterFields.add(new Vector2d(j,(height-1)/2-i+1));
                }
            }
            for(int i=0; i<width; i++){
                if(rng.nextDouble(0,1) <= (preferableFieldsCount - (fullRowsCount*width))/(float)(2*width)){
                    betterFields.add(new Vector2d(i, (int) ceil((height/2 + fullRowsCount/2 +1))));
                }
                if(rng.nextDouble(0,1) <= (preferableFieldsCount - (fullRowsCount*width))/(float)(2*width)){
                    betterFields.add(new Vector2d(i, (int) ceil((height/2 - fullRowsCount/2 -1))));
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
    public List<Vector2d> betterFields() {
        return new ArrayList<>( betterFields.stream().toList() );
    }

    @Override
    public List<Vector2d> worseFields() {
        return new ArrayList<>( worseFields.stream().toList() );
    }
}
