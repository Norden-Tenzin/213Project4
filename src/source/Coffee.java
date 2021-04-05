package source;

import java.lang.Math;

public class Coffee extends MenuItem implements Customizable{
    private double price;
    private String size;
    private String[] addIn;
    private int addInCount;
    private int GROWSIZE = 5;

    public Coffee (String size){
        this.setSize(size);
        this.price = 0.0;
        this.addIn = new String[5];
        this.addInCount = 0;
    }
    
    public void setSize(String size){
        this.size = size;
    }

    public boolean add(Object obj){
        if (this.addIn[this.addIn.length - 1] != null)
           grow();
        if(obj instanceof String){
            //add string to lst
            addIn[addInCount] = (String)obj;
            addInCount ++;
            return true;
        }
        return false;
    }

    // isn't used
    public boolean remove(Object obj){
        return true;
    }
    
    //grows
    private void grow() {
        String[] temp = new String[this.addIn.length + GROWSIZE];
  
        for (int i = 0; i < addInCount; i++) {
            temp[i] = this.addIn[i];
        }
        this.addIn = temp;
     }

    public double itemPrice(){
        //size
        switch(this.size){
            case "Short": this.price = 1.99;
            break;
            case "Tall": this.price = 1.99 + 0.50;
            break;
            case "Grande": this.price = 1.99 + 1.00;
            break;
            case "Venti": this.price = 1.99 + 1.50;
            break;
        }
        
        //addin
        this.price += this.addInCount * 0.20;
        //decimal 
        this.price =  Math.floor(this.price * 100) / 100;
        return this.price;
    }

    public double getPrice(){
        return this.price;
    }

    // tostring.
    public String toString(){
        String strAddIN = "";
        for(String str: addIn){
            if(str != null){
                strAddIN += str + " ";
            }
        }
        return String.valueOf(this.size + "  " + strAddIN + " " +  this.price);
    }

    // public static void main(String[] args) {
    //     Coffee cf = new Coffee("Grande");
    //     cf.add("cream");
    //     cf.itemPrice();
    //     System.out.println(cf.toString());
    // }
}

