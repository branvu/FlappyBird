package exercises;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Bird;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * You will complete your java exercises here
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class JavaExercises {
    Bird bird;


    @Test public void ReplaceThisWithYourMethod(){
    }


    @Test public void doSomething(){

    }

    public void add(){

    }
    public int something(){
        return 1 + 2;
    }
    public double something2(double variable, int variable2){
        return variable + variable2;
    }
    public boolean isTrue(double number){
        if(number > 1){
            return true;
        }
        else{
            return false;
        }
    }
    public void printName(String name){
        System.out.println("Your name is " + name);
    }
    @Test public void callMethod(){
        System.out.println(isTrue(2.1));
        Library LansdaleLib = new Library("book", "red",1,1,0,150,"Lansdale",false,70);
        LansdaleLib.checkInBook("book");
        LansdaleLib.classbookName = "7";
        System.out.println(LansdaleLib.classbookName);
    }




    public class Library{
    String classbookName;
    String color;
    int shelves;
    int walls;
    int ceilings;
    int computers;
    String name;
    boolean hasAir;
        Bird bird;
    protected void handleInput(){
        if (Gdx.input.justTouched()){
            bird.jump();
        }
    }
    public Library(String bookNameInput, String color, int shelves, int walls, int
                   ceilings, int computers, String name, boolean hasAir, int numberOfBaths){
        classbookName = bookNameInput;
        this.ceilings = ceilings;
        this.color = color;
        this.walls = walls;
        this.computers = computers;
        this.name = name;
        this.shelves = shelves;
        this.name = name;
        this.hasAir = hasAir;

    }

        public void checkInBook(String bookName){
            //Checked in book
            this.classbookName = bookName;
        }
        public String checkOutBook(){
            return classbookName;
        }

}

}



