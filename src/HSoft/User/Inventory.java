/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HSoft.User;

/**
 *
 * @author Gina
 */
public class Inventory {
    public Inventory () {
        productNumber = "";
        productName = "";
        productQuantity = 0;
        productDescription = "";
        productMeasureUnit = "";
        productMeasureNumber = 0;
        productDiscount = 0.0;
        productPrice = 0.0;
    }
    
    public String productNumber;
    public String productName;
    public int productQuantity;
    public String productDescription;
    public String productMeasureUnit;
    public double productMeasureNumber;
    public double productDiscount;
    public double productPrice;
}
