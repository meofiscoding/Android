package model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey (autoGenerate = true)
    private int _id;
    @ColumnInfo
    private String _productName;
    @ColumnInfo
    private double _productPrice;
    @ColumnInfo
    private String _productDescription;

    public Product() {
    }

    public Product(int _id, String _productName, String _productDescription, double _productPrice) {
        this._id = _id;
        this._productName = _productName;
        this._productPrice = _productPrice;
        this._productDescription = _productDescription;
    }

    public Product(String _productName, String _productDescription, double _productPrice) {
        this._productName = _productName;
        this._productPrice = _productPrice;
        this._productDescription = _productDescription;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_productName() {
        return _productName;
    }

    public void set_productName(String _productName) {
        this._productName = _productName;
    }

    public double get_productPrice() {
        return _productPrice;
    }

    public void set_productPrice(double _productPrice) {
        this._productPrice = _productPrice;
    }

    public String get_productDescription() {
        return _productDescription;
    }

    public void set_productDescription(String _productDescription) {
        this._productDescription = _productDescription;
    }
}