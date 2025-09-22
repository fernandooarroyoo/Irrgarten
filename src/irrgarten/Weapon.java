/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author fernando
 */
public class Weapon {
    private float power;
    private int uses;
    
    public Weapon(float power, int uses){
        this.power = power;
        this.uses = uses;
    }
    
    public float attack(){
        if(this.uses > 0){
            return this.power;
        }
        else{
            return 0;
        }
    }
    
    @Override //por qué NetBeans me ha puesto esto? 
    public String toString(){
        return "W["+this.power+","+this.uses+"]";
    }
}
