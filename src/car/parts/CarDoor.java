package car.parts;
public class CarDoor {
    //private      true open false close
    private String door;
    private String window;

    public CarDoor(){
        doorClose();
        windowClose();
    }
    public CarDoor(String door,String window){
        if(door.equals("open")||door.equals("close"))
            this.door = door;
        if(window.equals("open")||window.equals("close"))
            this.window=window;
    }
    public void doorOpen(){
        door="open";
    }
    public void doorClose(){
        door="close";
    }
    public void doorSwitch(){
        if(door.equals("open"))
            door="close";
        else
            door="open";
    }
    public void windowOpen(){
        window="open";
    }
    public void windowClose(){
        window = "close";
    }
    public void windowSwitch(){
        if(window.equals("open"))
            window="close";
        else
            window="close";
    }
    public void printInfo(){
        System.out.print("Статус дверей: ");
        if(getDoorBool())
            System.out.println("открыты.");
        else
            System.out.println("закрыты.");
        System.out.print("Статус окон: ");
        if(getWindowBool())
            System.out.println("открыты.");
        else
            System.out.println("закрыты.");
    }
    public String getDoor(){
        return door;
    }
    public String getWindow(){
        return window;
    }
    public boolean getDoorBool(){
            return door.equals("open");
    }
    public boolean getWindowBool(){
        return window.equals("open");
    }
}
