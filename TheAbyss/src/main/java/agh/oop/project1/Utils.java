package agh.oop.project1;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Utils {
    public static List<Animal> fightForYourDeath(Collection<Animal> animals, int n){
    return animals.stream()
            .sorted((o1, o2) -> {
                if(o1.getEnergy() != o2.getEnergy()){
                    return -Integer.compare(o1.getEnergy(), o2.getEnergy());
                } else if (o1.getBirthDate() != o2.getBirthDate()) {
                    return -Integer.compare(o2.getBirthDate(), o1.getBirthDate());
                } else if (o1.getOffspringCount() != o2.getOffspringCount()) {
                    return -Integer.compare(o1.getOffspringCount(), o2.getOffspringCount());
                }
                else return (new Random()).nextInt(-1,2);
            })
            .limit(n)
            .toList();
}
}
